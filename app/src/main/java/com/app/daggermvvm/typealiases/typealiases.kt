package com.app.daggermvvm.typealiases

import androidx.databinding.ViewDataBinding
import com.app.daggermvvm.base.AppFragment

typealias ApplicationFragment = AppFragment<out ViewDataBinding>