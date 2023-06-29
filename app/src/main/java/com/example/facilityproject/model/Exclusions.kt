package com.example.facilityproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Exclusions (
  @PrimaryKey(autoGenerate = true)
  var id: Int?=0,
  var index: Int?=0,
  @SerializedName("facility_id" ) var facilityId : String="",
  @SerializedName("options_id"  ) var optionsId  : String=""

) : Parcelable{
  constructor(index: Int,facilityId: String,optionsId: String) : this(0,index,facilityId,optionsId)
  constructor(facilityId: String,optionsId: String) : this(0,0,facilityId,optionsId)

}