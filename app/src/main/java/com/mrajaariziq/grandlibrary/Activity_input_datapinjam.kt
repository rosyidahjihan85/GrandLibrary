package com.mrajaariziq.grandlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.RoomDB.DataPinjam
import com.mrajaariziq.grandlibrary.databinding.ActivityInputDatapinjamBinding

class Activity_input_datapinjam : AppCompatActivity() {
    private val db by lazy { DBLibrary.getInstance(this) }
    private lateinit var binding: ActivityInputDatapinjamBinding
    private lateinit var database : DBLibrary
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDatapinjamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= DBLibrary.getInstance(applicationContext)
        binding.btnsimpndta.setOnClickListener {
            if (binding.editnmapmnjm.text.isNotEmpty() &&
                binding.editTextjdulbuku.text.isNotEmpty() &&
                binding.editTexttglpinjm.text.isNotEmpty() &&
                binding.edittglpengembalian.text.isNotEmpty()){
                db.librarydao().insertDataPinjam(DataPinjam(
                    0,binding.editnmapmnjm.text.toString(),
                    binding.editTextjdulbuku.text.toString(),
                    binding.editTexttglpinjm.text.toString().toInt(),
                        binding.edittglpengembalian.text.toString().toInt())
                )
                binding.editnmapmnjm.setText("")
                binding.editTextjdulbuku.setText("")
                binding.editTexttglpinjm.setText("")
                binding.edittglpengembalian.setText("")
                startActivity(
                    Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(applicationContext,
                    "silahkan isi data terlebih dahulu",
                    Toast.LENGTH_SHORT).show()

            }

        }

    }

}