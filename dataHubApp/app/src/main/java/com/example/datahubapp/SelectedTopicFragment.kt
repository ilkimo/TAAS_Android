package com.example.datahubapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory
import com.example.datahubapp.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class SelectedTopicFragment : Fragment() {

    private var columnCount = 1
    lateinit var model: AppViewModel
    private val TOPIC_NAME: String = "topicName"
    lateinit var selectedTopic: Topic

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var viewModelFactory = AppViewModelFactory(requireContext())
        model = ViewModelProviders.of(requireActivity(), viewModelFactory).get(AppViewModel::class.java)
        Log.d("TEST_TOPIC", "${arguments?.getString(TOPIC_NAME)}")

        //TODO assert business rule topic.name unique for each user
        var topicList = model.getUserData().value?.topicList?.filter{
            it.name.equals(arguments?.getString(TOPIC_NAME))
        } as ArrayList<Topic>?

        if((topicList?.size ?: -1) > 0) {
            //bind to topic
            selectedTopic = topicList!![0]
        } else {
            throw Error("invalid topic selected")
        }

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_selected_topic_list, container, false)
        val view: RecyclerView = root.findViewById(R.id.list)

        root.findViewById<TextView>(R.id.textView2).text = selectedTopic.name

        // Set the adapter
        if(view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = SelectedTopicRecyclerViewAdapter(selectedTopic, this@SelectedTopicFragment)
            }
        }
        return root
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            SelectedTopicFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}