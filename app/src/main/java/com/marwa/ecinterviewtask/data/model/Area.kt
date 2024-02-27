// Description: This file contains the data class for the com.marwa.ecinterviewtask.data.model.Area object.
// Path: Area.kt
// created by Marwa Talaat on 25/2/2024
package com.marwa.ecinterviewtask.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class Area(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("flag") var flag: String? = null

):Parcelable