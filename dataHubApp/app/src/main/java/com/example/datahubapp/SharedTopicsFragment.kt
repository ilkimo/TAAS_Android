package com.example.datahubapp

import android.content.Context
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
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.datahubapp.controller.addSharedTopic
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory

/**
 * A fragment representing a list of Items.
 */
class SharedTopicsFragment : Fragment() {

    private var columnCount = 1
    lateinit var model: AppViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var viewModelFactory = AppViewModelFactory(requireContext())
        model = ViewModelProviders.of(requireParentFragment(), viewModelFactory).get(AppViewModel::class.java)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shared_topics_list, container, false)

        // Set the adapter
        /*
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = SharedTopicsRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }

         */
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewAdapter(view.findViewById(R.id.list) as RecyclerView)
        addOnClickListeners(view, context)

        //Set back arrow visible and enabled
        (activity as AppCompatActivity?)?.getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)?.getSupportActionBar()?.setDisplayShowHomeEnabled(false)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setRecyclerViewAdapter(view: RecyclerView) {
        with(view) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

            adapter = model.getSharedTopics().value?.let{SharedTopicsRecyclerViewAdapter(it as ArrayList<Topic>, this@SharedTopicsFragment)} ?: SharedTopicsRecyclerViewAdapter(ArrayList(), this@SharedTopicsFragment)
            model.getSharedTopics().observe(viewLifecycleOwner, Observer<List<Topic>>{
                // update UI
                with(adapter as SharedTopicsRecyclerViewAdapter) {
                    updateSharedTopicList((model.getSharedTopics().value ?: ArrayList()) as ArrayList<Topic>)
                }
            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addOnClickListeners(root: View, context: Context?) {
        var addTopic: ImageButton = root.findViewById(R.id.addTopicButton)

        addTopic.setOnClickListener { root ->
            if (context != null) {
                addSharedTopic(Topic("SHARED new_topic", "description", null, null, false), requireParentFragment(), context)
            } else {
                Log.d("ERROR", "TopicsFragment.addClickListeners")
                throw Error("Error: No context for this event")
            }
        }
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            SharedTopicsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}