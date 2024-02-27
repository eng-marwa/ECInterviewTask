package com.marwa.ecinterviewtask.domain.repository

import com.marwa.ecinterviewtask.data.datasource.remote.api.NetworkResource
import com.marwa.ecinterviewtask.data.model.CompetitionsResponse
import kotlinx.coroutines.flow.Flow

interface ICompetitionsRepository {
    suspend fun getRemoteCompetitions(): Flow<NetworkResource<CompetitionsResponse>>
    fun cacheCompetitionsData(data: CompetitionsResponse)
    fun getCachedCompetitionsData(): CompetitionsResponse?

}