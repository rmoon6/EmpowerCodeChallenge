package com.example.emergecodechallenge

sealed interface MainPageState {
    data class ListPage(val beneficiaries: List<Beneficiary>) : MainPageState
    data class BeneficiaryDetailsPage(val beneficiary: Beneficiary) : MainPageState
}
