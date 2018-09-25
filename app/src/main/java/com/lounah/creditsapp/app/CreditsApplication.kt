package com.lounah.creditsapp.app

import android.app.Activity
import android.app.Application
import com.lounah.creditsapp.BuildConfig
import com.lounah.creditsapp.di.common.AppInjector
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CreditsApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        val configBuilder = YandexMetricaConfig.newConfigBuilder(BuildConfig.YANDEX_METRIKA_API_KEY)
        YandexMetrica.activate(applicationContext, configBuilder.build())
        YandexMetrica.enableActivityAutoTracking(this)
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}