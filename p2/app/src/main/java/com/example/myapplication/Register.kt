package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.models.UserModel
import com.example.myapplication.services.UserService
import kotlinx.android.synthetic.main.activity_second.*

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        registerBtn2.setOnClickListener {
            if (passwordRegister.text.toString() != confirmPasswordRegister.text.toString()) {
                noPasswordMatchText.visibility = View.VISIBLE
                badRegister.visibility = View.GONE
            }
            else {
                val user = UserModel(
                    email = emailRegister.text.toString(),
                    password = passwordRegister.text.toString()
                )
                if (UserService.addUser(user)) {
                    var resultIntent = Intent()
                    resultIntent.putExtra(MainActivity.STATUS, "LOGIN")
                    resultIntent.putExtra(MainActivity.EMAIL, emailRegister.text.toString())
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                } else {
                    passwordRegister.setText("")
                    badRegister.visibility = View.VISIBLE
                    noPasswordMatchText.visibility = View.GONE
                }
            }
        }

        back_to_login_button.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

    }
}
