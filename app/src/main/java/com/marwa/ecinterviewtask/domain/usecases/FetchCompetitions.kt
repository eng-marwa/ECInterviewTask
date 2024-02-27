package com.marwa.ecinterviewtask.domain.usecases

import com.marwa.ecinterviewtask.data.datasource.remote.api.NetworkResource
import com.marwa.ecinterviewtask.data.model.CompetitionsResponse
import com.marwa.ecinterviewtask.domain.repository.ICompetitionsRepository
import kotlinx.coroutines.flow.Flow

class FetchCompetitions(private val iCompetitionsRepository: ICompetitionsRepository) {
    suspend operator fun invoke(): Flow<NetworkResource<CompetitionsResponse>> {
        return iCompetitionsRepository.getRemoteCompetitions()
    }

}