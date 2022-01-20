package com.example.datahubapp

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.datahubapp.data.model.Topic

import com.example.datahubapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.datahubapp.databinding.FragmentSelectedSharedTopicBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class SelectedSharedTopicRecyclerViewAdapter(
    private val topic: Topic
): RecyclerView.Adapter<SelectedSharedTopicRecyclerViewAdapter.ViewHolder>() {
    private val TAG = "SelectedSharedTopicAdap"

    init {
        Log.d("$TAG", "List of nametype elements for topic:${topic.name}")
        topic.nameType.forEach{ elem ->
            Log.d("$TAG", "${elem.name} : ${elem.data}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentSelectedSharedTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = topic.nameType[position].name
        val dataType = topic.nameType[position].data

        holder.nameTextView.text = name
        holder.dataTypeTextView.text = dataType
    }

    override fun getItemCount(): Int = topic.nameType.size

    inner class ViewHolder(binding: FragmentSelectedSharedTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView: TextView = binding.name
        val dataTypeTextView: TextView = binding.dataType

        override fun toString(): String {
            return super.toString() + "${nameTextView.text}:${dataTypeTextView.text}"
        }
    }
}