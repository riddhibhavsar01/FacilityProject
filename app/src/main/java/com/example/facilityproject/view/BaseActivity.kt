package com.example.facilityproject.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(), View.OnClickListener {


    protected lateinit var binding: B
    abstract fun onViewBinding(): B
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = onViewBinding()
        setContentView(binding.root)
        initView()
    }

    override fun onClick(v: View?) {

    }




}