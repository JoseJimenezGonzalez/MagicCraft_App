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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Empieza la crema

        setupButtons()

    }

    private fun setupButtons() {
        //Pantano
        binding.btnPlusSwamp.setOnClickListener {
            var oldValue = binding.tvManaSwamp.text.toString().toInt()
            oldValue += 1
            binding.tvManaSwamp.text = oldValue.toString()
        }

        binding.btnMinusSwamp.setOnClickListener {
            var oldValue = binding.tvManaSwamp.text.toString().toInt()
            oldValue -= 1
            binding.tvManaSwamp.text = oldValue.toString()
        }
        //Montaña
        binding.btnPlusMountain.setOnClickListener {
            var oldValue = binding.tvManaMountain.text.toString().toInt()
            oldValue += 1
            binding.tvManaMountain.text = oldValue.toString()
        }

        binding.btnMinusMountain.setOnClickListener {
            var oldValue = binding.tvManaMountain.text.toString().toInt()
            oldValue -= 1
            binding.tvManaMountain.text = oldValue.toString()
        }
        //Forest

        binding.btnPlusForest.setOnClickListener {
            var oldValue = binding.tvManaForest.text.toString().toInt()
            oldValue += 1
            binding.tvManaForest.text = oldValue.toString()
        }

        binding.btnMinusForest.setOnClickListener {
            var oldValue = binding.tvManaForest.text.toString().toInt()
            oldValue -= 1
            binding.tvManaForest.text = oldValue.toString()
        }
        //Isla
        binding.btnPlusIsland.setOnClickListener {
            var oldValue = binding.tvManaIsland.text.toString().toInt()
            oldValue += 1
            binding.tvManaIsland.text = oldValue.toString()
        }

        binding.btnMinusIsland.setOnClickListener {
            var oldValue = binding.tvManaIsland.text.toString().toInt()
            oldValue -= 1
            binding.tvManaIsland.text = oldValue.toString()
        }
        //Llanura
        binding.btnPlusPlains.setOnClickListener {
            var oldValue = binding.tvManaPlains.text.toString().toInt()
            oldValue += 1
            binding.tvManaPlains.text = oldValue.toString()
        }

        binding.btnMinusPlains.setOnClickListener {
            var oldValue = binding.tvManaPlains.text.toString().toInt()
            oldValue -= 1
            binding.tvManaPlains.text = oldValue.toString()
        }
        //Stomr
        binding.btnPlusStorm.setOnClickListener {
            var oldValue = binding.tvManaStorm.text.toString().toInt()
            oldValue += 1
            binding.tvManaStorm.text = oldValue.toString()
        }

        binding.btnMinusStorm.setOnClickListener {
            var oldValue = binding.tvManaStorm.text.toString().toInt()
            oldValue -= 1
            binding.tvManaStorm.text = oldValue.toString()
        }
    }

}