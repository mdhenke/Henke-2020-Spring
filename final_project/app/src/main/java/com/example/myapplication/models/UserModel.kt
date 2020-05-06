package com.example.myapplication.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class UserModel(
    @Id var id: Long = 0,
    var email:String? = null,
    var password:String? = null
    )
