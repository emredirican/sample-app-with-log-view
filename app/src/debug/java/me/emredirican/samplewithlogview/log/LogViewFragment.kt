package me.emredirican.samplewithlogview.log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import me.emredirican.samplewithlogview.R
import javax.inject.Inject

@AndroidEntryPoint
class LogViewFragment : Fragment() {

  companion object {
    fun newInstance() = LogViewFragment()
  }

  @Inject
  lateinit var viewModelFactory: LogViewModelFactory

  private lateinit var viewModel: LogViewViewModel
  private lateinit var adapter: LogAdapter
  private val disposables = CompositeDisposable()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.log_view_fragment, container, false)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    adapter = LogAdapter()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(this, viewModelFactory).get(LogViewViewModel::class.java)
    val listView = view.findViewById<RecyclerView>(R.id.rv_log_view)
    (listView.layoutManager as LinearLayoutManager).stackFromEnd = true
    listView.adapter = adapter
  }

  override fun onResume() {
    super.onResume()
    val disposable = viewModel.logs().subscribe { logs ->
      adapter.submitList(logs)
//      requireView().findViewById<RecyclerView>(R.id.rv_log_view).layoutManager!!.scrollToPosition(
//          adapter.itemCount - 1)
    }
    disposables.add(disposable)
  }

  override fun onPause() {
    disposables.clear()
    super.onPause()
  }

  override fun onDestroy() {
    disposables.dispose()
    super.onDestroy()
  }
}
