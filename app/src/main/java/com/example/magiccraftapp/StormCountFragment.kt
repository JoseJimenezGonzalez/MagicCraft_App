package com.example.magiccraftapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.magiccraftapp.databinding.FragmentLifeCountBinding
import com.example.magiccraftapp.databinding.FragmentStormCountBinding

class StormCountFragment : Fragment() {

    private var _binding: FragmentStormCountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStormCountBinding.inflate(inflater, container, false)
        return binding.root
    }

}