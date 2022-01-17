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
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.toLowerCase
import androidx.core.view.marginBottom
import com.example.datahubapp.controller.addTopic
import com.example.datahubapp.data.model.DataInfoPair
import com.example.datahubapp.data.model.NewTopic
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.google.android.material.snackbar.Snackbar
import dev.sasikanth.colorsheet.ColorSheet
import java.util.*
import kotlin.collections.ArrayList

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

    private var spinnerArray = mutableListOf<String>("Text", "Integer Number", "Floating Point Number", "Date", "Hour")

    private var pairList = ArrayList<Pair<String, String>>()
    private var colorSelected = "#f44336"
    private var topicNameString = ""
    private var topicDescriptionString = ""
    private val TAG = "AddTopicFragment"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val colors: IntArray = intArrayOf(
        Color.parseColor("#f44336"),
        Color.parseColor("#e91e63"),
        Color.parseColor("#9c27b0"),
        Color.parseColor("#673ab7"),
        Color.parseColor("#3f51b5"),
        Color.parseColor("#2196f3"),
        Color.parseColor("#03a9f4"),
        Color.parseColor("#00bcd4"),
        Color.parseColor("#009688"),
        Color.parseColor("#4caf50"),
        Color.parseColor("#8bc34a"),
        Color.parseColor("#cddc39"),
        Color.parseColor("#ffeb3b"),
        Color.parseColor("#ffc107"),
        Color.parseColor("#ff9800"),
        Color.parseColor("#ff5722"),
        Color.parseColor("#795548"),
        Color.parseColor("#607d8b"),
    )

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

        //Set back arrow visible and enabled
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayShowHomeEnabled(true)

        _binding = FragmentAddTopicBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
            Log.d("$TAG", pairList.toString())

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

            if(topicNameString.trim().isEmpty() || topicDescriptionString.trim().isEmpty()) {
                formOk = false
            }

            if(formOk) {
                Log.d("$TAG", "form OK")

                val arrayDataInfoPair = arrayListOf<DataInfoPair>()
                val arrayColor = arrayListOf<String>()


                arrayDataInfoPair.add(DataInfoPair("Name", "Text"))
                arrayDataInfoPair.add(DataInfoPair("Date", "Date"))

                count = container.childCount
                for (i in 0 until count) {
                    val row = container.getChildAt(i)

                    val fieldName = row.findViewById<View>(R.id.fieldName) as EditText
                    val fieldDataType = row.findViewById<View>(R.id.fieldDataType) as Spinner

                    val dataInfoPair = DataInfoPair(fieldName.text.toString(), fieldDataType.selectedItem.toString())
                    arrayDataInfoPair.add(dataInfoPair)

                    Log.d(
                        "TEST",
                        fieldName.text.toString() + "; " + fieldDataType.selectedItem.toString()
                    )
                }

                val colors = getColors(colorSelected)
                val secondColor = colors.first
                val thirdColor = colors.second

                arrayColor.add(colorSelected)
                arrayColor.add(secondColor)
                arrayColor.add(thirdColor)

                val mapper = ObjectMapper()
                mapper.configure(SerializationFeature.INDENT_OUTPUT, true)

                //TODO: change id with the correct userID
                val topic = NewTopic("2", topicNameString, topicDescriptionString, arrayDataInfoPair, arrayColor, false)

                val jsonString = mapper.writeValueAsString(topic)

                Log.d("$TAG", "new Topic=$jsonString")

                addTopic(requireParentFragment(), requireContext(), topic)

                //If ok
                val toast = Toast.makeText(context, "Topic Added", Toast.LENGTH_SHORT)
                toast.show()

                //If there is an error
                /*
                val toast = Toast.makeText(context, "Error", Toast.LENGTH_LONG)
                toast.show()
                 */
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

        val colorSelectedDiv = binding.colorSelectedDiv

        val colorButton = binding.colorButton

        colorButton.setOnClickListener {
            ColorSheet().colorPicker(
                colors = colors,
                listener = { color ->
                    // Handle color
                    val hexColor = java.lang.String.format("#%06X", 0xFFFFFF and color)
                    colorSelected = hexColor.lowercase(Locale.getDefault())
                    colorSelectedDiv.setBackgroundColor(color)
                    Log.d("Color", "Hex Color selected:${hexColor}")
                })
                .show(parentFragmentManager)
        }


        //Topic Name
        val topicName = binding.topicName

        topicName.addTextChangedListener(object : TextWatcher {
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
                topicNameString = s.toString()
            }
        })


        //Topic Name
        val topicDescription = binding.topicDescription

        topicDescription.addTextChangedListener(object : TextWatcher {
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
                topicDescriptionString = s.toString()
            }
        })

    }

    fun getColors(firstColor: String): Pair<String, String> {
        var secondColor: String = ""
        var thirdColor: String = ""

        when (firstColor.lowercase(Locale.getDefault())) {
            "#f44336" -> {
                secondColor = "#ff6154"
                thirdColor = "#ff574a"
            }
            "#e91e63" -> {
                secondColor = "#ff3c81"
                thirdColor = "#fd3277"
            }
            "#9c27b0" -> {
                secondColor = "#ba45ce"
                thirdColor = "#b03bc4"
            }
            "#673ab7" -> {
                secondColor = "#8558d5"
                thirdColor = "#7b4ecb"
            }
            "#3f51b5" -> {
                secondColor = "#5d6fd3"
                thirdColor = "#5365c9"
            }
            "#2196f3" -> {
                secondColor = "#3fb4ff"
                thirdColor = "#35aaff"
            }
            "#03a9f4" -> {
                secondColor = "#21c7ff"
                thirdColor = "#17bdff"
            }
            "#00bcd4" -> {
                secondColor = "#1edaf2"
                thirdColor = "#14d0e8"
            }
            "#009688" -> {
                secondColor = "#1eb4a6"
                thirdColor = "#14aa9c"
            }
            "#4caf50" -> {
                secondColor = "#6acd6e"
                thirdColor = "#60c364"
            }
            "#8bc34a" -> {
                secondColor = "#a9e168"
                thirdColor = "#9fd75e"
            }
            "#cddc39" -> {
                secondColor = "#ebfa57"
                thirdColor = "#e1f04d"
            }
            "#ffeb3b" -> {
                secondColor = "#ffff59"
                thirdColor = "#ffff4f"
            }
            "#ffc107" -> {
                secondColor = "#ffdf25"
                thirdColor = "#ffd51b"
            }
            "#ff9800" -> {
                secondColor = "#ffb61e"
                thirdColor = "#ffac14"
            }
            "#ff5722" -> {
                secondColor = "#ff7540"
                thirdColor = "#ff6b36"
            }
            "#795548" -> {
                secondColor = "#977366"
                thirdColor = "#8d695c"
            }
            "#607d8b" -> {
                secondColor = "#7e9ba9"
                thirdColor = "#74919f"
            }
            else -> { // Note the block
                Log.e("COLOR", "Color not available")
            }
        }

        Log.d("Second color", secondColor)

        val returnColor = Pair(secondColor, thirdColor)
        return returnColor
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