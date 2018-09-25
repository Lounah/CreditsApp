package com.lounah.creditsapp.di.common

import com.lounah.creditsapp.app.CreditsApplication

object AppInjector {

    fun init(app: CreditsApplication) {
        DaggerAppComponent
                .builder()
                .application(app)
                .appContext(app)
                .build()
                .inject(app)
    }
}