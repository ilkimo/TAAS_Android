package com.example.datahubapp

import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.model.UserData
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory

import com.example.datahubapp.databinding.ItemTopicBinding
import com.example.datahubapp.databinding.TopicItemBinding


/**
 * [RecyclerView.Adapter] that can display a [Topic].
 * TODO: Replace the implementation with code for your data type.
 */
class TopicsRecyclerViewAdapter(
    private var topicList: ArrayList<Topic> = ArrayList(),
    var fragment: TopicsFragment
) : RecyclerView.Adapter<TopicsRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            TopicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = topicList[holder.bindingAdapterPosition]
        holder.topicName.text = item.name
        holder.topicColor.setBackgroundColor(Color.parseColor(item.color[1]))
    }

    override fun getItemCount(): Int = topicList.size

    fun updateTopicList(topicList: ArrayList<Topic>) {
        //this.topicList.clear()
        this.topicList = topicList.clone() as ArrayList<Topic>
        notifyDataSetChanged()
        Log.d("testina", "updateTopicList")
    }

    inner class ViewHolder(binding: TopicItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val topicName: TextView = binding.topicName
        val topicColor: LinearLayout = binding.cardColor

        var model: AppViewModel

        init {
            var viewModelFactory = AppViewModelFactory(fragment.requireContext())
            model = ViewModelProviders.of(fragment.requireActivity(), viewModelFactory).get(AppViewModel::class.java)

            binding.root.setOnClickListener(this)
        }

        override fun toString(): String {
            return super.toString() + " '" + topicName.text + "'"
        }

        override fun onClick(view: View) {
            //Toast.makeText(view.context, "You clicked $layoutPosition", Toast.LENGTH_SHORT).show()

            var navigationDirection: NavDirections = TopicsFragmentDirections.actionTopicsFragmentToSelectedTopicFragment(topicName.text as String);
            NavHostFragment.findNavController(fragment).navigate(navigationDirection)

            /*
            NavDirections navi = TopicsFragmentDirections.action_topicsFragment_to_selectedTopicFragment("prova");
            NavHostFragment.findNavController(this).navigate(navi);
             */
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