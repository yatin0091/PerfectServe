package com.yatin.perfectserve.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yatin.perfectserve.R
import com.yatin.perfectserve.databinding.FragmentHomeBinding
import com.yatin.perfectserve.domain.core.Failure
import com.yatin.perfectserve.presentation.core.BaseFragment
import com.yatin.perfectserve.presentation.core.extension.appContext
import com.yatin.perfectserve.presentation.core.extension.failure
import com.yatin.perfectserve.presentation.core.extension.observe
import com.yatin.perfectserve.presentation.core.extension.showKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    @Inject
    lateinit var businessesAdapter: BusinessesAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(homeViewModel) {
            observe(businesses, ::renderBusinessList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        binding.locationSearch.requestFocus()
        binding.locationSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    loadBusinessList(it)
                    binding.locationSearch.clearFocus()
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        binding.businessesList.layoutManager = LinearLayoutManager(appContext)
        binding.businessesList.adapter = businessesAdapter
    }

    private fun showProgress() = View.VISIBLE.also { binding.progressBar.visibility = it }

    private fun hideProgress() = View.GONE.also { binding.progressBar.visibility = it }

    private fun resetWrongSearchQuery() {
        binding.locationSearch.setQuery("", false)
        binding.locationSearch.requestFocus()
        binding.locationSearch.showKeyboard()
    }

    private fun loadBusinessList(location: String) {
        renderBusinessList(emptyList())
        showProgress()
        homeViewModel.loadBusinesses(location)
    }

    private fun renderBusinessList(businessList: List<BusinessView>?) {
        businessesAdapter.collection = businessList.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> {
                resetWrongSearchQuery()
                renderFailure(R.string.failure_server_error)
            }
            is BusinessFailure.ListNotAvailable -> renderFailure(R.string.failure_businesses_list_unavailable)
            else -> renderFailure(R.string.failure_server_error)
        }
    }


    private fun renderFailure(@StringRes message: Int) {
//        binding.postList.invisible()
//        binding.emptyView.visible()
        hideProgress()
        notify(message)
    }
}