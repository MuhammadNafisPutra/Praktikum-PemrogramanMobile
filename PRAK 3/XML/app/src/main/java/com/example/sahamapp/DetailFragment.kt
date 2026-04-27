package com.example.sahamapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sahamapp.data.getStockList
import com.example.sahamapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stockIndex = arguments?.getInt("stockIndex") ?: 0
        val stockList = getStockList()
        val stock = stockList.getOrNull(stockIndex) ?: return
        binding.ivDetailImage.setImageResource(stock.imageRes)
        binding.tvDetailName.text = getString(stock.nameRes)
        binding.tvDetailYear.text = getString(stock.yearRes)
        binding.tvDetailDesc.text = getString(stock.descRes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}