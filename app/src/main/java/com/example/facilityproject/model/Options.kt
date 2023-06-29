package com.example.facilityproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Options (

  @SerializedName("name" ) var name : String="",
  @SerializedName("icon" ) var icon : String="",
  @PrimaryKey(autoGenerate = true)
   var id   : Int,
  @SerializedName("id") var options_id   : String="",
  var facility_id   : String=""

) : Parcelable{
  constructor(name: String,icon: String,options_id: String,facility_id : String) : this(name,icon, 0,options_id,facility_id)
}