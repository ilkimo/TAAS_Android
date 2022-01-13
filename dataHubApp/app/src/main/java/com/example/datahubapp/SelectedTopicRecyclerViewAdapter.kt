package com.example.datahubapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.datahubapp.data.model.Registration
import com.example.datahubapp.data.model.Topic

import com.example.datahubapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.datahubapp.databinding.FragmentSelectedTopicBinding
import com.example.datahubapp.databinding.RegistrationTopicItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class SelectedTopicRecyclerViewAdapter(
    selectedTopic: Topic,
    var fragment: SelectedTopicFragment
) : RecyclerView.Adapter<SelectedTopicRecyclerViewAdapter.ViewHolder>() {
    var registrationsList: ArrayList<Registration> = selectedTopic.listRegistrazioni

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RegistrationTopicItemBinding.inflate(
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

    inner class ViewHolder(binding: RegistrationTopicItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val idView: TextView = binding.registrationName
        val contentView: TextView = binding.registrationDate

        init {
            binding.root.setOnClickListener(this)
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }

        override fun onClick(view: View) {
            //Toast.makeText(view.context, "You clicked $layoutPosition", Toast.LENGTH_SHORT).show()

            var navigationDirection: NavDirections = SelectedTopicFragmentDirections.actionSelectedTopicFragmentToSelectedRegistrationFragment(idView.text.toString().toLong())
            NavHostFragment.findNavController(fragment).navigate(navigationDirection)

            /*
            NavDirections navi = TopicsFragmentDirections.action_topicsFragment_to_selectedTopicFragment("prova");
            NavHostFragment.findNavController(this).navigate(navi);
             */
        }
    }

}