// Description: This file contains the data class for the CurrentSeason object.
// Path: CurrentSeason.kt
// created by Marwa Talaat on 25/2/2024
package com.marwa.ecinterviewtask.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentSeason(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("startDate") var startDate: String? = null,
    @SerializedName("endDate") var endDate: String? = null,
    @SerializedName("currentMatchday") var currentMatchday: Int? = null,
    @SerializedName("winner") var winner: Winner? = null

) : Parcelable