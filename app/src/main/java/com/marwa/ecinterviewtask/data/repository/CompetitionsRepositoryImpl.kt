package com.marwa.ecinterviewtask.data.repository

import com.marwa.ecinterviewtask.data.datasource.local.PreferenceHelper
import com.marwa.ecinterviewtask.data.datasource.remote.api.NetworkResource
import com.marwa.ecinterviewtask.data.datasource.remote.interfaces.IRemoteCompetitionsDS
import com.marwa.ecinterviewtask.data.model.CompetitionsResponse
import com.marwa.ecinterviewtask.domain.repository.ICompetitionsRepository
import kotlinx.coroutines.flow.Flow

class CompetitionsRepositoryImpl(
    private val iRemoteCompetitionsDS: IRemoteCompetitionsDS,
    private val sharedPrefsHelper: PreferenceHelper
) : ICompetitionsRepository {
    override suspend fun getRemoteCompetitions(): Flow<NetworkResource<CompetitionsResponse>> {
        return iRemoteCompetitionsDS.getCompetitions()
    }

    override fun cacheCompetitionsData(data: CompetitionsResponse) {
        sharedPrefsHelper.competitionData = data
    }

    override fun getCachedCompetitionsData(): CompetitionsResponse? {
        return sharedPrefsHelper.competitionData
    }


}