package com.example.empowercodechallenge

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class MainViewModel(private val applicationContext: Context) : ViewModel() {

    val pageState: LiveData<MainPageState> get() = _pageState

    fun beneficiarySelected(beneficiary: Beneficiary) {
        _pageState.value = MainPageState.BeneficiaryDetailsPage(beneficiary)
    }

    fun beneficiaryDetailsExited() {
        loadListPage()
    }

    private val _pageState = MutableLiveData<MainPageState>()

    init {
        loadListPage()
    }

    private fun loadListPage() {
        val beneficiariesJson = applicationContext.resources.readStringFromRaw(R.raw.beneficiaries)
        _pageState.value = MainPageState.ListPage(Beneficiary.parseListFromJson(beneficiariesJson))
    }

    object CreationFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return MainViewModel(extras.application.applicationContext) as T
        }
    }
}
