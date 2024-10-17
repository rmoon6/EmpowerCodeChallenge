package com.example.empowercodechallenge

import org.json.JSONArray

data class Beneficiary(
    val lastName: String,
    val firstName: String,
    val designationCode: String,
    val beneType: String,
    val socialSecurityNumber: String,
    val dateOfBirth: String,
    val middleName: String?,
    val phoneNumber: String,
    val beneficiaryAddress: BeneficiaryAddress
) {

    companion object {
        fun parseListFromJson(jsonString: String): List<Beneficiary> {
            val beneficiaries = mutableListOf<Beneficiary>()

            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)

                val addressObject = jsonObject.getJSONObject("beneficiaryAddress")
                val beneficiaryAddress = BeneficiaryAddress(
                    firstLineMailing = addressObject.getString("firstLineMailing"),
                    scndLineMailing = addressObject.optString("scndLineMailing", null),
                    city = addressObject.getString("city"),
                    zipCode = addressObject.getString("zipCode"),
                    stateCode = addressObject.getString("stateCode"),
                    country = addressObject.getString("country")
                )

                val beneficiary = Beneficiary(
                    lastName = jsonObject.getString("lastName"),
                    firstName = jsonObject.getString("firstName"),
                    designationCode = jsonObject.getString("designationCode"),
                    beneType = jsonObject.getString("beneType"),
                    socialSecurityNumber = jsonObject.getString("socialSecurityNumber"),
                    dateOfBirth = jsonObject.getString("dateOfBirth"),
                    middleName = jsonObject.optString("middleName", null),
                    phoneNumber = jsonObject.getString("phoneNumber"),
                    beneficiaryAddress = beneficiaryAddress
                )

                beneficiaries.add(beneficiary)
            }

            return beneficiaries
        }
    }

    data class BeneficiaryAddress(
        val firstLineMailing: String,
        val scndLineMailing: String?,
        val city: String,
        val zipCode: String,
        val stateCode: String,
        val country: String
    )
}
