package com.mrajaariziq.grandlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.databinding.ActivityDetailBukuBinding

class DetailBukuActivity : AppCompatActivity() {
    private val db by lazy { DBLibrary.getInstance(this) }
    private lateinit var finding : ActivityDetailBukuBinding


override fun onCreate(savedInstanceState: Bundle?) {
    finding = ActivityDetailBukuBinding.inflate(layoutInflater)
    super.onCreate(savedInstanceState)
        setContentView(finding.root)

    finding.imgbook.setOnClickListener{onBackPressed()}

    val id = intent.getStringExtra("idbuku").toString().toInt()

    val data = db.librarydao().getid(id)[0]
    finding.judulbukuadapter.setText(data.judulBk)
    finding.pengarangadapter.setText(data.pengarangBuku)
    finding.penerbit.setText(data.penerbitBuku)

    }
}