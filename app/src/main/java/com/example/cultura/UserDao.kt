package com.example.cultura

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE email = :email")
    fun getUserByEmail(email: String): User?
}
