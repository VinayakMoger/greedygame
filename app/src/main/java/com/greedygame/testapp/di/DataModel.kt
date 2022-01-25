package com.greedygame.testapp.di

import android.content.Context
import com.greedygame.testapp.BuildConfig.BASEURL
import com.google.gson.GsonBuilder
import com.greedygame.testapp.data.local.AppDatabase
import com.greedygame.testapp.data.local.NewsDao
import com.greedygame.testapp.data.remote.RemoteDataSource
import com.greedygame.testapp.data.remote.WebService
import com.greedygame.testapp.data.repository.NewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideWebService(): WebService {
        val gSONBuilder = GsonBuilder()
            .setLenient()
            .create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gSONBuilder))
            .build().create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(webservice: WebService) = RemoteDataSource(webservice)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.newsDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,
                          localDataSource: NewsDao) =
        NewRepository(remoteDataSource, localDataSource)
}