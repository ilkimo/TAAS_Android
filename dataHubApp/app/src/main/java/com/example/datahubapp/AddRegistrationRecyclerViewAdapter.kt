package com.example.datahubapp

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datahubapp.controller.addRegistration
import com.example.datahubapp.data.model.*
import com.example.datahubapp.data.model.classicData.*
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory
import com.example.datahubapp.databinding.*
import com.example.datahubapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.datahubapp.util.BindableViewHolder
import com.fasterxml.jackson.databind.deser.std.DateDeserializers
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class AddRegistrationRecyclerViewAdapter(
    var topic: Topic,
    var fragment: Fragment,
    var context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = "AddRegistrationAdapter"
    private val registration_rows: ArrayList<RegistrationViewData<Any?>> = ArrayList()
    private lateinit var model: AppViewModel

    enum class DATA_TYPES(val value: Int) {
        BOOLEANDATA(0),
        BYTEDATA(1),
        CHARDATA(2),
        DOUBLEDATA(3),
        FLOATDATA(4),
        INTEGERDATA(5),
        LONGDATA(6),
        SHORTDATA(7),
        STRINGDATA(8),
        DATEDATA(9),
        HOURDATA(10)
    }

    init {
        var itemViewType: Int
        var emptyRegistration: SourceDataInterface<*>

        var viewModelFactory = AppViewModelFactory(context)
        model = ViewModelProviders.of(fragment, viewModelFactory).get(AppViewModel::class.java)

        for(i in topic.nameType.indices) {
            itemViewType = getItemViewType(topic.nameType[i].data)
            emptyRegistration = getEmptyRegistration(topic.nameType[i].data)

            registration_rows.add(RegistrationViewData(itemViewType, emptyRegistration))
        }
    }

    private fun getItemViewType(dataType: String): Int {
        return when(dataType) {
            "Text" -> DATA_TYPES.STRINGDATA.value
            "Integer Number" -> DATA_TYPES.INTEGERDATA.value
            "Floating Point Number" -> DATA_TYPES.FLOATDATA.value
            "Date" -> DATA_TYPES.DATEDATA.value
            "Hour" -> DATA_TYPES.HOURDATA.value
            //TODO ADD NEW TYPE MAPPING HERE
            else -> throw Error("Given class has not been matched to a ViewType in the Android application")
        }
    }

    private fun getEmptyRegistration(dataType: String): SourceDataInterface<*> {
        return when(dataType) {
            "Text" -> StringData.createEmptyInstance()
            "Integer Number" -> IntegerData.createEmptyInstance()
            "Floating Point Number" -> FloatData.createEmptyInstance()
            "Date" -> StringData.createEmptyInstance()
            "Hour" -> StringData.createEmptyInstance()
            //TODO ADD NEW TYPE MAPPING HERE
            else -> throw Error("Given class has not been matched to a ViewType in the Android application")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            DATA_TYPES.BOOLEANDATA.value -> BooleanViewHolder(
                RegistrationitemBooleanBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.BYTEDATA.value -> ByteViewHolder(
                RegistrationitemTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.CHARDATA.value -> CharViewHolder(
                RegistrationitemTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.DOUBLEDATA.value -> DoubleViewHolder(
                RegistrationitemIntBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.FLOATDATA.value -> FloatViewHolder(
                RegistrationitemIntBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.INTEGERDATA.value -> IntegerViewHolder(
                RegistrationitemIntBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.LONGDATA.value -> LongViewHolder(
                RegistrationitemIntBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.SHORTDATA.value -> ShortViewHolder(
                RegistrationitemIntBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.STRINGDATA.value -> TextViewHolder(
                RegistrationitemTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.DATEDATA.value -> DateViewHolder(
                RegistrationitemDateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.HOURDATA.value -> HourViewHolder(
                RegistrationitemHourBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            else -> throw Error("Given class has not been matched to a ViewType in the Android application")
        }
    }

    fun printAll() {
        println("---------------------------------------------------")
        println("Printing all the stuff:")
        for(reg in registration_rows) {
            println(reg)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: RegistrationViewData<*> = registration_rows[position]

        (holder as BindableViewHolder).bind(item, topic.nameType[position].name)
    }

    override fun getItemCount(): Int = registration_rows.size

    override fun getItemViewType(position: Int): Int {
        return registration_rows[position].viewType
    }

    fun formOk(): Boolean {
        var res = true

        registration_rows.forEachIndexed{ i, elem ->
            when(getItemViewType(topic.nameType[i].data)) {
                DATA_TYPES.BOOLEANDATA.value -> {
                    if((elem.registrationData as BooleanData).data == null) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=BooleanData")
                    }
                }
                DATA_TYPES.BYTEDATA.value -> {
                    if((elem.registrationData as ByteData).data == null) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=ByteData")
                    }
                }
                DATA_TYPES.CHARDATA.value -> {
                    if((elem.registrationData as CharData).data == null) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=CharData")
                    }
                }
                DATA_TYPES.DOUBLEDATA.value -> {
                    if((elem.registrationData as DoubleData).data == null) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=DoubleData")
                    }
                }
                DATA_TYPES.FLOATDATA.value -> {
                    if((elem.registrationData as FloatData).data == null) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=FloatData")
                    }
                }
                DATA_TYPES.INTEGERDATA.value -> {
                    if((elem.registrationData as IntegerData).data == null) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=IntegerData")
                    }
                }
                DATA_TYPES.LONGDATA.value -> {
                    if((elem.registrationData as LongData).data == null) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=LongData")
                    }
                }
                DATA_TYPES.SHORTDATA.value -> {
                    if((elem.registrationData as ShortData).data == null) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=ShortData")
                    }
                }
                DATA_TYPES.STRINGDATA.value -> {
                    if(((elem.registrationData as StringData).data == null) ||
                        (elem.registrationData as StringData).data.equals("")) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=StringData")
                    }
                }
                DATA_TYPES.DATEDATA.value -> {
                    if(((elem.registrationData as StringData).data == null) ||
                        (elem.registrationData as StringData).data.equals("")) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=DateData equals(\"\")=" + (elem.registrationData as StringData).data.equals(""))
                    }
                }
                DATA_TYPES.HOURDATA.value -> {
                    if(((elem.registrationData as StringData).data == null) ||
                        (elem.registrationData as StringData).data.equals("")) {
                        res = false
                        Log.d("$TAG", "ERROR formOk:index=$i data type=HourData")
                    }
                }
                else -> TODO()
            }
        }

        return res
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNewRegistration(fragment: Fragment, context: Context) {
        val list = ArrayList<String>()
        val userId = model.getUser().value!!.id.toString()

        registration_rows.forEach{ elem ->
            list.add(elem.registrationData.toString())
        }

        val newRegistration = NewRegistration(userId, topic.name, list)
        addRegistration(fragment, context, newRegistration)
    }

    //TODO!!!!
    inner class BooleanViewHolder(binding: RegistrationitemBooleanBinding) :
        BindableViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: CheckBox = binding.content

        override fun bind(item: RegistrationViewData<*>, name: String) {
            TODO("Not yet implemented")
        }

        override fun removeListeners() {
            TODO("Not yet implemented")
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
    //TODO!!!!
    //TODO CUSTOM
    inner class ByteViewHolder(binding: RegistrationitemTextBinding) :
        BindableViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        override fun bind(item: RegistrationViewData<*>, name: String) {
            TODO("Not yet implemented")
        }

        override fun removeListeners() {
            TODO("Not yet implemented")
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
    //TODO!!!!
    //TODO CUSTOM
    inner class CharViewHolder(binding: RegistrationitemTextBinding) :
        BindableViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        override fun bind(item: RegistrationViewData<*>, name: String) {
            TODO("Not yet implemented")
        }

        override fun removeListeners() {
            TODO("Not yet implemented")
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class DoubleViewHolder(binding: RegistrationitemIntBinding) :
        BindableViewHolder(binding.root) {
        private val name: TextView = binding.name
        private val contentView = binding.content
        var listeners: ArrayList<TextWatcher> = ArrayList()

        override fun bind(item: RegistrationViewData<*>, name: String) {
            super.bind(item, name)

            this.name.text = name
            if((item.registrationData as DoubleData).data != null) {
                (contentView as TextView).text = item.registrationData.data.toString()
            } else {
                (contentView as TextView).text = ""
            }

            var watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    Log.d("LISTENER", s.toString())

                    try {
                        item.registrationData.data = s.toString().toDouble()
                        Log.d("LISTENER", "updated data to ${item.registrationData.data}")
                    } catch(e: Exception) {
                        Log.d("EXCEPTION", "${e.message}")
                    }
                }
            }

            listeners.add(watcher)
            contentView.addTextChangedListener(watcher)
        }

        override fun removeListeners() {
            for(l in listeners) {
                Log.d("LISTENER", "REMOVING OLD LISTENER!!!")
                contentView.removeTextChangedListener(l)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class FloatViewHolder(binding: RegistrationitemIntBinding) :
        BindableViewHolder(binding.root) {
        private val name: TextView = binding.name
        private val contentView = binding.content
        var listeners: ArrayList<TextWatcher> = ArrayList()

        override fun bind(item: RegistrationViewData<*>, name: String) {
            super.bind(item, name)

            this.name.text = name
            if((item.registrationData as FloatData).data != null) {
                (contentView as TextView).text = item.registrationData.data.toString()
            } else {
                (contentView as TextView).text = ""
            }

            var watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    Log.d("LISTENER", s.toString())

                    try {
                        item.registrationData.data = s.toString().toFloat()
                        Log.d("LISTENER", "updated data to ${item.registrationData.data}")
                    } catch(e: Exception) {
                        Log.d("EXCEPTION", "${e.message}")
                    }
                }
            }

            listeners.add(watcher)
            contentView.addTextChangedListener(watcher)
        }

        override fun removeListeners() {
            for(l in listeners) {
                Log.d("LISTENER", "REMOVING OLD LISTENER!!!")
                contentView.removeTextChangedListener(l)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class IntegerViewHolder(binding: RegistrationitemIntBinding) :
        BindableViewHolder(binding.root) {
        private val name: TextView = binding.name
        private val contentView = binding.content
        var listeners: ArrayList<TextWatcher> = ArrayList()

        override fun bind(item: RegistrationViewData<*>, name: String) {
            super.bind(item, name)

            this.name.text = name
            if((item.registrationData as IntegerData).data != null) {
                (contentView as TextView).text = item.registrationData.data.toString()
            } else {
                (contentView as TextView).text = ""
            }

            var watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    Log.d("LISTENER", s.toString())

                    try {
                        item.registrationData.data = s.toString().toInt()
                        Log.d("LISTENER", "updated data to ${item.registrationData.data}")
                    } catch(e: Exception) {
                        Log.d("EXCEPTION", "${e.message}")
                    }
                }
            }

            listeners.add(watcher)
            contentView.addTextChangedListener(watcher)
        }

        override fun removeListeners() {
            for(l in listeners) {
                Log.d("LISTENER", "REMOVING OLD LISTENER!!!")
                contentView.removeTextChangedListener(l)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class LongViewHolder(binding: RegistrationitemIntBinding) :
        BindableViewHolder(binding.root) {
        private val name: TextView = binding.name
        private val contentView = binding.content
        var listeners: ArrayList<TextWatcher> = ArrayList()

        override fun bind(item: RegistrationViewData<*>, name: String) {
            super.bind(item, name)

            this.name.text = name
            if((item.registrationData as LongData).data != null) {
                (contentView as TextView).text = item.registrationData.data.toString()
            } else {
                (contentView as TextView).text = ""
            }

            var watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    Log.d("LISTENER", s.toString())

                    try {
                        item.registrationData.data = s.toString().toLong()
                        Log.d("LISTENER", "updated data to ${item.registrationData.data}")
                    } catch(e: Exception) {
                        Log.d("EXCEPTION", "${e.message}")
                    }
                }
            }

            listeners.add(watcher)
            contentView.addTextChangedListener(watcher)
        }

        override fun removeListeners() {
            for(l in listeners) {
                Log.d("LISTENER", "REMOVING OLD LISTENER!!!")
                contentView.removeTextChangedListener(l)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class ShortViewHolder(binding: RegistrationitemIntBinding) :
        BindableViewHolder(binding.root) {
        private val name: TextView = binding.name
        private val contentView = binding.content
        var listeners: ArrayList<TextWatcher> = ArrayList()

        override fun bind(item: RegistrationViewData<*>, name: String) {
            super.bind(item, name)

            this.name.text = name
            if((item.registrationData as ShortData).data != null) {
                (contentView as TextView).text = item.registrationData.data.toString()
            } else {
                (contentView as TextView).text = ""
            }

            var watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    Log.d("LISTENER", s.toString())

                    try {
                        item.registrationData.data = s.toString().toShort()
                        Log.d("LISTENER", "updated data to ${item.registrationData.data}")
                    } catch(e: Exception) {
                        Log.d("EXCEPTION", "${e.message}")
                    }
                }
            }

            listeners.add(watcher)
            contentView.addTextChangedListener(watcher)
        }

        override fun removeListeners() {
            for(l in listeners) {
                Log.d("LISTENER", "REMOVING OLD LISTENER!!!")
                contentView.removeTextChangedListener(l)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class TextViewHolder(binding: RegistrationitemTextBinding) :
        BindableViewHolder(binding.root) {
        private val name: TextView = binding.name
        private val contentView = binding.content
        var listeners: ArrayList<TextWatcher> = ArrayList()

        override fun bind(item: RegistrationViewData<*>, name: String) {
            super.bind(item, name)

            this.name.text = name
            if((item.registrationData as StringData).data != null) {
                (contentView as TextView).text = item.registrationData.data.toString()
            } else {
                (contentView as TextView).text = ""
            }

            var watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    Log.d("LISTENER", s.toString())

                    try {
                        item.registrationData.data = s.toString()
                        Log.d("LISTENER", "updated data to ${item.registrationData.data}")
                    } catch(e: Exception) {
                        Log.d("EXCEPTION", "${e.message}")
                    }
                }
            }

            listeners.add(watcher)
            contentView.addTextChangedListener(watcher)
        }

        override fun removeListeners() {
            for(l in listeners) {
                Log.d("LISTENER", "REMOVING OLD LISTENER!!!")
                contentView.removeTextChangedListener(l)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class DateViewHolder(binding: RegistrationitemDateBinding) :
        BindableViewHolder(binding.root) {
        private val name: TextView = binding.name
        private val contentView = binding.content
        var listeners: ArrayList<DatePicker.OnDateChangedListener> = ArrayList()

        @RequiresApi(Build.VERSION_CODES.O)
        override fun bind(item: RegistrationViewData<*>, name: String) {
            super.bind(item, name)

            lateinit var date: LocalDateTime

            var watcher = DatePicker.OnDateChangedListener {
                    _, year: Int, month: Int, day: Int ->
                try {
                    Log.d("$TAG", "about to parse data $year-$month-${day}T00:00:00.000Z")

                    (item.registrationData).data = DateData(year, month+1, day).toString()
                    Log.d("$TAG", "updated data to ${item.registrationData.data}")
                } catch(e: Exception) {
                    throw Error(e.message)
                }
            }

            listeners.add(watcher)
            contentView.setOnDateChangedListener(watcher)

            if((item.registrationData.data as String) == "") {
                date = LocalDateTime.now()
                Log.d("$TAG", " nessun valore, metto data=$date")
            } else {
                date = LocalDateTime.parse((item.registrationData as StringData).data.toString(), DateTimeFormatter.ISO_DATE_TIME)
                Log.d("$TAG", "data precedente recuperata=$date")

                //add 1 to month because of different indexing system of DatePicker
                date.plusMonths(1L)
            }

            this.name.text = name

            Log.d("$TAG", "       aggiorno vista con =${date.year}-${date.monthValue-1}-${date.dayOfMonth}")
            contentView.updateDate(date.year, date.monthValue-1, date.dayOfMonth)
        }

        override fun removeListeners() {
            for(l in listeners) {
                Log.d("$TAG", "REMOVING OLD LISTENER!!!")
            }
        }

        override fun toString(): String {
            return super.toString() + " '${contentView.year}-${contentView.month}-${contentView.dayOfMonth}'"
        }
    }

    inner class HourViewHolder(binding: RegistrationitemHourBinding) :
        BindableViewHolder(binding.root) {
        private val name: TextView = binding.name
        private val contentView = binding.content
        var listeners: ArrayList<TimePicker.OnTimeChangedListener> = ArrayList()

        @RequiresApi(Build.VERSION_CODES.M)
        override fun bind(item: RegistrationViewData<*>, name: String) {
            super.bind(item, name)

            var watcher = TimePicker.OnTimeChangedListener { _, hour, minute ->
                item.registrationData.data = "$hour:$minute"
                Log.d("LISTENER", "updated data to ${item.registrationData.data}")
            }

            listeners.add(watcher)
            contentView.setOnTimeChangedListener(watcher)

            if((item.registrationData.data as String).length > 0) {
                var time = (item.registrationData as StringData).data.toString().split(":")
                contentView.hour = time[0].toInt()
                contentView.minute = time[1].toInt()
            } else {
                contentView.hour = 0
                contentView.minute = 0
            }

            this.name.text = name
        }

        override fun removeListeners() {
            for(l in listeners) {
                Log.d("LISTENER", "REMOVING OLD LISTENER!!!")
                //contentView.removeTextChangedListener(l)
                // TODO check if it is not needed to remove listeners because setOnDateChangedListener already makes the substitution(?)
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun toString(): String {
            return super.toString() + " '${contentView.hour}:${contentView.minute}'"
        }
    }

    data class RegistrationViewData<T>(val viewType: Int, val registrationData: SourceDataInterface<*>)
}