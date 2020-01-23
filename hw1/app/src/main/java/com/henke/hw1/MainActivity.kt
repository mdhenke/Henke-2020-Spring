package com.henke.hw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catBtn.setOnClickListener {
            var string:String = editText.text.toString() + editText2.text.toString()
            catBox.text = string
        }

        d4Btn.setOnClickListener {
            val rand = Random.nextInt(1, 5) + damageText.text.toString().toInt()
            var string:String = "You rolled for $rand points of damage!"
            textView2.text = string
        }

        d6Btn.setOnClickListener {
            val rand = Random.nextInt(1, 7) + damageText.text.toString().toInt()
            var string:String = "You rolled for $rand points of damage!"
            textView2.text = string
        }

        d8Btn.setOnClickListener {
            val rand = Random.nextInt(1, 9) + damageText.text.toString().toInt()
            var string:String = "You rolled for $rand points of damage!"
            textView2.text = string
        }
    }
}
