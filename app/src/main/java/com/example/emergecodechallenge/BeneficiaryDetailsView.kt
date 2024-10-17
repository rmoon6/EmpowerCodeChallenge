package com.example.emergecodechallenge

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class BeneficiaryDetailsView(context: Context, beneficiary: Beneficiary) : LinearLayout(context) {

    init {
        orientation = VERTICAL
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // Create and add text views for each beneficiary detail
        addView(createDetailTextView("Name: ${beneficiary.firstName} ${beneficiary.lastName}"))
        addView(createDetailTextView("Date of Birth: ${beneficiary.dateOfBirth}"))
        addView(createDetailTextView("Phone: ${beneficiary.phoneNumber}"))
        addView(createDetailTextView("Address: ${beneficiary.beneficiaryAddress.firstLineMailing}"))
    }

    private fun createDetailTextView(detail: String): TextView {
        return TextView(context).apply {
            text = detail
            textSize = 16f
            setPadding(16, 16, 16, 16)
        }
    }
}
