
package com.example.facilityproject.view

import com.example.facilityproject.model.Facilities


interface AssessmentView {
    fun showProgress()
    fun hideProgress()
    fun setFacilitiesData(arrNewsUpdates: ArrayList<Facilities>)
    fun getDataFailed(strError: String)
    fun onExclusionClicked()
}