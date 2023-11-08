package com.mrajaariziq.grandlibrary

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary.Companion.getInstance
import com.mrajaariziq.grandlibrary.RoomDB.DataBuku
import com.mrajaariziq.grandlibrary.RoomDB.DataPinjam
import com.mrajaariziq.grandlibrary.adapter.Adapterdatabuku
import com.mrajaariziq.grandlibrary.databinding.ActivityBookDataBinding
import com.mrajaariziq.grandlibrary.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookData : AppCompatActivity() {

    private val db by lazy { DBLibrary.getInstance(this)}
    private lateinit var adapter : Adapterdatabuku
    private lateinit var database : DBLibrary
    private lateinit var binding: ActivityBookDataBinding
    private var dataid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_data)

        binding = ActivityBookDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= Adapterdatabuku(arrayListOf(),
            object : Adapterdatabuku.OnAdapterListener{
                override fun ondelete(dataBuku: DataBuku) {
                    hapusdata(dataBuku)
                }

                override fun onedit(dataBuku: DataBuku) {
                    EditData(dataBuku)
                }

            }
        )
        binding.listdata.adapter = adapter
        binding.listdata.layoutManager= LinearLayoutManager(applicationContext, LinearLayoutManager. VERTICAL, false)
        binding.listdata.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager. VERTICAL))
        binding.btnplusbook.setOnClickListener{
            startActivity(
                Intent(this, InputDatabukuActivity::class.java)
            )
        }
    }
    private fun hapusdata (dataBuku: DataBuku) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi hapus data")
            setMessage("Apakah anda yakin akan menghapus data ${dataBuku.judulBk}?")
            setNegativeButton("Batal") { dialoginterface: DialogInterface, i: Int ->
                dialoginterface.dismiss()
            }
            setPositiveButton("Hapus") { dialoginterface: DialogInterface, i: Int ->
                dialoginterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.librarydao().deleteDataBuku(dataBuku)
                    finish()
                    startActivity(intent)
                }
                tampilsemuadata()
            }
            dialog.show()
        }
    }
    private fun EditData (dataBuku: DataBuku) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("edit data?")
            setMessage("Apakah anda yakin akan mengedit data ${dataBuku.judulBk}?")
            setNegativeButton("Batal") { dialoginterface: DialogInterface, i: Int ->
                dialoginterface.dismiss()
            }
            setPositiveButton("Edit") { dialoginterface: DialogInterface, i: Int ->
                dialoginterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.librarydao().updateDataBuku(dataBuku)
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
        binding.listdata.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.librarydao().getAllBuku()
            adapter.setData(data)
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }
        }
        binding.listdata.adapter= adapter
    }

}


