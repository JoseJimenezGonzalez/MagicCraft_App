package com.example.magiccraftapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.magiccraftapp.databinding.ItemCardBinding
import com.example.magiccraftapp.model.Card
import com.squareup.picasso.Picasso

class AdapterSearchCards(private val context: Context) : RecyclerView.Adapter<AdapterSearchCards.ViewHolder>() {

    private var dataSet: MutableList<Card> = mutableListOf()

    inner class ViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            //Foto
            Picasso.get().load(card.image).into(binding.ivCard)
            //Nombre
            binding.tvCardName.text = card.name.capitalize()
            //Edicion
            binding.tvCardEdition.text = card.edition.capitalize()
            //Rareza
            binding.tvCardRarity.text = card.rarity.capitalize()
            //Tipo
            binding.tvCardType.text = card.type.capitalize()
            //Reserved
            binding.tvCardReserved.text = card.reserved.capitalize()
            //Precio
            binding.tvCardPrice.text = card.price
            //Texto
            binding.tvCardText.text = card.text.capitalize()
            //Coste de mana
            binding.tvCardManaCost.text = card.manaCost
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = ItemCardBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val card = dataSet[position]
        viewHolder.bind(card)
    }

    fun updateData(newData: MutableList<Card>) {
        dataSet.clear()
        dataSet.addAll(newData)
        notifyDataSetChanged()
    }
}

