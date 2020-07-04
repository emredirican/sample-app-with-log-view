package me.emredirican.samplewithlogview.log

import android.util.Log
import me.emredirican.samplewithlogview.EarthQuakeListener
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DebugEarthQuakeListener @Inject constructor() : EarthQuakeListener {

  override fun onEarthQuake() {
    Log.d("EarthQuakeListener", "Earth quake detected!")
  }
}
