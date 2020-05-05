package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.models.Driver
import com.example.myapplication.models.RentalModel
import com.example.myapplication.services.DriverService
import com.example.myapplication.services.RentalService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var currentDriver: Driver
    private lateinit var currentRental: RentalModel
    private var zeroIn : Boolean = false

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1024
        const val INTERNET_PERMISSION_REQUEST_CODE = 1025
        const val DRIVER_FOUND = 10
        var currentLocation: LatLng? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        view_drivers_btn.setOnClickListener {
            val intent = Intent(this, DriverActivity::class.java)

            startActivityForResult(intent, DRIVER_FOUND)
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            setMapToCurrentLocation()
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.INTERNET),
                INTERNET_PERMISSION_REQUEST_CODE)
        }
    }

    private fun enableMyLocation() {
        mMap.isMyLocationEnabled = true
    }

    private fun disableMyLocation() {
        mMap.isMyLocationEnabled = false
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    enableMyLocation()
                } else {
                    disableMyLocation()
                }
                return
            }
            INTERNET_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //Nothing
                }
                else {
                    // return to main activity
                }
            }

            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun setMapToCurrentLocation() {
        mMap.isMyLocationEnabled = true
        mMap.setOnMyLocationChangeListener {
            if (!zeroIn) {
                currentLocation = LatLng(it.latitude, it.longitude)
                var loc = CameraPosition(currentLocation, 16.0f, 0.0f, 0.0f)
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(loc))
                zeroIn = true
            }
        }
    }

    private fun driverSelected() {
        view_drivers_btn.visibility = View.GONE
        val lat = currentDriver.lat
        val lng = currentDriver.long
        if (lat != null && lng != null) {
            mMap.addMarker(MarkerOptions().position(LatLng(lat, lng)).title(currentDriver.name))
        }
        driver_on_the_way_card.visibility = View.VISIBLE
        driver_time_to_arrival.text = "Arriving in 2 minutes"

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DRIVER_FOUND) {
            if (resultCode == Activity.RESULT_OK && data?.getIntExtra("ACTIVITY", 0) != 0) {
                var dId : Long? = data?.getLongExtra("DRIVER", 0)
                val driver = DriverService.getDriverById(dId!!)
                currentDriver = driver!!
                currentRental = RentalService.getRentalById(currentDriver.currentRentalId!!)!!
                Toast.makeText(applicationContext,
                    "You selected " + currentDriver.name + " to pick you up for " + currentRental.activity + "!",
                    Toast.LENGTH_LONG).show()
                driverSelected()
            }
        }
    }

}
