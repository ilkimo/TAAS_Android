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
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.datahubapp.controller.addTopic
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.model.UserData
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory
import com.example.datahubapp.databinding.FragmentTopicsBinding

/**
 * A fragment representing a list of Items.
 */
class TopicsFragment : Fragment() {
    private var columnCount = 1
    private var _binding: FragmentTopicsBinding? = null

    lateinit var model: AppViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var viewModelFactory = AppViewModelFactory(requireContext())
        model = ViewModelProviders.of(requireParentFragment(), viewModelFactory).get(AppViewModel::class.java)

        arguments?.let {
            columnCount = it.getInt(SelectedTopicFragment.ARG_COLUMN_COUNT)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopicsBinding.inflate(inflater, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewAdapter(view.findViewById(R.id.list) as RecyclerView)
        addOnClickListeners(view, context)

        //Set back arrow visible and enabled
        (activity as AppCompatActivity?)?.getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)?.getSupportActionBar()?.setDisplayShowHomeEnabled(false)

        val addTopicButton = binding.addTopicButton

        addTopicButton.setOnClickListener {
            var navigationDirection: NavDirections = TopicsFragmentDirections.actionTopicsFragmentToAddTopicFragment();
            NavHostFragment.findNavController(this).navigate(navigationDirection)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addOnClickListeners(root: View, context: Context?) {
        var addTopic: ImageButton = root.findViewById(R.id.addTopicButton)

        addTopic.setOnClickListener { root ->
            if (context != null) {
                addTopic(Topic("new_topic", "description", null, null, false), requireParentFragment(), context)
            } else {
                Log.d("ERROR", "TopicsFragment.addClickListeners")
                throw Error("Error: No context for this event")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setRecyclerViewAdapter(view: RecyclerView) {
        with(view) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

            adapter = model.getUserData().value?.topicList?.let{TopicsRecyclerViewAdapter(it, this@TopicsFragment)} ?: TopicsRecyclerViewAdapter(ArrayList(), this@TopicsFragment)
            model.getUserData().observe(viewLifecycleOwner, Observer<UserData>{
                // update UI
                with(adapter as TopicsRecyclerViewAdapter) {
                    updateTopicList(model.getUserData().value?.topicList ?: ArrayList())
                }
            })
        }
    }
}