package com.example.emergecodechallenge

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Locale

class BeneficiaryDetailsView(
    context: Context,
    beneficiary: Beneficiary
) : LinearLayout(context) {

    init {
        orientation = VERTICAL
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        addView(createDetailTextView("Name: ${beneficiary.firstName} ${beneficiary.lastName}"))
        addView(createDetailTextView("SSN: ${beneficiary.socialSecurityNumber}"))
        addView(createDetailTextView("Date of Birth: ${formatDateOfBirth(beneficiary.dateOfBirth)}"))
        addView(createDetailTextView("Phone: ${formatPhoneNumber(beneficiary.phoneNumber)}"))
        addView(createDetailTextView("Address: ${beneficiary.beneficiaryAddress.firstLineMailing}, " +
                "${beneficiary.beneficiaryAddress.city}, " +
                "${beneficiary.beneficiaryAddress.stateCode} ${beneficiary.beneficiaryAddress.zipCode}"))
    }

    private fun createDetailTextView(detail: String): TextView {
        return TextView(context).apply {
            text = detail
            textSize = 16f
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(16, 16, 16, 16)
        }
    }

    private fun formatDateOfBirth(dob: String): String {
        return try {
            val inputFormat = SimpleDateFormat("MMddyyyy", Locale.US)
            val outputFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            val date = inputFormat.parse(dob)
            outputFormat.format(date ?: "")
        } catch (e: Exception) {
            "Invalid Date"
        }
    }

    private fun formatPhoneNumber(phone: String): String {
        return if (phone.length == 10) {
            "${phone.substring(0, 3)}-${phone.substring(3, 6)}-${phone.substring(6)}"
        } else {
            phone
        }
    }
}
