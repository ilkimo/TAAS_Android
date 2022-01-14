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
import com.example.datahubapp.data.model.Registration
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory
import com.example.datahubapp.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class SelectedRegistrationFragment : Fragment() {

    private var columnCount = 1
    lateinit var model: AppViewModel
    private val REGISTRATION_ID_ARGUMENT: String = "id"
    private val TOPIC_NAME_ARGUMENT: String = "topic-name"
    private lateinit var selectedRegistration: Registration

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var viewModelFactory = AppViewModelFactory(requireContext())
        model = ViewModelProviders.of(requireActivity(), viewModelFactory).get(AppViewModel::class.java)
        var selectedTopic: Topic

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

        val topicList = model.getUserData().value?.topicList?.filter{
            it.name.equals(arguments?.getString(TOPIC_NAME_ARGUMENT))
        } as ArrayList<Topic>?

        if((topicList?.size ?: -1) > 0) {
            //bind to topic
            selectedTopic = topicList!![0]
        } else {
            throw Error("invalid topic selected")
        }

        Log.d("MY_TEST", "ecco --> ${arguments?.getLong(REGISTRATION_ID_ARGUMENT)}")

        var listRegistrations = selectedTopic.listRegistrazioni.filter{
            it.id == (arguments?.getLong(REGISTRATION_ID_ARGUMENT) ?: -1L)
        }

        if(listRegistrations.isNotEmpty()) {
            selectedRegistration = listRegistrations[0]
        } else {
            throw Error("Problem with the selected Registration ID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_selected_registration_list, container, false)
        val view = root.findViewById<RecyclerView>(R.id.list)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = SelectedRegistrationRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textViewID).text = "${getString(R.string.ID)}: ${selectedRegistration.id}"
        view.findViewById<TextView>(R.id.textViewDate).text = "${getString(R.string.Date)}: ${selectedRegistration.creationDate}"
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            SelectedRegistrationFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}