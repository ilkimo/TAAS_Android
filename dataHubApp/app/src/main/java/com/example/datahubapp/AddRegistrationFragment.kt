package com.example.datahubapp

import android.os.Build
import android.os.Bundle
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
import java.time.LocalDate

/**
 * A fragment representing a list of Items.
 */
class AddRegistrationFragment : Fragment() {

    private var columnCount = 1
    lateinit var model: AppViewModel
    private val REGISTRATION_ID_ARGUMENT: String = "id"
    private val TOPIC_NAME_ARGUMENT: String = "topic-name"
    private lateinit var selectedRegistration: Registration
    lateinit var selectedTopic: Topic
    @RequiresApi(Build.VERSION_CODES.O)
    private val creationDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var viewModelFactory = AppViewModelFactory(requireContext())
        model = ViewModelProviders.of(requireActivity(), viewModelFactory).get(AppViewModel::class.java)

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
                adapter = AddRegistrationRecyclerViewAdapter(selectedTopic)
            }
        }
        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textViewID).text = "${getString(R.string.ID)}: ${selectedTopic.numberRecords+1}"
        view.findViewById<TextView>(R.id.textViewDate).text = "${getString(R.string.Date)}: ${creationDate}"
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