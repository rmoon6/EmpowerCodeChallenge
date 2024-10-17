package com.example.emergecodechallenge

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class MainViewModel(private val applicationContext: Context) : ViewModel() {

    val beneficiaries: LiveData<List<Beneficiary>> get() = _beneficiaries
    private val _beneficiaries = MutableLiveData<List<Beneficiary>>()

    init {
        val beneficiariesJson = applicationContext.resources.readStringFromRaw(R.raw.beneficiaries)
        _beneficiaries.value = Beneficiary.parseFromJson(beneficiariesJson)
    }

    object CreationFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return MainViewModel(extras.application.applicationContext) as T
        }
    }
}
