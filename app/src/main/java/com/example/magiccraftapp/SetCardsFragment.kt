package com.example.magiccraftapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magiccraftapp.api.ScryfallService
import com.example.magiccraftapp.databinding.FragmentSetCardsBinding
import com.example.magiccraftapp.model.Card
import com.example.magiccraftapp.view.adapter.AdapterSearchCards
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder

class SetCardsFragment : Fragment() {

    private var _binding: FragmentSetCardsBinding? = null
    private val binding get() = _binding!!
    private var nameSetEng: String = ""
    private lateinit var adapter: AdapterSearchCards
    private val cardModelList = mutableListOf<Card>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSetCardsBinding.inflate(inflater, container, false)
        return binding.root
    }
    //Lo uso para recuperar los datos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener el String pasado como argumento
        val argumentos = arguments
        if (argumentos != null) {
            nameSetEng = arguments?.getString("setNameEng").toString()
            Log.d("FragmentB", "Contenido recibido: $nameSetEng")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        searchCardsBySet(nameSetEng)

    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvSearchSetCards
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = AdapterSearchCards(requireContext())
        recyclerView.adapter = adapter
    }

    private fun getRetrofitForCards(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.scryfall.com/") // La URL base común
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchCardsBySet(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Formatear el nombre para que sea seguro en una URL
                val formattedQuery = URLEncoder.encode(name, "UTF-8")
                //https://api.scryfall.com/cards/search?q=set:tempest
                // Construir la URL completa
                val fullUrl = "cards/search?q=set:$formattedQuery"

                // Llamada a la API para buscar la carta con el nombre especificado
                val response = getRetrofitForCards().create(ScryfallService::class.java).searchCards(fullUrl)

                requireActivity().runOnUiThread {
                    if (response.isSuccessful) {
                        val datos = response.body()
                        //Limpiamos la lista
                        cardModelList.clear()
                        //Cabecera
                        val setEdition = datos?.data?.get(0)?.set_name
                        if (setEdition != null) {
                            binding.tvNameSet.text = setEdition.capitalize()
                        }
                        // Tenemos que recorrer los datos
                        datos?.data?.forEach { card ->
                            val namee = card.name
                            val edition = card.set_name
                            val rarity = card.rarity
                            val image = card.image_uris.art_crop
                            var price = card.prices.eur
                            val text = card.oracle_text
                            val reserved = card.reserved.toString()
                            val type = card.type_line
                            val manaCost = card.mana_cost
                            //Da problemas si la carta de set online
                            if (price.isNullOrEmpty()){
                                price = "0.0"
                            }

                            val cardModel = Card(namee, image, rarity, reserved, price, edition, text, type, manaCost)

                            cardModelList.add(cardModel)
                        }
                        adapter.updateData(cardModelList)
                    } else {
                        showError("Mierda")
                    }
                }
            } catch (e: Exception) {
                // Manejar errores de la llamada a la API
                requireActivity().runOnUiThread {
                    Log.d("Error", "Error: ${e.message}")
                    //showError("Error en la llamada a la API: ${e.message}")

                }
            }
        }
    }
    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}