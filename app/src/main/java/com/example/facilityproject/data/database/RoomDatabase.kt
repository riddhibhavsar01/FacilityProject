package com.example.facilityproject.data.database

import com.example.facilityproject.model.Album
import androidx.room.Database
import com.example.facilityproject.model.Exclusions
import com.example.facilityproject.model.Facilities
import com.example.facilityproject.model.Options

@Database(entities = [Facilities::class,Options::class,Exclusions::class], version = 1, exportSchema = false)
abstract class RoomDatabase : androidx.room.RoomDatabase() {
    abstract fun dao(): Dao
}