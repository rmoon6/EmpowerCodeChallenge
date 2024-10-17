package com.example.emergecodechallenge

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class MainViewModel(private val applicationContext: Context) : ViewModel() {

    val pageState: LiveData<MainPageState> get() = _pageState

    private val _pageState = MutableLiveData<MainPageState>()

    fun beneficiarySelected(beneficiary: Beneficiary) {
        _pageState.value = MainPageState.BeneficiaryDetailsPage(beneficiary)
    }

    fun beneficiaryDetailsExited() {
        loadListPage()
    }

    private fun loadListPage() {
        val beneficiariesJson = applicationContext.resources.readStringFromRaw(R.raw.beneficiaries)
        _pageState.value = MainPageState.ListPage(Beneficiary.parseFromJson(beneficiariesJson))
    }

    object CreationFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return MainViewModel(extras.application.applicationContext) as T
        }
    }
}
