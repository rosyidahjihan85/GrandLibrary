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
    fun databuku (view: View){
        val bookdata = Intent (this, BookData::class.java)
        startActivity(bookdata)
    }
    fun datapinjam (view: View){
        val datapinjam = Intent (this, LoanData::class.java)
        startActivity(datapinjam)
    }
}