package com.henke.hw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catBtn.setOnClickListener {
            var string:String = editText.text.toString() + editText.text.toString()
            catBox.text = string
        }
    }
}
