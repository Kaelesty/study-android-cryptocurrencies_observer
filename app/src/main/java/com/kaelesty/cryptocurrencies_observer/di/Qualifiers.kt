package com.kaelesty.cryptocurrencies_observer.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppContextQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityContextQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LifecycleOwnerQualifier

