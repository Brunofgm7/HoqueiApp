package com.example.apphoquei

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_jogo.view.*

class JogosAdapter(private val exampleList: List<JogoItem>) : RecyclerView.Adapter<JogosAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_jogo, parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun getItemCount(): Int = exampleList.size

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.textViewVisitado.text = currentItem.textViewVisitado
        holder.textViewVisitante.text = currentItem.textViewVisitante
        holder.textViewResultado.text = currentItem.textViewResultado
    }


    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewVisitado: TextView = itemView.textViewVisitado
        val textViewVisitante: TextView = itemView.textViewVisitante
        val textViewResultado: TextView = itemView.textViewResultado

    }

}