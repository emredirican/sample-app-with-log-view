package me.emredirican.samplewithlogview

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class EarthQuakeListenerModule {

  @Binds
  abstract fun bindListener(impl: DebugEarthQuakeListener): EarthQuakeListener
}
