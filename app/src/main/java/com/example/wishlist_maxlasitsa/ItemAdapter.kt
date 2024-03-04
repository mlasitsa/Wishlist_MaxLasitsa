package com.example.wishlist_maxlasitsa

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist_maxlasitsa.R

class ItemAdapter(private val items: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewName)
        val textViewPrice: TextView = view.findViewById(R.id.textViewPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textViewName.text = item.name
        holder.textViewPrice.text = item.price.toString()
        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                ContextCompat.startActivity(holder.itemView.context, browserIntent, null)
            } catch (e: Exception) {
                Toast.makeText(holder.itemView.context, "Invalid URL for " + item.name, Toast.LENGTH_LONG).show()
            }
        }
        holder.itemView.setOnLongClickListener {
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            true
        }
    }

    override fun getItemCount() = items.size

    fun addItem(item: Item) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}