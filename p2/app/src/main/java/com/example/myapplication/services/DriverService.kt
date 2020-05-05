package com.example.myapplication.services

import android.content.Context
import android.util.Log
import com.example.myapplication.models.Driver
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result;
import com.google.gson.Gson

object DriverService {
    private val cachedDrivers : MutableList<Driver> = mutableListOf<Driver>()
    fun getNearbyDrivers(context : Context) : List<Driver> {
        cachedDrivers.add(Driver(name = "Marcus Henke", lat = 43.523825, long = -116.052372))


        val httpAsync= "http://10.0.2.2:3000/drivers"
            .httpGet()
            .responseString{ request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.w("backend", "Connection to the express server could not be made. Please check your database credentials under the express folder in main.js, and ensure your server is running.")
                    cachedDrivers.add(Driver(id = 1, name = "Bill Gates", lat = 43.522298, long = -116.052267, imageUrl = "http://10.0.2.2:3000/drivers/bill_gates.jpg"))
                }
                is Result.Success -> {
                    val data = result.get()
                    //Load instance of Gson
                    val gson = Gson()
                    //Gather all new drivers from JSON response
                    val newDrivers: List<Driver> = gson.fromJson(data, Array<Driver>::class.java).toList()
                    //Add all new drivers to the list
                    for(newDriver: Driver in newDrivers) {
                        cachedDrivers.add(newDriver)
                    }
                }
            }
        }

        httpAsync.join()
        return cachedDrivers
    }

    fun getDriverById(i : Long) : Driver? {
        for (driver in cachedDrivers) {
            if (driver.id == i) {
                return driver
            }
        }
        return null
    }


}