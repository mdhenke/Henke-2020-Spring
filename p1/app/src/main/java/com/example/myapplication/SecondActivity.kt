package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {

    companion object{
        const val KEY_EXTRA = "KEY_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


    }
}
