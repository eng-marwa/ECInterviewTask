// Description: This file contains the data class for the Competitions object.
// Path: Competitions.kt
// created by Marwa Talaat on 25/2/2024
package com.marwa.ecinterviewtask.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Competitions(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("area") var area: Area? = Area(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("emblem") var emblem: String? = null,
    @SerializedName("plan") var plan: String? = null,
    @SerializedName("currentSeason") var currentSeason: CurrentSeason? = CurrentSeason(),
    @SerializedName("numberOfAvailableSeasons") var numberOfAvailableSeasons: Int? = null,
    @SerializedName("lastUpdated") var lastUpdated: String? = null

) : Parcelable