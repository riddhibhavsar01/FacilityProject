package com.example.facilityproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Facilities(
  @PrimaryKey(autoGenerate = true)
  var id: Int ?=0,
  @SerializedName("facility_id" ) var facilityId : String = "",
  @SerializedName("name"        ) var name       : String = "",
  @Ignore @SerializedName("options"     ) var options    : ArrayList<Options> = arrayListOf(),

) : Parcelable{
  constructor(id : Int,facilityId: String,name: String) : this(id, facilityId, name,arrayListOf())
  constructor(facilityId: String,name: String) : this(0,facilityId, name,arrayListOf())
}