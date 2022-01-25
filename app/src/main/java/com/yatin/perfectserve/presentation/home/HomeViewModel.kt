package com.yatin.perfectserve.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yatin.perfectserve.domain.entities.Business
import com.yatin.perfectserve.domain.usecase.GetBusinessUseCase
import com.yatin.perfectserve.presentation.core.BaseViewModel
import com.yatin.perfectserve.presentation.home.BusinessFailure.ListNotAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBusinessUseCase: GetBusinessUseCase
) : BaseViewModel() {

    private val _businesses: MutableLiveData<List<BusinessView>> = MutableLiveData()
    val businesses: LiveData<List<BusinessView>> = _businesses

    fun loadBusinesses(location: String) =
        getBusinessUseCase(
            GetBusinessUseCase.Params(location),
            viewModelScope
        ) { it.fold(::handleFailure, ::handleBusinessList) }

    private fun handleBusinessList(business: List<Business>) {
        if (business.isEmpty()) {
            handleFailure(ListNotAvailable())
        } else {
            _businesses.value = business.map { it.toBusinessView() }
        }
    }

}