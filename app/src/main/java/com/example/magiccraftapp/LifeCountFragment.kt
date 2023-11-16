package com.example.magiccraftapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.magiccraftapp.databinding.FragmentLifeCountBinding

class LifeCountFragment : Fragment() {

    private var _binding: FragmentLifeCountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLifeCountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Empieza la crema

        setupButtons()

    }

    private fun setupButtons() {

        binding.btnPlusLifePlayer1.setOnClickListener {
            var oldValue = binding.tvLifePlayer1.text.toString().toInt()
            oldValue += 1
            binding.tvLifePlayer1.text = oldValue.toString()
        }
        binding.btnMinusLifePlayer1.setOnClickListener {
            var oldValue = binding.tvLifePlayer1.text.toString().toInt()
            oldValue -= 1
            binding.tvLifePlayer1.text = oldValue.toString()
        }
        binding.btnPlusLifePlayer2.setOnClickListener {
            var oldValue = binding.tvLifePlayer1.text.toString().toInt()
            oldValue += 1
            binding.tvLifePlayer1.text = oldValue.toString()
        }
        binding.btnMinusLifePlayer2.setOnClickListener {
            var oldValue = binding.tvLifePlayer1.text.toString().toInt()
            oldValue -= 1
            binding.tvLifePlayer1.text = oldValue.toString()
        }

    }
}