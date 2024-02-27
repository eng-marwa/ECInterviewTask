package com.marwa.ecinterviewtask.di

import com.marwa.ecinterviewtask.data.datasource.local.PreferenceConfig
import com.marwa.ecinterviewtask.data.datasource.local.PreferenceHelper
import com.marwa.ecinterviewtask.data.datasource.remote.RemoteCompetitionsDSImpl
import com.marwa.ecinterviewtask.data.datasource.remote.api.ApiService
import com.marwa.ecinterviewtask.data.datasource.remote.api.AuthInterceptor
import com.marwa.ecinterviewtask.data.datasource.remote.api.RetrofitClient
import com.marwa.ecinterviewtask.data.datasource.remote.interfaces.IRemoteCompetitionsDS
import com.marwa.ecinterviewtask.data.repository.CompetitionsRepositoryImpl
import com.marwa.ecinterviewtask.domain.repository.ICompetitionsRepository
import com.marwa.ecinterviewtask.domain.usecases.CacheCompetitions
import com.marwa.ecinterviewtask.domain.usecases.FetchCompetitions
import com.marwa.ecinterviewtask.domain.usecases.GetCachedCompetitions
import com.marwa.ecinterviewtask.presentation.competitions.CompetitionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { CompetitionViewModel(get(),get(),get()) }
}
val useCaseModule = module {
    single { FetchCompetitions(get()) }
    single { CacheCompetitions(get()) }
    single { GetCachedCompetitions(get()) }

}
val repositoryModule = module {
    single<ICompetitionsRepository> {
        CompetitionsRepositoryImpl(get(),get())

    }
}
val dataSourceModule = module {

    single {
        RemoteCompetitionsDSImpl(get()) as IRemoteCompetitionsDS
    }
}

val networkModule = module {
    single { AuthInterceptor() }
    single { RetrofitClient.provideOkHttpClient(authInterceptor = get()) }
    single { RetrofitClient.provideRetrofit(okHttpClient = get()) }
    single { ApiService.createApiService(retrofit = get()) }
}


val preferencesModule = module {
    single { PreferenceConfig.provideEncryptedSharedPreferences(androidContext()) }
    single {
        PreferenceHelper(get())
    }

}
