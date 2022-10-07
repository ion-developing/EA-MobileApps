package com.example.parcial

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R

class UserViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val id=view.findViewById<TextView>(R.id.txtBDId)
    val name=view.findViewById<TextView>(R.id.txtBDName)
    val title=view.findViewById<TextView>(R.id.txtBDImg)

    fun render(posModel:tablePost){
        id.text=posModel.id.toString()
        name.text=posModel.name.toString()
        title.text=posModel.img.toString()
    }

}