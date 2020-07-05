package me.emredirican.samplewithlogview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.seismic.ShakeDetector
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import me.emredirican.samplewithlogview.ui.main.MainFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ShakeDetector.Listener {

  @Inject
  lateinit var earthQuakeListener: EarthQuakeListener

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.container, MainFragment.newInstance())
          .commitNow()
    }
    Observable.interval(500, TimeUnit.MILLISECONDS, Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          Log.d("MainActivity", "Logging..${it}")
        }
    earthQuakeListener.onEarthQuake()
  }

  override fun hearShake() {
    earthQuakeListener.onEarthQuake()
  }
}
