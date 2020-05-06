package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.models.Driver
import com.example.myapplication.services.PlaceService
import kotlinx.android.synthetic.main.activity_driver.*
import kotlinx.android.synthetic.main.activity_main.*

class DriverActivity : AppCompatActivity(), DriverFragment.OnListFragmentInteractionListener {

    companion object {
        const val HIRE_DRIVER = 3
        const val DRIVER_KEY = "DRIVER"
    }

    private lateinit var driver : Driver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver)
        PlaceService.getNearbyLocation(applicationContext, this::displayCurrentLocationText)
    }



    private fun displayCurrentLocationText(s : String?) {
        val text : String = "Drivers near " + s
        site_loc.text = text
        var f : DriverFragment = supportFragmentManager.findFragmentById(R.id.fragment_drivers_list) as DriverFragment
        f.view?.visibility = View.VISIBLE
    }

    override fun onListFragmentInteraction(item: Driver?) {
        if (item != null) {
            driver = item
        }
        val intent = Intent(this, HireActivity::class.java)
        intent.putExtra(DRIVER_KEY, item?.id)
        startActivityForResult(intent, HIRE_DRIVER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == HIRE_DRIVER) {
            if (resultCode == Activity.RESULT_OK) {
               val resultIntent = Intent()
                resultIntent.putExtra("DRIVER", driver.id)
                resultIntent.putExtra("ACTIVITY", 1)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
