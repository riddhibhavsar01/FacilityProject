package com.example.facilityproject.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object Util {

    fun showToast(context: Context, strError: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, strError, length).show()
    }
}

fun ViewGroup?.inflate(
    @LayoutRes resource: Int,
    attachToRoot: Boolean = false
): View {
    return LayoutInflater.from(this?.context).inflate(resource, this, attachToRoot)
}