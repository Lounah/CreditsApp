package com.lounah.creditsapp.di.common.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lounah.creditsapp.BuildConfig
import com.lounah.creditsapp.data.network.CreditsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
            .setLenient()
            .create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(CreditsApi::class.java)

}