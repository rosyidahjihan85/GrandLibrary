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

        val nis = intent.getStringExtra("nispinjam").toString().toInt()
        val data =db.librarydao().getnis(nis)

        binding.updNmPin.setText(data[0].namaPinjam)
        binding.UpdJdlPin.setText(data[0].judul)
        binding.UpdTglPin.setText(data[0].tglPinjam)
        binding.UpdBatasWkt.setText(data[0].bataswaktu)
        binding.btnUpPin.setOnClickListener {
        if (
            binding.updNisPin.text.isNotEmpty()&&
            binding.updNmPin.text.isNotEmpty()&&
                binding.UpdJdlPin.text.isNotEmpty()&&
                binding.UpdTglPin.text.isNotEmpty()&&
                binding.UpdBatasWkt.text.isNotEmpty()){

            db.librarydao().updateDataPinjam(DataPinjam(nis,
            binding.updNmPin.text.toString(),
            binding.UpdJdlPin.text.toString(),
            binding.UpdTglPin.text.toString(),
            binding.UpdBatasWkt.text.toString()))

            Toast.makeText(applicationContext,"Data berhasil di ubah",
            Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,LoanData::class.java)
            )
            onBackPressed()
        }else{
            Toast.makeText(applicationContext,"Ubah data terlebih dahulu",
            Toast.LENGTH_SHORT).show()
        }
        }
        binding.backUpPIN.setOnClickListener {
            startActivity(Intent(this, LoanData::class.java))
        }
    }
}