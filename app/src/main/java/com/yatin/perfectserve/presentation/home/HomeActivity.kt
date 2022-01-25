package com.yatin.perfectserve.presentation.home

import com.yatin.perfectserve.presentation.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    override fun fragment() = HomeFragment()

}