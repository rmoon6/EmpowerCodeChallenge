package com.example.emergecodechallenge

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class BeneficiaryListView(
    context: Context,
    beneficiaries: List<Beneficiary>,
    onBeneficiarySelected: (Beneficiary) -> Unit
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
            val textView = TextView(context).apply {
                text = "${beneficiary.firstName} ${beneficiary.lastName}"
                textSize = 18f
                setPadding(16, 16, 16, 16)
                setOnClickListener { onBeneficiarySelected.invoke(beneficiary) }
            }
            container.addView(textView)
        }

        addView(container)
    }
}
