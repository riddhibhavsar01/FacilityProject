package com.example.facilityproject.data.database

import com.example.facilityproject.model.Album
import com.example.facilityproject.model.Exclusions
import com.example.facilityproject.model.Facilities
import com.example.facilityproject.model.Options


interface Database {
    suspend fun getAllFacilities(): List<Facilities>

    suspend fun addAllFacilities(list: List<Facilities>)

    suspend fun getAllOptions(selectedId : String): List<Options>

    suspend fun addAllOptions(list: List<Options>)

    suspend fun getAllExclusions(): List<Exclusions>

    suspend fun addAllExclusions(list: List<Exclusions>)
}