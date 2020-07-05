package me.emredirican.samplewithlogview

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import me.emredirican.samplewithlogview.log.DebugViewActivity
import javax.inject.Inject

@ActivityScoped
class DebugEarthQuakeListener @Inject constructor(
    @ActivityContext private val context: Context
) : EarthQuakeListener {

  override fun onEarthQuake() {
    context.startActivity(Intent(context, DebugViewActivity::class.java))
  }
}
