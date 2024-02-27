package com.marwa.ecinterviewtask.domain.usecases

import com.marwa.ecinterviewtask.data.model.CompetitionsResponse
import com.marwa.ecinterviewtask.domain.repository.ICompetitionsRepository

class GetCachedCompetitions(private val iCompetitionsRepository: ICompetitionsRepository) {
    fun getCacheResponse(): CompetitionsResponse? {
        return iCompetitionsRepository.getCachedCompetitionsData()
    }

}
