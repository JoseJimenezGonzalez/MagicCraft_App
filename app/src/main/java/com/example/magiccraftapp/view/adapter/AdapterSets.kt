package com.example.magiccraftapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.magiccraftapp.databinding.ItemSetBinding
import com.example.magiccraftapp.model.SetItem
import java.util.Locale

class AdapterSets(private val context: Context, private val itemClickListener: OnSetClickListener) :
    RecyclerView.Adapter<AdapterSets.ViewHolder>() {

    private var dataSet: MutableList<SetItem> = mutableListOf()
    private var dataSetFiltered: MutableList<SetItem> = mutableListOf()

    inner class ViewHolder(private val binding: ItemSetBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(setItem: SetItem) {
            binding.ivSet.setImageResource(setItem.setImageResource)
            binding.tvSet.text = setItem.setName
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemClickListener.onItemClick(dataSetFiltered[position].setName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemSetBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSetFiltered.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val setItem = dataSetFiltered[position]
        viewHolder.bind(setItem)
    }

    fun updateData(newData: MutableList<SetItem>) {
        dataSet.clear()
        dataSet.addAll(newData)
        filterData("")  // Restablecer el filtro al actualizar los datos
        notifyDataSetChanged()
    }

    fun filterData(query: String) {
        dataSetFiltered.clear()

        if (query.isEmpty()) {
            dataSetFiltered.addAll(dataSet)
        } else {
            val lowerCaseQuery = query.toLowerCase(Locale.getDefault())
            for (item in dataSet) {
                if (item.setName.toLowerCase(Locale.getDefault()).contains(lowerCaseQuery)) {
                    dataSetFiltered.add(item)
                }
            }
        }

        notifyDataSetChanged()
    }

    // Interfaz para manejar clics en el adaptador
    interface OnSetClickListener {
        fun onItemClick(itemName: String)
    }
}


