package com.example.datahubapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.datahubapp.controller.deleteTopic
import com.example.datahubapp.data.TAG
import com.example.datahubapp.data.model.Registration
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory

/**
 * A fragment representing a list of Items.
 */
class SelectedRegistrationFragment : Fragment() {

    private var columnCount = 1
    lateinit var model: AppViewModel
    private val REGISTRATION_ID_ARGUMENT: String = "id"
    private val TOPIC_NAME_ARGUMENT: String = "topic-name"
    private lateinit var selectedRegistration: Registration
    lateinit var selectedTopic: Topic

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);


        var viewModelFactory = AppViewModelFactory(requireContext())
        model = ViewModelProviders.of(requireParentFragment(), viewModelFactory).get(AppViewModel::class.java)

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
                adapter = SelectedRegistrationRecyclerViewAdapter(selectedRegistration.typeNameRegistration, selectedTopic.nameType)
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textViewID).text = "${getString(R.string.ID)}: ${selectedRegistration.id}"
        view.findViewById<TextView>(R.id.textViewDate).text = "${getString(R.string.Date)}: ${selectedRegistration.creationDate}"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_registration_selected, menu);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.delete_registration) {
            Log.d(TAG, "DELETE REGISTRATION MENU ITEM CLICKED!")

            //TODO: make query -> delete registration
            /*
            deleteTopic(
                requireParentFragment(),
                requireContext(),
                selectedTopic.name,
                model.getUser().value?.id!!
            )
             */

            //TODO move this backstackpop() to processResult
            Log.d("$TAG", "popping back stack")
            NavHostFragment.findNavController(this).popBackStack()

            true
        } else super.onOptionsItemSelected(item)
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