package com.example.cultura

import androidx.room.*

@Dao
interface DestinacijaDao {
    @Query("SELECT * FROM destinacije")
    suspend fun getSveDestinacije(): List<Destinacija>

    @Query("SELECT * FROM destinacije WHERE id = :id")
    suspend fun getDestinacijaPoId(id: Int): Destinacija

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(destinacija: Destinacija)
}
