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
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.model.UserData
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory

/**
 * A fragment representing a list of Items.
 */
class TopicsFragment : Fragment() {
    private var columnCount = 1
    lateinit var model: AppViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var viewModelFactory = AppViewModelFactory(requireContext())
        model = ViewModelProviders.of(requireActivity(), viewModelFactory).get(AppViewModel::class.java)

        //model = AppViewModelFactory(context).create(AppViewModel::class.java)
        //model = ViewModelProviders.of(this).get(AppViewModel::class.java)
        //model = ViewModelProvider(requireActivity()).get(AppViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_topics, container, false)

        // Set the adapter
        with(view.findViewById(R.id.list) as RecyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

            adapter = TopicsRecyclerViewAdapter(model.getUserData())
            model.getUserData().observe(viewLifecycleOwner, Observer<UserData>{ userData ->
                // update UI
                adapter?.let{it.notifyDataSetChanged()} //TODO verificare se funziona!
            })
        }

        return view
    }
}