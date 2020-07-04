package me.emredirican.samplewithlogview

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object EarthQuakeListenerModule {

  @Provides
  @Singleton
  fun provideListener(): EarthQuakeListener {
    return NoOpEarthQuakeListener
  }

  object NoOpEarthQuakeListener: EarthQuakeListener {
    override fun onEarthQuake() {
      //do nothing
    }
  }
}
