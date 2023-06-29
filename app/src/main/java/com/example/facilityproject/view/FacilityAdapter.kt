package com.example.facilityproject.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.facilityproject.R
import com.example.facilityproject.databinding.ListFacilityBinding
import com.example.facilityproject.databinding.ListOptionsBinding
import com.example.facilityproject.model.Facilities
import com.example.facilityproject.presenter.AssessmentPresenter

import com.example.facilityproject.utils.inflate

class FacilityAdapter(
    val list: ArrayList<Facilities> = arrayListOf(),
    val assessmentPresenter : AssessmentPresenter
) :  RecyclerView.Adapter<FacilityAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

            return MyViewHolder(ListFacilityBinding.bind(parent.inflate(R.layout.list_facility)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FacilityAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class MyViewHolder(val binding: ListFacilityBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(facilities: Facilities) = with(facilities) {
            binding.txtFacilityName.text = facilities.name
          /*  binding.tvInterestName.text = profile?.firstName ?: "Name"
            glideRequests.loadUrl(binding.ivUserProfile, this?.avatar ?: "")*/
            binding.recyclerView.adapter = OptionAdapter(facilities.options) { facilityId, optionId ->
                assessmentPresenter.onItemClick(facilityId,optionId)
            }

        }
    }
}