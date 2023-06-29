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

package com.example.facilityproject.model


import com.example.facilityproject.FacilityProject
import com.example.facilityproject.data.database.DatabaseManager
import com.example.facilityproject.utils.Preference
import com.vendu.books.webservice.WSUtils
import io.reactivex.SingleObserver
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AssesmentInteractor {
    companion object {
        private val TAG: String = AssesmentInteractor::class.java.simpleName
    }

    interface OnFinishedListener {
        fun onResultSuccess(arrNewsUpdates: ArrayList<Facilities>)
        fun onResultFail(strError: String)
        fun onShowResultError()
    }

    var selectedExclusion =  arrayListOf<Exclusions>()

    fun requestFacilityDataAPI(onFinishedListener: OnFinishedListener) {
        val manager = FacilityProject.dbManager
        val getFacilityDetailObservable =
            WSUtils.client?.getAssessmentList()?.compose(applyObservableAsync<ResAssessmentModel>())
        getFacilityDetailObservable?.subscribe(object : SingleObserver<ResAssessmentModel> {
            override fun onSuccess(value: ResAssessmentModel?) {
                value?.let {
                    if (!it.facilities.isEmpty()) {
                        Preference.instance.timeStamp = System.currentTimeMillis()
                        val facilityList = arrayListOf<Facilities>()
                        val optionsList = arrayListOf<Options>()
                        val exclusionList = arrayListOf<Exclusions>()
                        for (facility in it.facilities) {
                            facilityList.add(Facilities(facility.facilityId,facility.name))
                            for(options in facility.options){
                                optionsList.add(Options(options.name,options.icon,options.options_id,facility.facilityId))
                            }
                        }
                        it.exclusions.forEachIndexed{index, it ->
                            var exlusionObj = Exclusions()
                            exlusionObj.index = index
                            it.forEach { it1 ->
                                exlusionObj.facilityId = it1.facilityId
                                exlusionObj.optionsId = it1.optionsId
                            }
                            exclusionList.add(exlusionObj)
                        }
                        GlobalScope.launch {
                            manager.addAllFacilities(facilityList)
                            manager.addAllOptions(optionsList)
                            manager.addAllExclusions(exclusionList)
                        }
                        onFinishedListener.onResultSuccess(it.facilities as ArrayList<Facilities>)
                    } else {
                        onFinishedListener.onResultFail("Nothing to show")
                    }
                }

            }

            override fun onSubscribe(d: Disposable?) {

            }

            override fun onError(e: Throwable?) {
                onFinishedListener.onResultFail("Something went wrong")
            }

        }
        )
    }

    fun requestFacilityDataDB(onFinishedListener: OnFinishedListener) {
        val manager = FacilityProject.dbManager
        GlobalScope.launch {
           val facilityList = manager.getAllFacilities()
            if (!facilityList.isEmpty()) {
                for (facility in facilityList) {
                    val options = manager.getAllOptions(facility.facilityId)
                    facility.options.addAll(options)
                }
                onFinishedListener.onResultSuccess(facilityList as ArrayList<Facilities>)
            }else{
                onFinishedListener.onResultFail("Nothing to show")
            }
        }

    }

    protected fun <T> applyObservableAsync(): SingleTransformer<T, T> {

        return SingleTransformer { observable ->
            observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun checkForExclusions(facilityId : String , optionId : String,onFinishedListener: OnFinishedListener){
        val manager = FacilityProject.dbManager
        selectedExclusion.add(Exclusions(facilityId,optionId))
        if(selectedExclusion.size >1){
            GlobalScope.launch {
               val exclusionList =  manager.getAllExclusions()
                val selectedIndex = arrayListOf<Int?>()
               selectedExclusion.forEach {
                   exclusionList.forEach {exclusions ->
                       if(exclusions.facilityId.equals(it.facilityId) && exclusions.optionsId.equals(it.optionsId)){
                          selectedIndex.add(exclusions.index)
                       }
                   }
               }

                val numbersByElement = selectedIndex.groupBy { it }.mapValues { it.value.size }.maxBy { it.value }?.value

                if (numbersByElement != null) {
                    if (numbersByElement.toInt() ==2)
                    {
                        onFinishedListener.onShowResultError()
                    }


                }
            }
        }
    }



}