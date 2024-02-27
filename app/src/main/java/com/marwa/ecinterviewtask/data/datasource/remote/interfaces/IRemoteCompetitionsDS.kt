package com.marwa.ecinterviewtask.data.datasource.remote.interfaces

import com.marwa.ecinterviewtask.data.datasource.remote.api.NetworkResource
import com.marwa.ecinterviewtask.data.model.CompetitionsResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteCompetitionsDS {
    suspend fun getCompetitions(): Flow<NetworkResource<CompetitionsResponse>>
}
