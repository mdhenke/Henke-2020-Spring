package com.example.myapplication.services

import android.location.Location

object Helper {
    public fun distanceInMiles(lat1: Double, lng1: Double, lat2: Double, lng2: Double) : String {
        var l1 = Location("")
        var l2 = Location("")
        l1.latitude = lat1
        l1.longitude = lng1
        l2.latitude = lat2
        l2.longitude = lng2
        var distance = l1.distanceTo(l2)
        distance /= 1609f
        var ret = "%.1f".format(distance) + " miles away"
        return ret
    }
}