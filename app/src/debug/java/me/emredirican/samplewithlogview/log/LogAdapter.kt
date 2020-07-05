package me.emredirican.samplewithlogview.log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class LogAdapter : ListAdapter<String, LogViewHolder>(LogDiffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
    val view = TextView(parent.context)
    view.setPadding(10, 10, 10 , 10)
    return LogViewHolder(view)
  }

  override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}

class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(text: String) {
    (itemView as TextView).text = text
  }
}

object LogDiffCallback : DiffUtil.ItemCallback<String>() {

  override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
    return oldItem == newItem
  }
}
