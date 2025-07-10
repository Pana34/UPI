package com.example.cultura

import androidx.room.*

@Dao
interface ObicajDao {
    @Query("SELECT * FROM obicaji WHERE destinacijaId = :destId")
    suspend fun getObicajiZaDestinaciju(destId: Int): List<Obicaj>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg obicaji: Obicaj)
}
