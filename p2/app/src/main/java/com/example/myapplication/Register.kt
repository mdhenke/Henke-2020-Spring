package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        registerBtn2.setOnClickListener {
            val user = UserModel(emailRegister.text.toString(), passwordRegister.text.toString())
            if (UserService.addUser(user)) {
                var resultIntent = Intent()
                resultIntent.putExtra(MainActivity.STATUS, "LOGIN")
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            else {
                emailRegister.setText("")
                passwordRegister.setText("")
                badLogin2.visibility = View.VISIBLE
            }
        }

    }
}
