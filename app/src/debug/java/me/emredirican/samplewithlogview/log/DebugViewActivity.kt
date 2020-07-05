package me.emredirican.samplewithlogview.log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import me.emredirican.samplewithlogview.R

@AndroidEntryPoint
class DebugViewActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.debug_view_activity)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.container, LogViewFragment.newInstance())
          .commitNow()
    }
  }
}
