package com.example.datahubapp

import android.os.AsyncTask
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.loader.content.AsyncTaskLoader
import com.example.datahubapp.data.model.*
import com.example.datahubapp.data.model.classicData.*
import com.example.datahubapp.databinding.*

import com.example.datahubapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.datahubapp.util.BindableViewHolder
import java.time.LocalDate
import javax.xml.transform.Source

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class AddRegistrationRecyclerViewAdapter(
    var topic: Topic
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val registration_rows: ArrayList<RegistrationViewData<Any?>> = ArrayList()

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: RegistrationViewData<*> = registration_rows[position]

        (holder as BindableViewHolder).bind(item, topic.nameType[position].name)
    }

    override fun getItemCount(): Int = registration_rows.size

    override fun getItemViewType(position: Int): Int {
        return registration_rows[position].viewType
    }

    fun getNewRegistration(): NewRegistration {
        val newRegistration = createNewRegistration()

        return newRegistration
    }

    private fun createNewRegistration(): NewRegistration {
        val reg = NewRegistration()

        TODO() //loop registration_rows for data

        return reg
    }

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
                        Log.d("LISTENER", "updated float data to ${item.registrationData.data}")
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

    inner class LongViewHolder(binding: RegistrationitemIntBinding) :
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

    inner class ShortViewHolder(binding: RegistrationitemIntBinding) :
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

    inner class TextViewHolder(binding: RegistrationitemTextBinding) :
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

    inner class DateViewHolder(binding: RegistrationitemDateBinding) :
        BindableViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: DatePicker = binding.content

        override fun bind(item: RegistrationViewData<*>, name: String) {
            TODO("Not yet implemented")
        }

        override fun removeListeners() {
            TODO("Not yet implemented")
        }

        override fun toString(): String {
            return super.toString() + " '${contentView.year}-${contentView.month}-${contentView.dayOfMonth}'"
        }
    }

    //TODO MODIFICA
    inner class HourViewHolder(binding: RegistrationitemHourBinding) :
        BindableViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TimePicker = binding.content

        override fun bind(item: RegistrationViewData<*>, name: String) {
            TODO("Not yet implemented")
        }

        override fun removeListeners() {
            TODO("Not yet implemented")
        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun toString(): String {
            return super.toString() + " '${contentView.hour}:${contentView.minute}'"
        }
    }

    data class RegistrationViewData<T>(val viewType: Int, val registrationData: SourceDataInterface<*>)
}