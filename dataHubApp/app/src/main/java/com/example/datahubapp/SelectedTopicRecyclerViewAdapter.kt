package com.example.datahubapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.datahubapp.data.model.Registration
import com.example.datahubapp.data.model.Topic

import com.example.datahubapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.datahubapp.databinding.FragmentSelectedTopicBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class SelectedTopicRecyclerViewAdapter(
    private val selectedTopic: Topic
) : RecyclerView.Adapter<SelectedTopicRecyclerViewAdapter.ViewHolder>() {
    var registrationsList: ArrayList<Registration> = selectedTopic.listRegistrazioni

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentSelectedTopicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = registrationsList[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.creationDate.toString()
    }

    override fun getItemCount(): Int = registrationsList.size

    inner class ViewHolder(binding: FragmentSelectedTopicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.date

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}