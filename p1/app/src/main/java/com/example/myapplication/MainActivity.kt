package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val STATUS = "STATUS"
        const val REGISTER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataService.initializeJobList()

        loginBtn.setOnClickListener {
            val usr : UserModel? = UserService.loginUser(emailLoginField.text.toString(), passwordLoginField.text.toString())
            if (usr != null) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(HomeActivity.USER, usr.email)
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
            if (resultCode == Activity.RESULT_OK) {
                goodRegister.visibility = View.VISIBLE
                badLogin.visibility = View.INVISIBLE
            }
        }
    }
}
