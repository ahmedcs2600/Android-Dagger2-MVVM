package com.app.daggermvvm.ktx

import android.widget.Toast
import com.app.daggermvvm.typealiases.ApplicationFragment

fun ApplicationFragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}