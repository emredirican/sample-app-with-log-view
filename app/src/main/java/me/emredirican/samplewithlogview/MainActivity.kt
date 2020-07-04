package me.emredirican.samplewithlogview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.seismic.ShakeDetector
import dagger.hilt.android.AndroidEntryPoint
import me.emredirican.samplewithlogview.ui.main.MainFragment
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ShakeDetector.Listener {

  @Inject lateinit var earthQuakeListener: EarthQuakeListener

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.container, MainFragment.newInstance())
          .commitNow()
    }
  }

  override fun hearShake() {
    earthQuakeListener.onEarthQuake()
  }
}
