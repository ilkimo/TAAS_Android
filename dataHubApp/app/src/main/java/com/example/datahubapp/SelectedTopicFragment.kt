package com.example.datahubapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.CompoundButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.datahubapp.controller.deleteTopic
import com.example.datahubapp.data.TAG


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
        setHasOptionsMenu(true);

        var viewModelFactory = AppViewModelFactory(requireContext())
        model = ViewModelProviders.of(requireParentFragment(), viewModelFactory).get(AppViewModel::class.java)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_topic_selected, menu);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.delete_topic) {
            Log.d(TAG, "DELETE TOPIC MENU ITEM CLICKED!")

            deleteTopic(
                requireParentFragment(),
                requireContext(),
                selectedTopic.name,
                model.getUser().value?.id!!
            )

            //TODO move this backstackpop() to processResult
            Log.d("$TAG", "popping back stack")
            NavHostFragment.findNavController(this).popBackStack()

            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_selected_topic_list, container, false)
        val view: RecyclerView = root.findViewById(R.id.list)

        root.findViewById<TextView>(R.id.textView2).text = selectedTopic.name

        //Set back arrow visible and enabled
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayShowHomeEnabled(true)

        val swipeRefreshLayout = root.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshTopicsRegistrations)

        swipeRefreshLayout.setOnRefreshListener {
            Log.d("Topic's registrations", "Refresh!!")

            //TODO: make query -> aggiornare la lista di registrazioni del topic corrente
            /*
            Handler().postDelayed(Runnable {

                swipeRefreshLayout.isRefreshing = false
            }, 4000)
             */
        }

        val shareTopic = root.findViewById<Switch>(R.id.shareTopic)
        shareTopic.isChecked = selectedTopic.shared
        shareTopic.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            Log.d("Toggle Share Topic", shareTopic.isChecked.toString())
            //TODO: make query -> condividere il topic corrente
        })

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var floatingButton: FloatingActionButton = view.findViewById(R.id.addRegistrationButton)
        floatingButton.setOnClickListener {
            if(context != null) {
                var navigationDirection: NavDirections = SelectedTopicFragmentDirections.actionSelectedTopicFragmentToAddRegistrationFragment(selectedTopic.name);
                NavHostFragment.findNavController(requireParentFragment()).navigate(navigationDirection)
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
            SelectedTopicFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}