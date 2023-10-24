package com.mrajaariziq.grandlibrary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrajaariziq.grandlibrary.R
import com.mrajaariziq.grandlibrary.RoomDB.DataBuku

class Adapterdatabuku(val list: ArrayList<DataBuku>, var listener:OnAdapterListener) :

    RecyclerView.Adapter<Adapterdatabuku.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var judul = itemView.findViewById<TextView>(R.id.jdlbuku)
        var edit = itemView.findViewById<ImageView>(R.id.imgedit)
        var hapus = itemView.findViewById<ImageView>(R.id.imghps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapterdatabuku, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.judul.text = list[position].judulBk


        //step 4 membuat holder
        holder.hapus.setOnClickListener{
            listener.ondelete(list[position])
        }
        holder.edit.setOnClickListener{
            listener.onedit(list[position])
        }

    }

    fun setData(newList: List<DataBuku>){
        list.clear()
        list.addAll(newList)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    //step 2 membuat interface untuk bisa diperintah

    interface OnAdapterListener{
        fun ondelete(dataBuku: DataBuku)
        fun onedit(dataBuku: DataBuku)

    }
}


