package com.example.myapplication.services

import com.example.myapplication.models.RentalModel

object RentalService {
    private val cachedRentals : MutableList<RentalModel> = mutableListOf<RentalModel>()

    fun addRental(r: RentalModel) {
        cachedRentals.add(r)
    }

    fun getRentalById(id: Long): RentalModel? {
        for (r : RentalModel in cachedRentals) {
            if (r.id == id) {
                return r
            }
        }
        return null
    }
}