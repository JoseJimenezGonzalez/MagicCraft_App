package com.example.magiccraftapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.magiccraftapp.databinding.FragmentToolsBinding

class ToolsFragment : Fragment() {

    private var _binding: FragmentToolsBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el diseño usando View Binding
        _binding = FragmentToolsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

    }

    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {
        // Utiliza childFragmentManager en lugar de supportFragmentManager
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment_tools) as NavHostFragment
        val navController = navHostFragment.navController

        // Utiliza viewLifecycleOwner al configurar la navegación
        binding.bottomNavigationViewTools.setupWithNavController(navController)
    }

}





