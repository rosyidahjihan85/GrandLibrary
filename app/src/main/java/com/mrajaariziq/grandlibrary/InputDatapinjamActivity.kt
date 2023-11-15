package com.mrajaariziq.grandlibrary

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.RoomDB.DataPinjam
import com.mrajaariziq.grandlibrary.databinding.ActivityInputDatapinjamBinding
import java.text.SimpleDateFormat
import java.util.*

class InputDatapinjamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputDatapinjamBinding
    private lateinit var database: DBLibrary
    private lateinit var selecteditemjudul: String
    private var opsijudul: String = "null"
    private var opJudul: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputDatapinjamBinding.inflate(layoutInflater)
        setContentView(binding.root)



        database = DBLibrary.getInstance(applicationContext)
        binding.SimpinputPin.setOnClickListener {
            if (binding.nmPin.text.isNotEmpty() &&
                selecteditemjudul !== "pilihjudulbuku" &&
                binding.tglPin.text.isNotEmpty() &&
                binding.batasWkt.text.isNotEmpty()
            ) {

                database.librarydao().insertDataPinjam(
                    DataPinjam(
                        0, binding.nmPin.text.toString(),
                        selecteditemjudul,
                        binding.tglPin.text.toString(),
                        binding.batasWkt.text.toString().toInt()
                    )
                )

                binding.nmPin.setText("")
                binding.tglPin.setText("")
                binding.batasWkt.setText("")
                startActivity(
                    Intent(this, LoanData::class.java)
                )
            }else {
                Toast.makeText(
                    applicationContext,
                    "silahkan isi data terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }

            val datajudulbuku = database.librarydao().getspinner()
            val databaru = arrayOf("pilihjudulbuku") + datajudulbuku
            val spnjudul = binding.pilihbuku
            val spnjuduladapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, databaru)
            spnjuduladapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spnjudul.adapter = spnjuduladapter
            spnjudul.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selecteditemjudul = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            val indexspnjudul = if (opsijudul == "null") 0 else databaru.indexOf(opsijudul)
            spnjudul.setSelection(indexspnjudul)
        }



        binding.backPin.setOnClickListener {
            startActivity(Intent(this, LoanData::class.java))
        }
        this.setTanggalRegister()
        this.setbataswaktu()
    }

    private fun setTanggalRegister() {
        this.setTanggal()
        binding.tglPin.setOnClickListener {
            var cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { picker, tahun, bulan, tanggal ->
                    binding.tglPin.setText("" + tanggal + "-" + bulan + "-" + tahun)
                }, year, month, day

            )
            datePickerDialog.show()
        }
    }

    private fun setTanggal() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("d-M-yyyy")
        val dateTime = simpleDateFormat.format(calendar.time)
        binding.tglPin.setText(dateTime)
    }

    private fun setbataswaktu() {
        this.setTglpngembalian()
        binding.batasWkt.setOnClickListener {
            var cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { picker, tahun, bulan, tanggal ->
                    binding.batasWkt.setText("" + tanggal + "-" + bulan + "-" + tahun)
                }, year, month, day

            )
            datePickerDialog.show()
        }
    }

    private fun setTglpngembalian() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("d-M-yyyy")
        val dateTime = simpleDateFormat.format(calendar.time)
        binding.batasWkt.setText(dateTime)

    }


}