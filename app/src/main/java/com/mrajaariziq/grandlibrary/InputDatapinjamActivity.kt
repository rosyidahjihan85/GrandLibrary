package com.mrajaariziq.grandlibrary

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.RoomDB.DataPinjam
import com.mrajaariziq.grandlibrary.databinding.ActivityInputDatapinjamBinding
import java.text.SimpleDateFormat
import java.util.*

class InputDatapinjamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputDatapinjamBinding
    private lateinit var database : DBLibrary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputDatapinjamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var id = intent.getStringExtra("iddatabuku")

        if (id == null){
        }else{
            (id.toString().toInt())
        }

        val data = arrayOf("legenda", "sejarahNgawi", "MazPaizlgibeligorengan",
            "NgawiMfire","sejarhawalmulaEpep","asalusulNgawi","awalMulaMazPaizsukagorengan",
            "Si Hitam Dari Jawir","Kamus Lengkap Plyer ML","Agus Lapar Bu","Si Hitam dari Selat Sunda")
        val spinner = binding.judulPin


        database= DBLibrary.getInstance(applicationContext)
        binding.SimpinputPin.setOnClickListener {
            if (binding.nmPin.text.isNotEmpty() &&
                binding.judulPin.text.isNotEmpty() &&
                binding.tglPin.text.isNotEmpty() &&
                binding.batasWkt.text.isNotEmpty()
            ){

                database.librarydao().insertDataPinjam(
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

        binding.backPin.setOnClickListener {
            startActivity(Intent(this, LoanData::class.java))
        }
        this.setTanggalRegister()
        this.setbataswaktu()
    }
    private fun setTanggalRegister(){
        this.setTanggal()
        binding.tglPin.setOnClickListener {
            var cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener{ picker, tahun, bulan, tanggal ->
                    binding.tglPin.setText("" + tanggal + "-" + bulan + "-" + tahun)
                }, year, month, day

            )
            datePickerDialog.show()
        }
    }
    private fun setTanggal(){
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("d-M-yyyy")
        val dateTime = simpleDateFormat.format(calendar.time)
        binding.tglPin.setText(dateTime)
    }
    private fun setbataswaktu(){
        this.setTglpngembalian()
        binding.batasWkt.setOnClickListener {
            var cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener{ picker, tahun, bulan, tanggal ->
                    binding.batasWkt.setText("" + tanggal + "-" + bulan + "-" + tahun)
                }, year, month, day

            )
            datePickerDialog.show()
        }
    }
    private fun setTglpngembalian(){
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("d-M-yyyy")
        val dateTime = simpleDateFormat.format(calendar.time)
        binding.batasWkt.setText(dateTime)
    }
}