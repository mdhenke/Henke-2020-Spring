package com.example.myapplication.services

import android.content.Context
import android.util.Log
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import java.util.*

object PlaceService {

    fun getNearbyLocation(context: Context, setFun: (s : String?) -> Unit) {
        // Initialize the SDK
        Log.i("me", "onetwothree")
        Places.initialize(context, "AIzaSyBlBtDIIw6keJi7ivugR1-_9txp4J6T3Hg")

        // Use fields to define the data types to return.
        val placeFields: List<Place.Field> =
            Collections.singletonList(Place.Field.NAME)

        // Use the builder to create a FindCurrentPlaceRequest.
        val request = FindCurrentPlaceRequest.newInstance(placeFields)
        // Create a new Places client instance
        val placesClient: PlacesClient = Places.createClient(context)

        // Call findCurrentPlace and handle the response (first check that the user has granted permission).
        val placeResponse: Task<FindCurrentPlaceResponse?> =
            placesClient.findCurrentPlace(request)
        //This has not been working lately...
        placeResponse.addOnCompleteListener { task: Task<FindCurrentPlaceResponse?> ->
            if (task.isSuccessful) {
                val response = task.result
                setFun(response!!.placeLikelihoods.get(0).place.name)
            } else {
                val exception = task.exception
                if (exception is ApiException) {
                    val apiException = exception
                    Log.e(
                        "places_error",
                        "Place not found: " + apiException.statusCode
                    )
                }
            }
        }
        //While google places API is not working...
       setFun("Barclay Bay")

    }

}