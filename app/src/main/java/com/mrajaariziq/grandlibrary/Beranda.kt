package com.mrajaariziq.grandlibrary

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mrajaariziq.grandlibrary.databinding.ActivityBerandaBinding

class Beranda : AppCompatActivity() {
    private lateinit var binding: ActivityBerandaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBerandaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("Grand",Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username","")

        binding.usernma.text= username

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