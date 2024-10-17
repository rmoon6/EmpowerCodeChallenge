package com.example.empowercodechallenge

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class BeneficiaryListView(
    context: Context,
    beneficiaries: List<Beneficiary>,
    onItemClick: (Beneficiary) -> Unit
) : ScrollView(context) {

    private val container = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    init {
        beneficiaries.forEach { beneficiary ->
            val row = createRow(beneficiary)
            row.setOnClickListener { onItemClick(beneficiary) }
            container.addView(row)
        }
        addView(container)
    }

    private fun createRow(beneficiary: Beneficiary): View {
        return TextView(context).apply {
            text = "${beneficiary.firstName} ${beneficiary.lastName} - ${mapDesignation(beneficiary.designationCode)}"
            textSize = 16f
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(16, 16, 16, 16)
        }
    }

    private fun mapDesignation(code: String): String {
        return when (code) {
            "P" -> "Primary"
            "C" -> "Contingent"
            else -> "Unknown"
        }
    }
}
