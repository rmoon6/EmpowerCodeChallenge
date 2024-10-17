package com.example.emergecodechallenge

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import com.example.emergecodechallenge.MainPageState.BeneficiaryDetailsPage
import com.example.emergecodechallenge.MainPageState.ListPage

class MainActivity : androidx.activity.ComponentActivity() {

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
