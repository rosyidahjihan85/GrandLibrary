package com.mrajaariziq.grandlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.databinding.ActivityDetailPinjamBinding

class DetailPinjamActivity : AppCompatActivity() {

    private val db by lazy { DBLibrary.getInstance(this) }
    private lateinit var fine : ActivityDetailPinjamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        fine = ActivityDetailPinjamBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(fine.root)

        val nis = intent.getStringExtra("nispinjam").toString().toInt()
        val data = db.librarydao().getnis(nis)[0]
        fine.namapinjamadapter.setText (data.namaPinjam)
        fine.judulbukupinjamadapter.setText(data.judul)
        fine.tanggalpinjamadapter.setText(data.tglPinjam)
        fine.bataswaktupinjamadapter.setText(data.bataswaktu)
        fine.imageView13.setImageResource(R.drawable.peminjaaam_1)

    }
}