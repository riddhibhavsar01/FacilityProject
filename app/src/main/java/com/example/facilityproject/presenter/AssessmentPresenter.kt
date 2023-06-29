/*
 * Copyright (C) 2017 Rohit Sahebrao Surwase.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.facilityproject.presenter

import com.example.facilityproject.model.AssesmentInteractor
import com.example.facilityproject.model.Facilities
import com.example.facilityproject.view.AssessmentView


class AssessmentPresenter(private var assessmentView: AssessmentView?, private val assesmentInteractor: AssesmentInteractor)
    : AssesmentInteractor.OnFinishedListener {

    fun getFacilityData() {
        assessmentView?.showProgress()
        assesmentInteractor.requestFacilityDataAPI(this)
    }

    fun getFacilityDataFromDb() {
        assessmentView?.showProgress()
        assesmentInteractor.requestFacilityDataDB(this)
    }

    fun onDestroy() {
        assessmentView = null
    }

    override fun onResultSuccess(arrNewsUpdates: ArrayList<Facilities>) {
        assessmentView?.hideProgress()
        assessmentView?.setFacilitiesData(arrNewsUpdates)
    }

    override fun onResultFail(strError: String) {
        assessmentView?.hideProgress()
        assessmentView?.getDataFailed(strError)
    }

    override fun onShowResultError() {
        assessmentView?.onExclusionClicked()
    }

    fun onItemClick(facilityId: String,optionId : String) {

        assesmentInteractor.checkForExclusions(facilityId, optionId,this)

    }
}