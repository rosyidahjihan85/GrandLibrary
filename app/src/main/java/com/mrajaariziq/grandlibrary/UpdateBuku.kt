package com.mrajaariziq.grandlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.RoomDB.DataBuku
import com.mrajaariziq.grandlibrary.adapter.Adapterdatabuku
import com.mrajaariziq.grandlibrary.databinding.ActivityInputDatabukuBinding
import com.mrajaariziq.grandlibrary.databinding.ActivityUpdateBukuBinding

class UpdateBuku : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBukuBinding
    private val db by lazy { DBLibrary.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("idbuku").toString().toInt()
        val dataBuku = db.librarydao().getid(id)

        binding.judulupdate.setText(dataBuku[0].judulBk)
        binding.pengarangupdate.setText(dataBuku[0].pengarangBuku)
        binding.penerbitupdate.setText(dataBuku[0].penerbitBuku)
        binding.savedatabuku.setOnClickListener {
            if (binding.idbukuupdate.text.isNotEmpty() &&
                binding.judulupdate.text.isNotEmpty() &&
                binding.pengarangupdate.text.isNotEmpty() &&
                binding.penerbitupdate.text.isNotEmpty()
            ) {

                db.librarydao().updateDataBuku(
                    DataBuku(
                        id,
                        binding.judulupdate.text.toString(),
                        binding.pengarangupdate.text.toString(),
                        binding.penerbitupdate.text.toString()
                    )
                )

                Toast.makeText(
                    applicationContext, "Data berhasil diubah",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, BookData::class.java))
                onBackPressed()

            } else {
                Toast.makeText(applicationContext, "Ubah data terlebih dahulu",
                Toast.LENGTH_SHORT).show()
            }
        }
        binding.backUpBK.setOnClickListener {
            startActivity(Intent(this, BookData::class.java))
        }
    }
}