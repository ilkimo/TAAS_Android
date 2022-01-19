package com.example.datahubapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.datahubapp.controller.deleteTopic
import com.example.datahubapp.data.TAG
import com.example.datahubapp.data.model.UserData
import android.text.Editable

import android.text.TextWatcher
import android.widget.*

import androidx.appcompat.app.AlertDialog
import com.example.datahubapp.controller.changeSharedTopicStatus
import com.example.datahubapp.controller.refresh

import com.google.android.material.textfield.TextInputLayout
import java.lang.NullPointerException


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
        } else if(id == R.id.change_topic_name) {
            changeTopicNameDialog()
            //ChangeTopicNameDialog().show(parentFragmentManager, "MyCustomFragment")
            true
        } else super.onOptionsItemSelected(item)



    }

    fun changeTopicNameDialog() {
        // create an alert builder
        // create an alert builder
        val customDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        customDialog.setTitle("Change Topic Name")
        // set the custom layout
        // set the custom layout
        val customLayout: View = layoutInflater.inflate(R.layout.change_topic_name_dialog, null)
        customDialog.setView(customLayout)

        // add a button

        // add a button
        customDialog.setPositiveButton("Change Topic Name") { dialog, which -> }

        // create and show the alert dialog

        // create and show the alert dialog
        val dialog: AlertDialog = customDialog.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { v ->
            val newTopicNameTIL: TextInputLayout =
                customLayout.findViewById(R.id.new_topic_name_til)
            val newTopicName: EditText = customLayout.findViewById(R.id.new_topic_name)
            val newTopicNameString = newTopicName.text.toString()

            newTopicName.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    newTopicNameTIL.error = null
                }
            })

            if (newTopicNameString.isEmpty()) {
                newTopicNameTIL.error = "Please insert a new topic name"
            } else {
                //TODO: make query -> change topic name
                Log.d("Change Topic Name", "New topic name $newTopicNameString")

                //TODO: formire feedback tramite toast se va a buon fine o meno la modifica!

                //after query dismiss dialog
                dialog.dismiss()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
            Log.d("$TAG", "Refresh!!")

            refresh(requireParentFragment(), requireContext(), model.getUser().value?.id.toString())
            swipeRefreshLayout.isRefreshing = false
        }

        val shareTopic = root.findViewById<Switch>(R.id.shareTopic)
        shareTopic.isChecked = selectedTopic.shared

        shareTopic.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            Log.d("$TAG", "shareTopic switch switched to ${shareTopic.isChecked}")
            changeSharedTopicStatus(
                requireParentFragment(),
                requireContext(),
                model.getUser().value!!.id.toString(),
                selectedTopic.name,
                null
            )
        })

        // Set the adapter
        if(view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = SelectedTopicRecyclerViewAdapter(selectedTopic, this@SelectedTopicFragment)
                model.getUserData().observe(viewLifecycleOwner, Observer<UserData?>{
                    // update UI
                    with(adapter as SelectedTopicRecyclerViewAdapter) {
                        val topicList = model.getUserData().value?.topicList

                        if((topicList == null) || topicList.isEmpty()) {
                            updateTopic(null)
                        } else {
                            updateTopic(model.getUserData().value?.topicList?.filter{
                                it.name.equals(selectedTopic!!.name)
                            }!![0])
                        }
                    }

                    selectedTopic = model.getUserData().value?.topicList?.filter{
                        it.name.equals(selectedTopic.name)
                    }!![0]
                })
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