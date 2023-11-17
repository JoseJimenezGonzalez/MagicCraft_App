package com.example.magiccraftapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magiccraftapp.api.ScryfallService
import com.example.magiccraftapp.databinding.FragmentSearchCardsBinding
import com.example.magiccraftapp.model.Card
import com.example.magiccraftapp.view.adapter.AdapterSearchCards
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder


class SearchCardsFragment : Fragment() {

    private var _binding: FragmentSearchCardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AdapterSearchCards

    private val cardModelList = mutableListOf<Card>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.svSearchCards.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    showError("Fallo")
                } else {
                    searchCardsByName(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun getRetrofitForCards(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.scryfall.com/") // La URL base común
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }




    private fun searchCardsByName(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Formatear el nombre para que sea seguro en una URL
                val formattedQuery = URLEncoder.encode(name, "UTF-8")

                // Construir la URL completa
                val fullUrl = "cards/search?q=$formattedQuery"

                // Llamada a la API para buscar la carta con el nombre especificado
                val response = getRetrofitForCards().create(ScryfallService::class.java).searchCards(fullUrl)

                requireActivity().runOnUiThread {
                    if (response.isSuccessful) {
                        val datos = response.body()
                        //Limpiamos la lista
                        cardModelList.clear()
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
                        showError("Hola")
                    }
                }
            } catch (e: Exception) {
                // Manejar errores de la llamada a la API
                requireActivity().runOnUiThread {
                    Log.d("Error", "Error: ${e.message}")
                    showError("Error en la llamada a la API: ${e.message}")

                }
            }
        }
    }



    private fun setupRecyclerView() {
        val recyclerView = binding.rvSearchCards
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = AdapterSearchCards(requireContext())
        recyclerView.adapter = adapter

    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
