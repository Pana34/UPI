package com.example.cultura

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinacije")
data class Destinacija(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val naziv: String,
    val opis: String,
    val slikaResId: Int
)
