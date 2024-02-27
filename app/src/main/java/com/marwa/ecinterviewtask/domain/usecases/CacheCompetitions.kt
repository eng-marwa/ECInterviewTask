package com.marwa.ecinterviewtask.domain.usecases

import com.marwa.ecinterviewtask.data.model.CompetitionsResponse
import com.marwa.ecinterviewtask.domain.repository.ICompetitionsRepository

class CacheCompetitions(private val iCompetitionsRepository: ICompetitionsRepository) {
    fun cacheResponse(data: CompetitionsResponse) {
        iCompetitionsRepository.cacheCompetitionsData(data)
    }

}
