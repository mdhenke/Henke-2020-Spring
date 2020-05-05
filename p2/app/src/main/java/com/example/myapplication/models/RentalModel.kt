package com.example.myapplication.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class RentalModel(
    @Id var id: Long = 0,
    var driver_id: Long? = 0,
    var activity: String? = "Wakeboarding",
    var hours: Int? = 1
)