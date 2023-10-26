package com.mrajaariziq.grandlibrary

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.RoomDB.DataBuku
import com.mrajaariziq.grandlibrary.databinding.ActivityBookDataBinding
import com.mrajaariziq.grandlibrary.databinding.ActivityInputDatabukuBinding
import java.text.SimpleDateFormat

class InputDatabukuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputDatabukuBinding
    private lateinit var database: DBLibrary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputDatabukuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DBLibrary.getinstance(applicationContext)
        binding.savedatabuku.setOnClickListener {
            if (binding.idbukuinput.text.isNotEmpty() &&
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
                    Intent(this, MainActivity::class.java)
                )
            } else {
                Toast.makeText(
                    applicationContext,
                    "Silahkan isi semua data terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        this.settglregis()
    }
    private fun settglregis(){
        this.settgl()
        binding.inputtgl.setOnClickListener{
            var call = Calendar.getInstance()
            var year = call.get(Calendar.YEAR)
            var month = call.get(Calendar.MONTH)
            var day = call.get(Calendar. DAY_OF_MONTH)
            var datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener{picker, tahun, bulan, tanggal ->
                    binding.inputtgl.setText("" + tanggal + "-" + tahun)
                }, year, month, day
            )
            datePickerDialog.show()
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun settgl(){
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat ("d-m-yyyy")
        val datetime = simpleDateFormat.format(calendar.time)
        binding.inputtgl.setText(datetime)
    }
}