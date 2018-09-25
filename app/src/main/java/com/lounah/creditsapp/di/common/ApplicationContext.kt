package com.lounah.creditsapp.di.common

import javax.inject.Qualifier

@Target(AnnotationTarget.VALUE_PARAMETER)
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext