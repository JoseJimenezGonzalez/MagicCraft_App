package com.example.magiccraftapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.magiccraftapp.databinding.FragmentToolsBinding

class ToolsFragment : Fragment() {

    private var _binding: FragmentToolsBinding? = null
    private val binding get() = _binding!!

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout y obtener la referencia al View Binding
        _binding = FragmentToolsBinding.inflate(inflater, container, false)
        val view = binding.root

        // Configurar el botón para abrir el menú lateral
        binding.ivToolss.setOnClickListener {
            // Obtener el DrawerLayout desde la actividad
            val drawerLayout = requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
            // Abrir el menú lateral
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
