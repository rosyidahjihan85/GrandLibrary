package com.mrajaariziq.grandlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Beranda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)
    }
    fun ini (view: View){
        val buku = Intent (this, BookData::class.java)
        startActivity(buku)
    }
    fun itu (view: View){
        val Pinjam = Intent (this, LoanData::class.java)
        startActivity(Pinjam)
    }
}