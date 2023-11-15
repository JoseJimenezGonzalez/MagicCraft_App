package com.example.magiccraftapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.example.magiccraftapp.databinding.FragmentToolsBinding


class ToolsFragment : Fragment() {

    private var _binding: FragmentToolsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout y obtener la referencia al View Binding
        _binding = FragmentToolsBinding.inflate(inflater, container, false)
        val view = binding.root

        // Configurar el botón para abrir el menú lateral
        binding.ibMenuBurguer.setOnClickListener {

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
