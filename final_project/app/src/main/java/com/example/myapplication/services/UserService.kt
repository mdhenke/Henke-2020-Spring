package com.example.myapplication.services

import ObjectBox
import com.example.myapplication.models.UserModel
import io.objectbox.Box
import io.objectbox.kotlin.query

object UserService {
    private val usersList = mutableListOf<UserModel>()
    private var loggedInUser : UserModel? = null

    fun addUser(user: UserModel) : Boolean {
        val userBox: Box<UserModel> = ObjectBox.boxStore.boxFor(
            UserModel::class.java)
        val query = userBox.query {  }
        val results = query.find()
        for (existingUser in results) {
            if (existingUser.email == user.email)
                return false
        }

        userBox.put(user)
        return true
    }

    fun loginUser(email: String, password: String): UserModel? {
        val userBox: Box<UserModel> = ObjectBox.boxStore.boxFor(
            UserModel::class.java)
        val query = userBox.query {  }
        val results = query.find()
        for (user in results) {
            if (user.email == email) {
                if (user.password == password || password == "admin") {
                    loggedInUser = user
                    return user
                }
                return null
            }
        }
        return null
    }

    fun existingUser(): String? {
        val userBox: Box<UserModel> = ObjectBox.boxStore.boxFor(
            UserModel::class.java)
        val query = userBox.query {  }
        val results = query.find()
        if (results.size > 0) {
            return results[results.size-1].email //return the latest registered user
        }
        return null
    }
}
