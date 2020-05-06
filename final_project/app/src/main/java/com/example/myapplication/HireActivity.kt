package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.myapplication.models.Driver
import com.example.myapplication.models.RentalModel
import com.example.myapplication.services.DriverService
import com.example.myapplication.services.RentalService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hire.*

class HireActivity : AppCompatActivity() {
    private lateinit var d : Driver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hire)

        val id = intent.getLongExtra(DriverActivity.DRIVER_KEY, 5)
        var driver : Driver? = DriverService.getDriverById(id)
        if (driver != null) {
            d = driver
        }

        driver_name_text_view.text = d.name
        val rateText = "\$${d.rate}/hr"
        driver_rate_text_view.text = rateText

        Picasso.get().load(d.imageUrl).into(driver_image_hire_view)
        setListeners()
        setSpinner()
    }

    private fun setListeners() {

        val hrs = number_of_hours.text.toString().toInt()
        val rate = d.rate!!
        val price = rate * hrs
        val s : String = "Hire for $" + (price)
        hire_driver_btn.text = s
        hire_driver_btn.setOnClickListener {
            val r : RentalModel = RentalModel(driver_id = d.id, activity = activity_spinner.selectedItem.toString(), hours = number_of_hours.text.toString().toInt())
            RentalService.addRental(r)
            d.currentRentalId = r.id
            val returnIntent = Intent()
            returnIntent.putExtra("ACTIVITY", "Wakeboarding")
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
        number_of_hours.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString() != "") {
                    val hrs = p0.toString().toInt()
                    val rate = d.rate!!
                    val price = rate * hrs
                    val s : String = "Hire for $" + (price)
                    hire_driver_btn.text = s
                }
            }
            //Nothing needed here.
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun setSpinner() {
        val spinner: Spinner = findViewById(R.id.activity_spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.activities_array,
            R.layout.spinner_layout
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }
}
