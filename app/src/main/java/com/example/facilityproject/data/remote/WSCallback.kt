package com.example.facilityproject.data.remote

import com.example.facilityproject.data.remote.WSConstants
import com.example.facilityproject.model.ResAssessmentModel
import io.reactivex.Single

import retrofit2.http.*

/**
 * Retrofit callback methods.
 */

interface WSCallback {


    @GET(WSConstants.WS_METHOD_GET_ASSESSMENT)
    fun getAssessmentList(): Single<ResAssessmentModel>



}
