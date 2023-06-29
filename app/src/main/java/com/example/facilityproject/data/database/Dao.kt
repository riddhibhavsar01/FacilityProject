package com.example.facilityproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.facilityproject.model.Album
import com.example.facilityproject.model.Exclusions
import com.example.facilityproject.model.Facilities
import com.example.facilityproject.model.Options


@Dao
interface Dao {

    @Query("SELECT * FROM facilities")
     fun getAllFacilities(): List<Facilities>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addAllFacilities(list: List<Facilities>)

    @Query("SELECT * FROM options where facility_id = :selectedId")
     fun getAllOptions(selectedId : String): List<Options>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addAllOptions(list: List<Options>)

    @Query("SELECT * FROM exclusions")
     fun getAllExclusions(): List<Exclusions>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addAllExclusions(list: List<Exclusions>)

}