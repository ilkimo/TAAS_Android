package com.example.datahubapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.model.UserData

import com.example.datahubapp.databinding.ItemTopicBinding



/**
 * [RecyclerView.Adapter] that can display a [Topic].
 * TODO: Replace the implementation with code for your data type.
 */
class TopicsRecyclerViewAdapter(
    private val topics: LiveData<UserData>
) : RecyclerView.Adapter<TopicsRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemTopicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = topics.value!!.topicList[position]
        holder.idView.text = "${position}: ${item.name}"
        //holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = topics.value?.topicList?.size ?: 0

    inner class ViewHolder(binding: ItemTopicBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val idView: TextView = binding.itemNumber
        //val contentView: TextView = binding.content

        init {
            binding.root.setOnClickListener(this);
        }

        override fun toString(): String {
            return super.toString() + " '" + idView.text + "'"
        }

        override fun onClick(view: View) {
            Toast.makeText(view.context, "You clicked $layoutPosition", Toast.LENGTH_SHORT).show()
        }
    }

    //----------------------------------------------------------------------------------------------
    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemTopicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = "Topic ${item.id}"
        //holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        //val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + idView.text + "'"
        }
    }*/

}