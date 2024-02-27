package com.marwa.ecinterviewtask.data.datasource.local

import android.content.SharedPreferences
import com.marwa.ecinterviewtask.data.model.CompetitionsResponse
import com.marwa.ecinterviewtask.utils.extensions.fromJson
import com.marwa.ecinterviewtask.utils.extensions.json


class PreferenceHelper(private val sharedPreferences: SharedPreferences) {

    //region var
    private val COMPETITION = "competitionData"
    var competitionData: CompetitionsResponse?
        get() = sharedPreferences.getString(COMPETITION, null).fromJson()
        set(value) {
            sharedPreferences[COMPETITION] = value.json()
        }
    //endregion


}