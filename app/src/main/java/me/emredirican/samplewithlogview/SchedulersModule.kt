package me.emredirican.samplewithlogview

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Qualifier

@Module
@InstallIn(ApplicationComponent::class)
object SchedulersModule {

  @Provides
  @IO
  fun provideIoScheduler(): Scheduler {
    return Schedulers.io()
  }

  @Provides
  @Main
  fun provideMainScheduler(): Scheduler {
    return AndroidSchedulers.mainThread()
  }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IO

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Main

