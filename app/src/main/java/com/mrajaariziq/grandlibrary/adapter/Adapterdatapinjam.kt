package com.mrajaariziq.grandlibrary.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrajaariziq.grandlibrary.DetailPinjamActivity
import com.mrajaariziq.grandlibrary.R
import com.mrajaariziq.grandlibrary.RoomDB.DataPinjam
import com.mrajaariziq.grandlibrary.UpdatePinjam

class Adapterdatapinjam(val list: ArrayList<DataPinjam>, var listener :OnAdapterLinstener)
    :RecyclerView.Adapter<Adapterdatapinjam.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val NAMAPENGGUNA = itemView.findViewById<TextView>(R.id.namapengguna)
        val HAPUS = itemView.findViewById<ImageView>(R.id.btnhapusadapterpinjam)
        val EDIT = itemView.findViewById<ImageView>(R.id.btneditadapterpinjam)
        val detail = itemView.findViewById<ImageView>(R.id.imgbook2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pinjam_adapter, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.NAMAPENGGUNA.text = list[position].namaPinjam
        holder.HAPUS.setOnClickListener {
            listener.onhapus(list[position])
        }
        holder.EDIT.setOnClickListener {
            listener.onedit(list[position])
        }
        holder.detail.setOnClickListener {
            val context = holder.itemView.context
            val intent= Intent(context, DetailPinjamActivity::class.java).putExtra("nispinjam", list[position].nisPinjam.toString())
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    fun setdata(newList: List<DataPinjam>){
        list.clear()
        list.addAll(newList)
    }
    interface OnAdapterLinstener {
        fun onhapus(dataPinjam: DataPinjam)
        fun onedit(dataPinjam: DataPinjam)

    }
}