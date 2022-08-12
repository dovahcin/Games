package com.android.games.di

import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.android.games.data.DetailsRepository
import com.android.games.data.GamesRepository
import com.android.games.data.SearchRepository
import com.android.games.data.db.GamesDatabase
import com.android.games.data.network.ApiServices
import com.android.games.presentation.features.gamesdetails.DetailsViewModel
import com.android.games.presentation.features.gameslist.GamesViewModel
import com.android.games.presentation.features.search.SearchViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.rawg.io/api/")
            .client(
                OkHttpClient.Builder()
                    .readTimeout(15.toLong(), TimeUnit.SECONDS)
                    .connectTimeout(15.toLong(), TimeUnit.SECONDS)
                    .build()
            ).build()
            .create(ApiServices::class.java)
    }
}
val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            GamesDatabase::class.java,
            "games_db"
        ).build().searchHistory()
    }
}

val stateModule = module {
    single { SavedStateHandle() }
}

val mainModule = module {
    viewModel { GamesViewModel(get(), get()) }
    single { GamesRepository(get()) }
    viewModel { DetailsViewModel(get(), get()) }
    single { DetailsRepository(get()) }
    viewModel { SearchViewModel(get(), get()) }
    single { SearchRepository(get(), get()) }
}