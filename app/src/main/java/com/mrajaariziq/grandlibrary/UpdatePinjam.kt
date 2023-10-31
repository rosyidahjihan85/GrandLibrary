package com.mrajaariziq.grandlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.RoomDB.DataPinjam
import com.mrajaariziq.grandlibrary.databinding.ActivityUpdatePinjamBinding

class UpdatePinjam : AppCompatActivity() {

    private lateinit var binding : ActivityUpdatePinjamBinding
    private val db by lazy { DBLibrary.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePinjamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("nisPinjam").toString().toInt()
        val data =db.librarydao().getAllPinjam(id)

        binding.UpdNmPin.setText(data[0].nisPinjam)
        binding.UpdJdlPin.setText(data[0].judul)
        binding.UpdTglPin.setText(data[0].tglPinjam.toString())
        binding.UpdBatasWkt.setText(data[0].bataswaktu.toString())
        binding.btnUpPin.setOnClickListener {
        if (binding.UpdNmPin.text.isNotEmpty()&&
                binding.UpdJdlPin.text.isNotEmpty()&&
                binding.UpdTglPin.text.isNotEmpty()&&
                binding.UpdBatasWkt.text.isNotEmpty()){

            db.librarydao().updateDataPinjam(DataPinjam(id,
            binding.UpdNmPin.text.toString(),
            binding.UpdJdlPin.text.toString(),
            binding.UpdTglPin.text.toString().toInt(),
            binding.UpdBatasWkt.text.toString().toInt()))

            Toast.makeText(applicationContext,"Data berhasil di ubah",
            Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java)
            )
            onBackPressed()
        }else{
            Toast.makeText(applicationContext,"Ubah data terlebih dahulu",
            Toast.LENGTH_SHORT).show()
        }
        }
    }
}