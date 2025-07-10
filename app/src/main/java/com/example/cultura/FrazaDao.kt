package com.example.cultura

import androidx.room.*

@Dao
interface FrazaDao {
    @Query("SELECT * FROM fraze WHERE destinacijaId = :destId")
    suspend fun getFrazeZaDestinaciju(destId: Int): List<Fraza>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg fraze: Fraza)
}
