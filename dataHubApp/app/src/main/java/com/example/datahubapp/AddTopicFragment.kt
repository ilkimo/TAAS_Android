package com.example.datahubapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.datahubapp.databinding.FragmentAddTopicBinding


import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import android.widget.TextView
import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddTopicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTopicFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentAddTopicBinding? = null

    private var spinnerArray = mutableListOf<String>("Text", "Integer", "Floating Point Number", "Date", "Hour")

    private var pairList = ArrayList<Pair<String, String>>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAddTopicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pairList.add(Pair("Name", "Text"))
        pairList.add(Pair("Date", "Date"))

        val firstFieldDataType = binding.firstFieldDataType
        val secondFieldDataType = binding.secondFieldDataType

        firstFieldDataType.isEnabled = false

        secondFieldDataType.setSelection(3)
        secondFieldDataType.isEnabled = false


        var index : Int
        index = 0

        val addDataField = binding.addDataField

        val container = binding.container

        addDataField.setOnClickListener {
            Log.d("Add Data field", "hai cliccato aggiungi data field")
            pairList.add(Pair("", ""))

            val inflater: LayoutInflater = LayoutInflater.from(activity).context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val addView: View = inflater.inflate(R.layout.add_topic_row, null)
            //val textOut = addView.findViewById<View>(R.id.textout) as TextView
            //textOut.setText("Aggiuntoooo: " + index)
            val buttonRemove: Button = addView.findViewById<View>(R.id.remove) as Button
            buttonRemove.setOnClickListener {
                    (addView.parent as LinearLayout).removeView(addView)
            }

            val fieldName = addView.findViewById<View>(R.id.fieldName) as EditText
            val fieldDataType = addView.findViewById<View>(R.id.fieldDataType) as Spinner

            //Set default value as Text
            fieldDataType.setSelection(0);

            //TODO: remove me
            fieldName.tag = index
            fieldDataType.tag = index

            fieldName.addTextChangedListener(object : TextWatcher {
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
                    Log.d("FIELD NAME$index", s.toString())
                }
            })

            fieldDataType?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.d("DATA TYPE $index", spinnerArray.get(position))
                }

            }


            container.addView(addView)

            index++
        }

        val doneButton = binding.doneButton

        doneButton.setOnClickListener {
            //Log.d("DoneButton", pairList.toString())

            var formOk = true

            //Check if all is compilated
            var count = container.childCount
            for (i in 0 until count) {
                val row = container.getChildAt(i)

                val fieldName = row.findViewById<View>(R.id.fieldName) as EditText

                if(fieldName.text.toString().equals("")) {
                    Log.d("Field Missing", i.toString())
                    formOk = false
                }
            }


            if(formOk) {
                Log.d("Form ok!", "OK")

                count = container.childCount
                for (i in 0 until count) {
                    val row = container.getChildAt(i)

                    val fieldName = row.findViewById<View>(R.id.fieldName) as EditText
                    val fieldDataType = row.findViewById<View>(R.id.fieldDataType) as Spinner

                    Log.d(
                        "TEST",
                        fieldName.text.toString() + "; " + fieldDataType.selectedItem.toString()
                    )

                    //TODO: make query to server


                    //If ok
                    val toast = Toast.makeText(context, "Topic Added", Toast.LENGTH_LONG)
                    toast.show()

                    //If there is an error
                    /*
                    val toast = Toast.makeText(context, "Error", Toast.LENGTH_LONG)
                    toast.show()
                     */
                }
            } else {
                val builder = context?.let { it1 -> AlertDialog.Builder(it1) }
                builder?.setTitle("Ouchhhh!")
                builder?.setMessage("Please compile all data fields")
                //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                builder?.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(context,
                        android.R.string.yes, Toast.LENGTH_SHORT).show()
                }

                builder?.show()
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddTopicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTopicFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}