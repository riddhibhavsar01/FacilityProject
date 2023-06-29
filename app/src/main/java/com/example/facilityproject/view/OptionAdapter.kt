package com.example.facilityproject.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facilityproject.R
import com.example.facilityproject.databinding.ListOptionsBinding
import com.example.facilityproject.model.Facilities
import com.example.facilityproject.model.Options
import com.example.facilityproject.utils.Util
import com.example.facilityproject.utils.inflate

class OptionAdapter(
    val list: ArrayList<Options> = arrayListOf(), private val listener: (String,String) -> Unit
) :  RecyclerView.Adapter<OptionAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

            return MyViewHolder(ListOptionsBinding.bind(parent.inflate(R.layout.list_options)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OptionAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position],listener)
    }


    inner class MyViewHolder(val binding: ListOptionsBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(options: Options, listener: (String,String) -> Unit) = with(options) {
           binding.txtOptionsName.text = options.name
            var image = R.mipmap.apartment
            when(options.icon){
                "apartment" -> R.mipmap.apartment
                "condo" -> R.mipmap.condo
                "boat" -> R.mipmap.boat
                "land" -> R.mipmap.land
                "rooms" -> R.mipmap.rooms
                "no-room" -> R.mipmap.no_room
                "swimming" -> R.mipmap.swimming
                "garden" -> R.mipmap.garden
                "garage" -> R.mipmap.garage
            }
            Glide.with(binding.txtOptionsName.context).load(image)
                .into(binding.imgOption)
            itemView.setOnClickListener{listener(options.facility_id,options.options_id)}
        }
    }
}