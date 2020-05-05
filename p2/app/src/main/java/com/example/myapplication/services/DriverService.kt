package com.example.myapplication.services

import android.content.Context
import android.util.Log
import com.example.myapplication.models.Driver
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result;

object DriverService {
    private val cachedDrivers : MutableList<Driver> = mutableListOf<Driver>()
    fun getNearbyDrivers(context : Context) : List<Driver> {
        cachedDrivers.add(Driver(name = "Marcus Henke", lat = 43.523825, long = -116.052372))


        val httpAsync= "http://10.0.2.2:3000/"
            .httpGet()
            .responseString{ request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.e("checkme", ex.toString())
                }
                is Result.Success -> {
                    val data = result.get()
                    cachedDrivers.add(Driver(id = 1, name = data, lat = 43.522298, long = -116.052267, imageUrl = "http://10.0.2.2:3000/drivers/bill_gates.jpg"))
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