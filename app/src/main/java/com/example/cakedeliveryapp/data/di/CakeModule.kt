package com.example.cakedeliveryapp.data.di

import android.app.Application
import android.content.Context
import com.example.cakedeliveryapp.data.local.CakeDao
import com.example.cakedeliveryapp.data.local.CakeDatabase
import com.example.cakedeliveryapp.data.manger.UserEntryImpl
import com.example.cakedeliveryapp.data.remote.ApiService
import com.example.cakedeliveryapp.data.repository.CakeRepositoryImpl
import com.example.cakedeliveryapp.domain.manger.UserEntry
import com.example.cakedeliveryapp.domain.repository.CakeRepository
import com.example.cakedeliveryapp.domain.usecase.appEntry.AppEntryUseCases
import com.example.cakedeliveryapp.domain.usecase.appEntry.ReadAppEntryUseCase
import com.example.cakedeliveryapp.domain.usecase.appEntry.SaveAppEntryUseCase
import com.example.cakedeliveryapp.domain.usecase.cake.CakeDataUseCase
import com.example.cakedeliveryapp.domain.usecase.cake.GetAllCakesUseCase
import com.example.cakedeliveryapp.domain.usecase.cake.GetCakeByIdUseCase
import com.example.cakedeliveryapp.domain.usecase.cake.GetCakesUseCase
import com.example.cakedeliveryapp.domain.usecase.cake.UpdateCakeQuantity
import com.example.cakedeliveryapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CakeModule {

    @Provides
    @Singleton
    fun provideAppEntry(application: Application): UserEntry {
        return UserEntryImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCase(userEntry: UserEntry) =
        AppEntryUseCases(
            saveAppEntryUseCases = SaveAppEntryUseCase(userEntry),
            readAppEntryUseCases = ReadAppEntryUseCase(userEntry)

        )
    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) :ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCakeDatabase(@ApplicationContext context: Context) : CakeDatabase{
        return CakeDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideCakeDao(cakeDatabase: CakeDatabase) : CakeDao{
        return cakeDatabase.getCakeDao()
    }
    @Provides
    @Singleton
    fun provideCakeRepository(apiService: ApiService,cakeDao: CakeDao) : CakeRepository{
        return CakeRepositoryImpl(apiService,cakeDao)
    }
    @Provides
    fun provideCakeDataUseCase(repository: CakeRepository) =
        CakeDataUseCase(
            getCakesUseCases = GetCakesUseCase(repository),
            getAllCakesUseCase = GetAllCakesUseCase(repository),
            getCakeById = GetCakeByIdUseCase(repository),
            updateCakeQuantity = UpdateCakeQuantity(repository)

        )


}

