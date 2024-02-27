// Description: This file contains the data class for the CompetitionsResponse object.
// Path: CompetitionsResponse.kt
// created by Marwa Talaat on 25/2/2024
package com.marwa.ecinterviewtask.data.model

import com.google.gson.annotations.SerializedName


data class CompetitionsResponse(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("competitions") var competitions: ArrayList<Competitions> = arrayListOf()

)