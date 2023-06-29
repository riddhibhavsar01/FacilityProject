package com.example.facilityproject.data.database


import com.example.facilityproject.model.Exclusions
import com.example.facilityproject.model.Facilities
import com.example.facilityproject.model.Options


class DatabaseManager(roomDatabase: RoomDatabase) : Database {

    private val dao = roomDatabase.dao()

    override suspend fun getAllFacilities(): List<Facilities> {
        return dao.getAllFacilities()
    }

    override suspend fun addAllFacilities(list: List<Facilities>) {
        dao.addAllFacilities(list)
    }

    override suspend fun getAllOptions(selectedId : String): List<Options> {
        return dao.getAllOptions(selectedId)
    }

    override suspend fun addAllOptions(list: List<Options>) {
        dao.addAllOptions(list)
    }

    override suspend fun getAllExclusions(): List<Exclusions> {
        return dao.getAllExclusions()
    }

    override suspend fun addAllExclusions(list: List<Exclusions>) {
        dao.addAllExclusions(list)
    }
}