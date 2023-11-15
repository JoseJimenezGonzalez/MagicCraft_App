package com.example.magiccraftapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magiccraftapp.databinding.FragmentSetsBinding
import com.example.magiccraftapp.model.SetItem
import com.example.magiccraftapp.view.adapter.AdapterSets

class SetsFragment : Fragment(), AdapterSets.OnSetClickListener {

    private var _binding: FragmentSetsBinding? = null
    private val binding get() = _binding!!

    val mapSetsEspToEng = mapOf(
        "Alpha" to "Alpha",
        "Beta" to "Beta",
        "Unlimited" to "Unlimited",
        "Arabian Nights" to "Arabian Nights",
        "Antiquities" to "Antiquities",
        "Legends" to "Legends",
        "The Dark" to "The Dark",
        "Fallen Empiress" to "Fallen Empires",
        "Era Glacial" to "Ice Age",
        "Alianzas" to "Alliances",
        "Ola de frio" to "Coldsnap",
        "Espejismo" to "Mirage",
        "Visiones" to "Visions",
        "Viento ligero" to "Weatherlight",
        "Tempestad" to "tempest",
        "Fortaleza" to "Stronghold",
        "Exodo" to "Exodus",
        "Saga de Urza" to "Urza's Saga",
        "Legado de Urza" to "Urza's Legacy",
        "Destino de Urza" to "Urza's Destiny",
        "Mascaras de mercadia" to "Mercadian Masques",
        "Nemesis" to "Nemesis",
        "Profecia" to "Prophecy",
        "Invasion" to "Invasion",
        "Cambio de Planes" to "Planeshift",
        "Transmigracion" to "Apocalypse",
        "Odisea" to "Odyssey",
        "Tormento" to "Torment",
        "Juicio" to "Judgment",
        "Embestida" to "Onslaught",
        "Legiones" to "Legions",
        "Azote" to "Scourge",
        "Mirrodin" to "Mirrodin",
        "Darksteel" to "Darksteel",
        "Amanecer de la Quinta" to "Fifth Dawn",
        "Campeones de Kamigawa" to "Champions of Kamigawa",
        "Traidores de Kamigawa" to "Betrayers of Kamigawa",
        "Salvadores de Kamigawa" to "Saviors of Kamigawa",
        "Ravnica: Ciudad de Gremios" to "Ravnica: City of Guilds",
        "Pacto de Gremios" to "Guildpact",
        "Disensión" to "Dissension",
        "Ola de Frío" to "Cold Snap",
        "Lorwyn" to "Lorwyn",
        "Alborada" to "Dawn",
        "Espiral del Tiempo" to "Time Spiral",
        "Alara renacida" to "Alara Reborn",
        "Cicatrices de Mirrodin" to "Scars of Mirrodin",
        "Colección Básica 2023" to "Core Set 2023",
        "Innistrad: Caza de Medianoche" to "Innistrad: Midnight Hunt",
        "Dominaria" to "Dominaria",
        "Zendikar Resurge" to "Zendikar Resurgent",
        "Trono de Eldraine" to "Throne of Eldraine",
        "Guerras de la Chispa" to "War of the Spark",
        "Ikoria: Nido del Behemot" to "Ikoria: Lair of Behemoths",
        "M21" to "Core Set 2021",
        "Kaldheim" to "Kaldheim",
        "Strixhaven: Academia de Magos" to "Strixhaven: School of Mages",
        "Aventuras en el Olvido" to "Adventures in the Forgotten Realms"
    )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Otras inicializaciones

