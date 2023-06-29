package com.example.facilityproject.model

import com.google.gson.annotations.SerializedName

data class ResAssessmentModel (

  @SerializedName("facilities" ) var facilities : List<Facilities>            = arrayListOf(),
  @SerializedName("exclusions" ) var exclusions : List<List<Exclusions>> = arrayListOf()

)