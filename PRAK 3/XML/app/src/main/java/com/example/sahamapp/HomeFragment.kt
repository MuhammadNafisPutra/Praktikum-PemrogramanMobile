package com.example.sahamapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sahamapp.data.getStockList
import com.example.sahamapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val isFirstTime = sharedPref.getBoolean("isFirstTime", true)
        if (isFirstTime) {
            Toast.makeText(requireContext(), getString(R.string.first_time_msg), Toast.LENGTH_LONG).show()
            sharedPref.edit().putBoolean("isFirstTime", false).apply()
        }

        val stockList = getStockList()

        val adapter = StockAdapter(stockList) { index ->
            val bundle = Bundle().apply { putInt("stockIndex", index) }
            findNavController().navigate(R.id.detailFragment, bundle)
        }

        binding.rvHorizontal.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHorizontal.adapter = adapter
        binding.rvVertical.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVertical.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}