package com.example.p2

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

// User.kt
@Entity
data class User(
    @Id var id: Long = 0,
    var name: String? = null
)

