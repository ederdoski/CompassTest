package com.edominguez.compasstest.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edominguez.compasstest.R
import com.edominguez.compasstest.model.WordCounter

class WordCounterAdapter(private val context: Context, private val items: List<WordCounter>) : RecyclerView.Adapter<WordCounterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.words_view_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.wordTextView.text = context.getString(R.string.txt_word, item.word)
        holder.wordQuantityTextView.text = context.getString(R.string.txt_word_counter, item.quantity)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordTextView: TextView = itemView.findViewById(R.id.tvWord)
        val wordQuantityTextView: TextView = itemView.findViewById(R.id.tvQuantity)
    }
}