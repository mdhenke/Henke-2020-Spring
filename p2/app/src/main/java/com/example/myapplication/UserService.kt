package com.example.myapplication

object UserService {
    private val usersList = mutableListOf<UserModel>()
    private val loggedInUser = null

    fun addUser(user: UserModel) : Boolean {
        for (existingUser in usersList) {
            if (existingUser.email == user.email)
                return false
        }
        usersList.add(user)
        return true
    }

    fun loginUser(email: String, password: String): UserModel? {

        for (user in usersList) {
            if (user.email == email) {
                if (user.password == password)
                    return user
                return null
            }
        }
        return null
    }
}
