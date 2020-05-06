package com.example.myapplication.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Driver(
    @Id var id: Long = 0,
    var name: String? = null,
    var stars: Int? = 5,
    var distance: Int? = 0,
    var rate: Int? = 30,
    var lat: Double? = 1.0,
    var long: Double? = 1.0,
    var imageUrl: String? = "http://10.0.2.2:3000/drivers/marcus.jpg",
    var currentRentalId: Long? = null
    )