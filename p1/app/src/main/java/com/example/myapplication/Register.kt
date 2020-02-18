package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Register : AppCompatActivity() {

    companion object{
        const val USERNAME = "USR"
        const val PASSWORD = "PASS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)




    }
}
