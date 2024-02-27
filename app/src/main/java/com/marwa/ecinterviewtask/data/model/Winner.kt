package com.marwa.ecinterviewtask.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Winner(
    var id: Int? = null,
    var name: String? = null,
    var shortName: String? = null,
    var tla: String? = null,
    var crest: String? = null,
    var address: String? = null,
    var website: String? = null,
    var founded: Int? = null,
    var clubColors: String? = null,
    var venue: String? = null,
    var lastUpdated: String? = null
) : Parcelable
