package com.mrajaariziq.grandlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.RoomDB.DataPinjam
import com.mrajaariziq.grandlibrary.databinding.ActivityInputDatapinjamBinding

class InputDatapinjamActivity : AppCompatActivity() {
    private val db by lazy { DBLibrary.getInstance(this) }
    private lateinit var binding: ActivityInputDatapinjamBinding
    private lateinit var database : DBLibrary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputDatapinjamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= DBLibrary.getInstance(applicationContext)
        binding.SimpinputPin.setOnClickListener {
            if (binding.nmPin.text.isNotEmpty() &&
                binding.judulPin.text.isNotEmpty() &&
                binding.tglPin.text.isNotEmpty() &&
                binding.batasWkt.text.isNotEmpty()){
                db.librarydao().insertDataPinjam(
                    DataPinjam(
                    0,binding.nmPin.text.toString(),
                        binding.judulPin.text.toString(),
                        binding.tglPin.text.toString().toInt(),
                        binding.batasWkt.text.toString().toInt()
                    )
                )
                binding.nmPin.setText("")
                binding.judulPin.setText("")
                binding.tglPin.setText("")
                binding.batasWkt.setText("")
                startActivity(
                    Intent(this, LoanData::class.java))
            }else{
                Toast.makeText(applicationContext,
                    "silahkan isi data terlebih dahulu",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}