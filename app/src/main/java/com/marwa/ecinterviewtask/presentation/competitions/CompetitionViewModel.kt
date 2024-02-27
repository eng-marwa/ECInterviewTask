package com.marwa.ecinterviewtask.presentation.competitions

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.marwa.ecinterviewtask.base.BaseViewModel
import com.marwa.ecinterviewtask.data.datasource.remote.api.NetworkStatus
import com.marwa.ecinterviewtask.data.model.CompetitionsResponse
import com.marwa.ecinterviewtask.domain.usecases.CacheCompetitions
import com.marwa.ecinterviewtask.domain.usecases.FetchCompetitions
import com.marwa.ecinterviewtask.domain.usecases.GetCachedCompetitions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CompetitionViewModel(
    private val fetchCompetitions: FetchCompetitions,
    private val cacheCompetitions: CacheCompetitions,
    private val cachedCompetitions: GetCachedCompetitions
) : BaseViewModel() {

    val fetchedCompetitionsState =
        MutableStateFlow<ViewState<CompetitionsResponse>>(ViewState.Empty())

    fun fetchCompetition() {
        viewModelScope.launch {
            fetchCompetitions().collect {
                val state: ViewState<CompetitionsResponse> = when (it.status) {
                    is NetworkStatus.Success -> {
                        cacheCompetitionsResponse(it.payload?.data!!)
                        ViewState.Loaded(data = it.payload.data!!)
                    }
                    is NetworkStatus.Failure -> ViewState.Failed(it.error)
                    else -> ViewState.Empty()
                }
                fetchedCompetitionsState.value = state
            }
        }
    }

    private fun cacheCompetitionsResponse(data: CompetitionsResponse) {
        Log.d("CTAG1", "cacheCompetitionsResponse: $data")
        cacheCompetitions.cacheResponse(data)
    }

    fun getCachedCompetitionsResponse(): CompetitionsResponse? {
        Log.d("CTAG2", "cacheCompetitionsResponse: ${cachedCompetitions.getCacheResponse()}")
        return cachedCompetitions.getCacheResponse()
    }

}