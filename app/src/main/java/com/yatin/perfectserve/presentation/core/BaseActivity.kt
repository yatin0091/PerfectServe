package com.yatin.perfectserve.presentation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yatin.perfectserve.R
import com.yatin.perfectserve.databinding.ActivityHomeBinding
import com.yatin.perfectserve.presentation.core.extension.inTransaction
import dagger.hilt.android.AndroidEntryPoint

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    internal lateinit var binding: ActivityHomeBinding

    abstract fun fragment(): BaseFragment<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment<*>).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(
                R.id.fragmentContainer,
                fragment()
            )
        }
}
