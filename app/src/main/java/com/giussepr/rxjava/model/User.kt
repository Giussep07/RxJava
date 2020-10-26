package com.giussepr.rxjava.model

data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val favoriteColor: String
) {
    companion object {
        fun getUsers(): List<User> {
            val users: MutableList<User> = ArrayList()

            users.add(User(1, "Christhian", 30, "Blue"))
            users.add(User(2, "Giussep", 26, "Blue"))
            users.add(User(3, "Lizeth", 29, "Violet"))
            users.add(User(4, "Elizabeth", 4, "Pink"))
            users.add(User(5, "Nicholas", 45, "Black"))

            return users
        }
    }
}