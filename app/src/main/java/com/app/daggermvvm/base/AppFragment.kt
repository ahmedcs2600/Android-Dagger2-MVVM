package com.app.daggermvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.app.daggermvvm.App
import com.app.daggermvvm.di.components.ApplicationComponent

abstract class AppFragment<VB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment(layoutId) {

    protected lateinit var binding: VB

    private val mApp
        get() = (requireActivity().application as App)

    protected val appComponent: ApplicationComponent
        get() = mApp.appComponent

    abstract fun initializeComponents()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponents()
    }
}