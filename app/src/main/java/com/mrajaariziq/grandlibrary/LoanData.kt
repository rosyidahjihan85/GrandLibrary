package com.mrajaariziq.grandlibrary

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.RoomDB.DataPinjam
import com.mrajaariziq.grandlibrary.adapter.Adapterdatapinjam
import com.mrajaariziq.grandlibrary.databinding.ActivityLoanDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoanData : AppCompatActivity() {

    private val db by lazy { DBLibrary.getInstance(this) }
    private lateinit var Adapter: Adapterdatapinjam
    private lateinit var database: DBLibrary
    private lateinit var binding:ActivityLoanDataBinding
    private var dtId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_data)

        binding = ActivityLoanDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Adapter = Adapterdatapinjam(arrayListOf(),
            object : Adapterdatapinjam.OnAdapterLinstener{

                override fun onhapus(dataPinjam: DataPinjam) {
                    hpsData(dataPinjam)

                }

                override fun onedit(dataPinjam: DataPinjam) {
                    UpdatePinjam(dataPinjam)
                }

            }

        )
        binding.homeLoan.setOnClickListener {
            startActivity( Intent(this,Beranda::class.java))
        }
        binding.ListPin.adapter = Adapter
        binding.ListPin.layoutManager= LinearLayoutManager(applicationContext, LinearLayoutManager. VERTICAL, false)
        binding.ListPin.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
        binding.btnplusLoan.setOnClickListener{
            startActivity(
                Intent(this, InputDatapinjamActivity::class.java)
            )
        }
    }
    private fun hpsData (dataPinjam: DataPinjam) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi hapus data")
            setMessage("Apakah anda yakin akan menghapus data ${dataPinjam.judul}?")
            setNegativeButton("Batal") { dialoginterface: DialogInterface, i: Int ->
                dialoginterface.dismiss()
            }
            setPositiveButton("Hapus") { dialoginterface: DialogInterface, i: Int ->
                dialoginterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.librarydao().deleteDataPinjam(dataPinjam)
                    finish()
                    startActivity(intent)
                }
                tampilsemuadata()
            }
            dialog.show()
        }
    }
    private fun UpdatePinjam(dataPinjam: DataPinjam){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("EDIT DATA?")
            setMessage("Apakah anda yakin akan mengedit data ${dataPinjam.namaPinjam}?")
            setNegativeButton("Batal") {dialoginterface: DialogInterface,i:Int ->
                dialoginterface.dismiss()
            }
            setPositiveButton("Edit") { dialoginterface: DialogInterface, i: Int ->
                dialoginterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.librarydao().deleteDataPinjam(dataPinjam)
                    finish()
                    startActivity(intent)
                }
                tampilsemuadata()
            }
            dialog.show()
        }

        }
    override fun onResume() {
        super.onResume()
        tampilsemuadata()

    }
    fun tampilsemuadata(){
        binding.ListPin.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val Data = db.librarydao().getAllPinjam()
            Adapter.setdata(Data)
            withContext(Dispatchers.Main){
                Adapter.notifyDataSetChanged()
            }
        }
        binding.ListPin.adapter= Adapter
    }

}
