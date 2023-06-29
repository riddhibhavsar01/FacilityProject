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

package com.example.facilityproject.view

import android.view.View
import com.example.facilityproject.databinding.ActivityAssessmentBinding
import com.example.facilityproject.model.AssesmentInteractor
import com.example.facilityproject.model.Facilities
import com.example.facilityproject.presenter.AssessmentPresenter
import com.example.facilityproject.utils.Preference
import com.example.facilityproject.utils.Util.showToast



class AssessmentActivity : BaseActivity<ActivityAssessmentBinding>(), AssessmentView {
    private lateinit var assessmentPresenter : AssessmentPresenter

    override fun onViewBinding(): ActivityAssessmentBinding {
        return ActivityAssessmentBinding.inflate(layoutInflater)
    }


    override fun initView() {
        assessmentPresenter = AssessmentPresenter(this, AssesmentInteractor())
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()

        if(NetworkUtils.isNetworkAvailable(this) && differenceResult(System.currentTimeMillis() - Preference.instance.timeStamp)) {
            assessmentPresenter.getFacilityData()
        }else{
            assessmentPresenter.getFacilityDataFromDb()
        }
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun setFacilitiesData(arrNewsUpdates: ArrayList<Facilities>) {
        binding.recyclerView.adapter = FacilityAdapter(arrNewsUpdates,assessmentPresenter)
    }


    override fun getDataFailed(strError: String) {
        showToast(this, strError)
    }

    override fun onExclusionClicked() {
        showToast(this, "You cant select this combo")
    }

    override fun onDestroy() {
        assessmentPresenter.onDestroy()
        super.onDestroy()
    }


    fun differenceResult(time: Long): Boolean {
        var days: Long = time / 86400
        return days >=1;
    }
}
