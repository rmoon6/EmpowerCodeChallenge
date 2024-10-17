package com.example.empowercodechallenge

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.empowercodechallenge.MainPageState.BeneficiaryDetailsPage
import com.example.empowercodechallenge.MainPageState.ListPage

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.CreationFactory }

    private lateinit var mainFrameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainFrameLayout = FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }

        setContentView(mainFrameLayout)

        viewModel.pageState.observe(this) {
            when (it) {
                is ListPage -> showListPage(it)
                is BeneficiaryDetailsPage -> showDetailsPage(it)
            }
        }
    }

    override fun onBackPressed() {
        when (viewModel.pageState.value) {
            is ListPage -> super.onBackPressed()
            is BeneficiaryDetailsPage -> viewModel.beneficiaryDetailsExited()
            null -> {}
        }
    }

    private fun showListPage(listPage: ListPage) {
        val beneficiaryListView = BeneficiaryListView(this, listPage.beneficiaries) { beneficiary ->
            viewModel.beneficiarySelected(beneficiary)
        }
        mainFrameLayout.removeAllViews()
        mainFrameLayout.addView(beneficiaryListView)
    }

    private fun showDetailsPage(beneficiaryDetailsPage: BeneficiaryDetailsPage) {
        val beneficiaryDetailsView = BeneficiaryDetailsView(this, beneficiaryDetailsPage.beneficiary)
        mainFrameLayout.removeAllViews()
        mainFrameLayout.addView(beneficiaryDetailsView)
    }
}
