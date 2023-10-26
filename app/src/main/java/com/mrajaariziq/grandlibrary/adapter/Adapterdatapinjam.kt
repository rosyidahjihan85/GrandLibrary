package com.mrajaariziq.grandlibrary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrajaariziq.grandlibrary.R
import com.mrajaariziq.grandlibrary.RoomDB.DataBuku
import com.mrajaariziq.grandlibrary.RoomDB.DataPinjam

class Adapterdatapinjam(val list: ArrayList<DataPinjam>, var listener : Any)
    :RecyclerView.Adapter<Adapterdatapinjam.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val NAMA = itemView.findViewById<TextView>(R.id.namapinjamadapter)
        val JUDULBUKU = itemView.findViewById<TextView>(R.id.judulbukuadapter)
        val TANGGAL = itemView.findViewById<TextView>(R.id.tanggalpinjamadapter)
        val BATASWAKTU = itemView.findViewById<TextView>(R.id.bataswaktupinjamadapter)
        val HAPUS = itemView.findViewById<ImageView>(R.id.btnhapusadapterpinjam)
        val EDIT = itemView.findViewById<ImageView>(R.id.btneditadapterpinjam)

    }

    interface Any {
        fun ondelete(dataPinjam: DataPinjam)
        fun onedit(dataPinjam: DataPinjam)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.detail_adapterpinjam, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.NAMA.text = list[position].namaPinjam
        holder.JUDULBUKU.text = list[position].judul
        holder.TANGGAL.text = list[position].tglPinjam.toString()
        holder.BATASWAKTU.text = list[position].bataswaktu.toString()
        holder.HAPUS.setOnClickListener {
            listener.ondelete(list[position])
        }
        holder.EDIT.setOnClickListener {
            listener.onedit(list[position])
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun setdata(newList: List<DataPinjam>){
        list.clear()
        list.addAll(newList)
    }
}