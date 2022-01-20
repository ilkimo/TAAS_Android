package com.example.datahubapp

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory
import com.example.datahubapp.placeholder.PlaceholderContent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout

/**
 * A fragment representing a list of Items.
 */
class SelectedSharedTopicFragment : Fragment() {
    lateinit var model: AppViewModel
    private var columnCount = 1
    lateinit var selectedTopic: Topic
    private val TAG = "SelectedSharedTopic"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewModelFactory = AppViewModelFactory(requireContext())
        model = ViewModelProviders.of(requireParentFragment(), viewModelFactory).get(AppViewModel::class.java)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

        selectedTopic = model.getSharedTopics().value?.filter{
            it.name.equals(arguments?.getString("topic-name"))
        }!![0]

        Log.d("$TAG", "selectedTopic=${selectedTopic?.name}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_selected_shared_topic_list, container, false)
        val view = root.findViewById<RecyclerView>(R.id.list)

        // Set the adapter
        with(view) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = SelectedSharedTopicRecyclerViewAdapter(selectedTopic)
        }

        root.findViewById<TextView>(R.id.textView2).text = selectedTopic.name
        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set back arrow visible and enabled
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayShowHomeEnabled(true)

        val cloneTopicButton: FloatingActionButton = view.findViewById(R.id.cloneTopicButton)
        cloneTopicButton.setOnClickListener {
            cloneTopicDialog()
        }
    }

    fun cloneTopicDialog() {
        // create an alert builder
        // create an alert builder
        val customDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        customDialog.setTitle("Clone Topic")

        // set the custom layout
        // set the custom layout
        val customLayout: View = layoutInflater.inflate(R.layout.change_topic_name_dialog, null)
        customDialog.setView(customLayout)

        // add a button

        // add a button
        customDialog.setPositiveButton("Clone Topic") { dialog, which -> }

        // create and show the alert dialog

        // create and show the alert dialog
        val dialog: AlertDialog = customDialog.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
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
                newTopicNameTIL.error = "Please insert a topic name"
            } else {
                //TODO: make query -> clone topic
                    //TODO: oin pratica bisogna creare un nuovo topic per l'utente con la struttura di questo selezionato e il nome che ha inserito -> contenuto in "newTopicNameString"

                        Log.d("Change Topic Name", "New topic name $newTopicNameString")

                //TODO: formire feedback tramite toast se va a buon fine o meno la clonazione!

                //after query dismiss dialog
                dialog.dismiss()
            }
        }
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                SelectedSharedTopicFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}