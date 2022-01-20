package com.example.datahubapp

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory

import com.example.datahubapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.datahubapp.databinding.TopicItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class SharedTopicsRecyclerViewAdapter(
    private var sharedTopicList: ArrayList<Topic> = ArrayList(),
    var fragment: SharedTopicsFragment

) : RecyclerView.Adapter<SharedTopicsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            TopicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), fragment
        )

    }

    fun updateSharedTopicList(topicList: ArrayList<Topic>) {
        //this.topicList.clear()
        this.sharedTopicList = topicList.clone() as ArrayList<Topic>
        notifyDataSetChanged()
        Log.d("testina", "updateSharedTopicList")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = sharedTopicList[holder.bindingAdapterPosition]
        holder.topicName.text = "${holder.bindingAdapterPosition}: ${item.name}"
        holder.justTopicName = item.name
    }

    override fun getItemCount(): Int = sharedTopicList.size

    inner class ViewHolder(binding: TopicItemBinding, fragment: SharedTopicsFragment) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val topicName: TextView = binding.topicName
        var model: AppViewModel
        lateinit var justTopicName: String

        init {
            var viewModelFactory = AppViewModelFactory(fragment.requireContext())
            model = ViewModelProviders.of(fragment.requireParentFragment(), viewModelFactory).get(
                AppViewModel::class.java
            )

            binding.root.setOnClickListener(this);
        }

        override fun toString(): String {
            return super.toString() + " '" + topicName.text + "'"
        }

        override fun onClick(view: View) {
            //Toast.makeText(requireContext(), "Logged In", Toast.LENGTH_LONG).show()
            var navigationDirection: NavDirections = SharedTopicsFragmentDirections.actionSharedTopicsFragmentToSelectedSharedTopicFragment(justTopicName)
            NavHostFragment.findNavController(fragment).navigate(navigationDirection)
        }
    }

    /*
    inner class ViewHolder(binding: FragmentSharedTopicsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

     */

}