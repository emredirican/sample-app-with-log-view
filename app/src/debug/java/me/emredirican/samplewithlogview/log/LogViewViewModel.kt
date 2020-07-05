package me.emredirican.samplewithlogview.log

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import me.emredirican.samplewithlogview.IO
import me.emredirican.samplewithlogview.Main

class LogViewViewModel(
    @Main private val mainScheduler: Scheduler,
    @IO private val ioScheduler: Scheduler,
    private val bufferSize: Int
) : ViewModel() {

  private var logs = mutableListOf<String>()
  private var process: Process? = null

  fun logs(): Flowable<List<String>> {
    val core = Flowable.create<List<String>>({ emitter ->

      logs.clear()
      process = Runtime.getRuntime().exec("logcat")
      val reader = process!!.inputStream.bufferedReader()

      var line = reader.readLine()
      while (line != null) {
        if (logs.size == bufferSize) {
          logs.removeAt(0)
        }
        logs.add(line)
        line = if (!emitter.isCancelled) {
          emitter.onNext(logs.toList())
          reader.readLine()
        } else {
          null
        }
      }
      emitter.onComplete()
      return@create
    }, BackpressureStrategy.BUFFER)
    return core
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .doOnCancel {
          process?.destroy()
          process = null
        }
  }
}
