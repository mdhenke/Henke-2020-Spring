package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.models.UserModel
import com.example.myapplication.services.UserService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val STATUS = "STATUS"
        const val REGISTER = 1
        const val EMAIL = "EMAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ObjectBox.init(this)
        setContentView(R.layout.activity_main)
        val usr = UserService.existingUser()
        if (usr != null) {
            emailLoginField.setText(usr)
        }

        loginBtn.setOnClickListener {
            val usr : UserModel? = UserService.loginUser(emailLoginField.text.toString(), passwordLoginField.text.toString())
            if (usr != null) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            else {
                passwordLoginField.setText("")
                badLogin.visibility = View.VISIBLE
                goodRegister.visibility = View.INVISIBLE
            }
        }
        
        registerBtn.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivityForResult(intent, REGISTER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REGISTER) {
            if (resultCode == Activity.RESULT_OK && data?.getStringExtra(STATUS) == "LOGIN") {
                goodRegister.visibility = View.VISIBLE
                var s : String? = data.getStringExtra(EMAIL)
                if (s != null) {
                    emailLoginField.setText(s)
                }
            }
            badLogin.visibility = View.INVISIBLE
        }
    }
}