        val setData: MutableList<SetItem> = mutableListOf(
            SetItem("Alfa", R.drawable.tempestad),
            SetItem("Beta", R.drawable.tempestad),
            SetItem("Unlimited", R.drawable.tempestad),
            SetItem("Revisada", R.drawable.tempestad),
            SetItem("Legends", R.drawable.tempestad),
            SetItem("La Oscuridad", R.drawable.tempestad),
            SetItem("Imperios Caídos", R.drawable.tempestad),
            SetItem("Era Glacial", R.drawable.era_glacial),
            SetItem("Territorios Natales", R.drawable.tempestad),
            SetItem("Espejismo", R.drawable.tempestad),
            SetItem("Tempestad", R.drawable.tempestad),
            SetItem("Fortaleza", R.drawable.fortaleza),
            SetItem("Éxodo", R.drawable.tempestad),
            SetItem("Saga de Urza", R.drawable.saga_de_urza),
            SetItem("Legado de Urza", R.drawable.tempestad),
            SetItem("Destino de Urza", R.drawable.tempestad),
            SetItem("Mascarada de Mercadia", R.drawable.tempestad),
            SetItem("Némesis", R.drawable.tempestad),
            SetItem("Profecía", R.drawable.tempestad),
            SetItem("Invasión", R.drawable.tempestad),
            SetItem("Desplazamiento de Planos", R.drawable.tempestad),
            SetItem("Apocalipsis", R.drawable.tempestad),
            SetItem("Odisea", R.drawable.odisea),
            SetItem("Tormenta", R.drawable.tempestad),
            SetItem("Juicio", R.drawable.juicio),
            SetItem("Asedio", R.drawable.tempestad),
            SetItem("Legiones", R.drawable.tempestad),
            SetItem("Azote", R.drawable.azote),
            SetItem("Mirrodin", R.drawable.mirrodin),
            SetItem("Darksteel", R.drawable.tempestad),
            SetItem("Quinto Amanecer", R.drawable.quinto_amanecer),
            SetItem("Campeones de Kamigawa", R.drawable.tempestad),
            SetItem("Traidores de Kamigawa", R.drawable.tempestad),
            SetItem("Salvadores de Kamigawa", R.drawable.tempestad),
            SetItem("Ravnica: Ciudad de Gremios", R.drawable.tempestad),
            SetItem("Pacto  Gremios", R.drawable.tempestad),
            SetItem("Discordia", R.drawable.discordia),
            SetItem("Era Glacial", R.drawable.era_glacial),
            SetItem("Caída de la Noche", R.drawable.tempestad),
            SetItem("Renacimiento", R.drawable.tempestad),
            SetItem("Eventide", R.drawable.tempestad),
            SetItem("Fragmentos de Alara", R.drawable.tempestad),
            SetItem("Conflux", R.drawable.tempestad),
            SetItem("Alara Renacida", R.drawable.tempestad),
            SetItem("Magic 2010", R.drawable.tempestad),
            SetItem("Zendikar", R.drawable.tempestad),
            SetItem("Despertar del Mundo", R.drawable.tempestad),
            SetItem("Ascenso de los Eldrazi", R.drawable.tempestad),
            SetItem("Magic 2011", R.drawable.tempestad),
            SetItem("Escarsa de Mirrodin", R.drawable.tempestad),
            SetItem("Mirrodin sitiado", R.drawable.tempestad),
            SetItem("Nueva Frixia", R.drawable.tempestad),
            SetItem("Magic 2012", R.drawable.tempestad),
            SetItem("Innistrad", R.drawable.tempestad),
            SetItem("Ascenso tenebroso", R.drawable.tempestad),
            SetItem("Restauración de Avacyn", R.drawable.tempestad),
            SetItem("Magic 2013", R.drawable.tempestad),
            SetItem("Regreso a Ravnica", R.drawable.tempestad),
            SetItem("Puertas de Gremios", R.drawable.tempestad),
            SetItem("Laberinto del Dragón", R.drawable.tempestad),
            SetItem("Magic 2014", R.drawable.tempestad),
            SetItem("Theros", R.drawable.tempestad),
            SetItem("Nacidos de los dioses", R.drawable.tempestad),
            SetItem("Viaje a Nyx", R.drawable.tempestad),
            SetItem("Magic 2015", R.drawable.tempestad),
            SetItem("Khans de Tarkir", R.drawable.tempestad),
        )


        val adapter = AdapterSets(requireContext(), this)
        adapter.updateData(setData)

        binding.rvSetsCards.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSetsCards.adapter = adapter

        // Configurar el SearchView
        binding.svSetsCards.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filterData(newText ?: "")
                return true
            }
        })

    }

    override fun onItemClick(setName: String) {
        //¿Que hago con el nombre?
        Log.d("TuFragmento", "Elemento clicado: $setName")
        //Lo paso a ingles para mandarlo a la api que solo reconoce ingles
        val nameSetEng = mapSetsEspToEng.get(setName)
        //Si lo tengo en el map ok
        if(nameSetEng.isNullOrEmpty()){
            showError("No existe esa expansion")
        }else{
            goToSetCardsFragment(nameSetEng)
        }
    }
    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun goToSetCardsFragment(setNameEng: String) {
        val bundle = Bundle().apply {
            putString("setNameEng", setNameEng)
        }

        val fragmentB = SetCardsFragment()
        fragmentB.arguments = bundle

        findNavController().navigate(R.id.action_setsFragment_to_setCardsFragment, bundle)
    }

}