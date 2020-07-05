package me.emredirican.samplewithlogview.log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.scopes.FragmentScoped
import io.reactivex.rxjava3.core.Scheduler
import me.emredirican.samplewithlogview.IO
import me.emredirican.samplewithlogview.Main
import javax.inject.Inject

@FragmentScoped
class LogViewModelFactory @Inject constructor(
    @IO private val ioScheduler: Scheduler,
    @Main private val mainScheduler: Scheduler
) : ViewModelProvider.NewInstanceFactory() {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return LogViewViewModel(
        mainScheduler = mainScheduler,
        ioScheduler = ioScheduler,
        bufferSize = 100
    ) as T
  }
}
