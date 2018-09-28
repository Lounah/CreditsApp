package com.lounah.creditsapp.di.creditsapp

import android.app.Application
import android.arch.persistence.room.Room
import com.lounah.creditsapp.BuildConfig
import com.lounah.creditsapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CreditsAppRepositoriesModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Application) =
            Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

    @Singleton
    @Provides
    fun provideCreditOfferDao(db: AppDatabase) = db.creditOffersDao()
}
