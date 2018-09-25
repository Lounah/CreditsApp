package com.lounah.creditsapp.di.common

import android.app.Application
import android.content.Context
import com.lounah.creditsapp.app.CreditsApplication
import com.lounah.creditsapp.di.common.modules.AppModule
import com.lounah.creditsapp.di.common.modules.NetworkModule
import com.lounah.creditsapp.di.creditsapp.CreditsAppBindingModule
import com.lounah.creditsapp.di.creditsapp.CreditsAppDomainModule
import com.lounah.creditsapp.di.creditsapp.CreditsAppRepositoriesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivitiesBuildersModule::class,
    AppModule::class,
    NetworkModule::class,
    CreditsAppRepositoriesModule::class,
    CreditsAppDomainModule::class,
    CreditsAppBindingModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun appContext(@ApplicationContext context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: CreditsApplication)
}