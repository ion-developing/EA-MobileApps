package com.example.parcial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R

class Adapter(val listaPost:List<tablePost>): RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val layoutInflater=LayoutInflater.from(parent.context)
        return UserViewHolder(layoutInflater.inflate(R.layout.item,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item=listaPost[position]
        holder.render(item)
    }

    override fun getItemCount(): Int =listaPost.size
}