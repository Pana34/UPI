package com.example.cultura

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Destinacija::class, Obicaj::class, Fraza::class, User::class],
    version = 4
)
abstract class AppDatabase : RoomDatabase() {

    // DAO-i za postojeće entitete
    abstract fun destinacijaDao(): DestinacijaDao
    abstract fun obicajDao(): ObicajDao
    abstract fun frazaDao(): FrazaDao

    // DAO za korisnika
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cultura_baza"
                )
                    .fallbackToDestructiveMigration() // Briše i stvara novu bazu pri migraciji
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
