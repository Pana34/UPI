package com.example.cultura

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "obicaji")
data class Obicaj(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val destinacijaId: Int,
    val tekst: String
)
