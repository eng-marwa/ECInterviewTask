package com.marwa.ecinterviewtask.data.datasource.remote

import com.marwa.ecinterviewtask.base.BaseApiProvider
import com.marwa.ecinterviewtask.data.datasource.remote.api.ApiService
import com.marwa.ecinterviewtask.data.datasource.remote.api.NetworkResource
import com.marwa.ecinterviewtask.data.datasource.remote.interfaces.IRemoteCompetitionsDS
import com.marwa.ecinterviewtask.data.model.CompetitionsResponse
import kotlinx.coroutines.flow.Flow

class RemoteCompetitionsDSImpl(private val apiService: ApiService) : IRemoteCompetitionsDS, BaseApiProvider() {
    override suspend fun getCompetitions(): Flow<NetworkResource<CompetitionsResponse>> {
       return apiRequest { apiService.getCompetitions() }
    }


}