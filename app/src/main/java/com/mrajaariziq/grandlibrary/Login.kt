package com.mrajaariziq.grandlibrary

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mrajaariziq.grandlibrary.RoomDB.DBLibrary
import com.mrajaariziq.grandlibrary.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val innama = binding.loginnama
        val inpassword = binding.loginpassword
        val password = binding.loginpassword.text.split("\\s+".toRegex())
        binding.btnlogin.setOnClickListener{

            if (innama.text.isNotEmpty()&& inpassword.text.isNotEmpty())

                if (inpassword.text.length>= 5){
                    val sharedPreferences= getSharedPreferences("Grand",Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("username",innama.text.toString())
                    editor.apply()

                    startActivity(
                        Intent(this,Beranda::class.java)
                            .putExtra("username",innama.text.toString())
                    )
                    alert("Selamat Datang di Grand Library  ${innama.text}")
                    finish()

                }else{
                    alert("password minimal 5 huruf")
                }
        }
    }
    private fun alert(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}