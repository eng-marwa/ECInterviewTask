package com.marwa.ecinterviewtask.data.datasource.remote.api

sealed class NetworkStatus {
    data object Success: NetworkStatus()
    data object Failure: NetworkStatus()
    data object Loading: NetworkStatus()
    data object Idle: NetworkStatus()
}