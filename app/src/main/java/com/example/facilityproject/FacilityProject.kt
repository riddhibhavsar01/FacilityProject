package com.example.facilityproject

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.facilityproject.data.database.DatabaseManager


class FacilityProject : Application() {
    private var facilityProject: FacilityProject? = null

    @Synchronized
    fun getInstance(): FacilityProject? {
        return facilityProject
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        facilityProject = this
        var roomDb = Room.databaseBuilder(
            this,
            RoomDatabase::class.java,
            "app.db"
        ).build()
       dbManager = DatabaseManager(roomDb)


    }

    companion object {
        lateinit var instance: FacilityProject
        lateinit var dbManager: DatabaseManager
    }





}
