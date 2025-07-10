package com.example.cultura

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fraze")
data class Fraza(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val destinacijaId: Int,
    val izgovor: String,
    val prijevod: String
)
