package com.example.sahamapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sahamapp.data.Stock
import com.example.sahamapp.databinding.ItemStockBinding

class StockAdapter(
    private val stockList: List<Stock>,
    private val onDetailClick: (Int) -> Unit
) : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    inner class StockViewHolder(private val binding: ItemStockBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(stock: Stock, position: Int) {
            val context = binding.root.context

            binding.ivStockImage.setImageResource(stock.imageRes)
            binding.tvStockName.text = context.getString(stock.nameRes)
            binding.tvStockYear.text = context.getString(R.string.label_year) + context.getString(stock.yearRes)
            binding.tvStockDesc.text = context.getString(stock.descRes)

            binding.btnWeb.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(stock.webLink))
                context.startActivity(intent)
            }

            binding.btnDetail.setOnClickListener {
                onDetailClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val binding = ItemStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(stockList[position], position)
    }

    override fun getItemCount(): Int = stockList.size
}