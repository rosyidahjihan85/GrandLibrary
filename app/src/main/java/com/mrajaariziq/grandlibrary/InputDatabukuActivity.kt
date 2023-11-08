package com.mrajaariziq.grandlibrary

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.RoomDB.DataBuku
import com.mrajaariziq.grandlibrary.databinding.ActivityBookDataBinding
import com.mrajaariziq.grandlibrary.databinding.ActivityInputDatabukuBinding
import java.text.SimpleDateFormat
import java.util.*

class InputDatabukuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputDatabukuBinding
    private lateinit var database: DBLibrary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputDatabukuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DBLibrary.getInstance(applicationContext)
        binding.savedatabuku.setOnClickListener {
            if (binding.idbukuinput.text.isNotEmpty()&&
                binding.judulinput.text.isNotEmpty() &&
                binding.pengaranginput.text.isNotEmpty() &&
                binding.penerbitinput.text.isNotEmpty()

            ) {
                database.librarydao().insertDataBuku(
                    DataBuku(
                        binding.idbukuinput.text.toString().toInt(),
                        binding.judulinput.text.toString(),
                        binding.pengaranginput.text.toString(),
                        binding.penerbitinput.text.toString()

                    )
                )
                binding.idbukuinput.setText("")
                binding.judulinput.setText("")
                binding.pengaranginput.setText("")
                binding.penerbitinput.setText("")

                startActivity(
                    Intent(this, BookData::class.java)
                )
            } else {
                Toast.makeText(
                    applicationContext,
                    "Silahkan isi semua data terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show() //tes
            }
        }
    }
}


