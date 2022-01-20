package com.example.datahubapp

import android.os.Build
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.datahubapp.data.model.DataInfoPair
import com.example.datahubapp.data.model.SourceDataInterface
import com.example.datahubapp.data.model.classicData.*
import com.example.datahubapp.databinding.*

import com.example.datahubapp.placeholder.PlaceholderContent.PlaceholderItem
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class SelectedRegistrationRecyclerViewAdapter(
    registration_inputs: List<SourceDataInterface<*>>,
    var nameTypes: List<DataInfoPair>
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

        for(i in nameTypes.indices) {
            itemViewType = when(nameTypes[i].data) {
                "Text" -> DATA_TYPES.STRINGDATA.value
                "Integer Number" -> DATA_TYPES.INTEGERDATA.value
                "Floating Point Number" -> DATA_TYPES.FLOATDATA.value
                "Date" -> DATA_TYPES.DATEDATA.value
                "Hour" -> DATA_TYPES.HOURDATA.value
                //TODO ADD NEW TYPE MAPPING HERE
                else -> throw Error("Given class has not been matched to a ViewType in the Android application")
            }

            registration_rows.add(RegistrationViewData(itemViewType, registration_inputs[i]))
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
                RegistrationitemTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.HOURDATA.value -> HourViewHolder(
                RegistrationitemTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            else -> throw Error("Given class has not been matched to a ViewType in the Android application")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: RegistrationViewData<*> = registration_rows[position]

        when(item.viewType) {
            DATA_TYPES.BOOLEANDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: BooleanData = item.registrationData as BooleanData
                (holder as BooleanViewHolder).name.text = nameTypes[position].name
                holder.contentView.isChecked = data.data as Boolean
            }
            DATA_TYPES.BYTEDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: ByteData = item.registrationData as ByteData
                (holder as ByteViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Byte).toString()
            }
            DATA_TYPES.CHARDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: CharData = item.registrationData as CharData
                (holder as CharViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Char).toString()
            }
            DATA_TYPES.DOUBLEDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: DoubleData = item.registrationData as DoubleData
                (holder as DoubleViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Double).toString()
            }
            DATA_TYPES.FLOATDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: FloatData = item.registrationData as FloatData
                (holder as FloatViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Float).toString()
            }
            DATA_TYPES.INTEGERDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: IntegerData = item.registrationData as IntegerData
                (holder as IntegerViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Integer).toString()
            }
            DATA_TYPES.LONGDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: LongData = item.registrationData as LongData
                (holder as LongViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Long).toString()
            }
            DATA_TYPES.SHORTDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: ShortData = item.registrationData as ShortData
                (holder as ShortViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Short).toString()
            }
            DATA_TYPES.STRINGDATA.value -> {
                var data: StringData = item.registrationData as StringData
                (holder as TextViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = data.data as String
            }
            DATA_TYPES.DATEDATA.value -> {
                var data: StringData = item.registrationData as StringData
                (holder as DateViewHolder).name.text = nameTypes[position].name
                if((data.data as String).length > 0) {
                    var date: LocalDate = LocalDate.parse(data.data as CharSequence?, DateTimeFormatter.ISO_DATE_TIME)
                    holder.contentView.text = "${date.year}-${date.monthValue}-${date.dayOfMonth}"
                } else {
                    holder.contentView.isVisible = false
                }
            }
            DATA_TYPES.HOURDATA.value -> {
                var data: StringData = item.registrationData as StringData
                (holder as HourViewHolder).name.text = nameTypes[position].name

                if((data.data as String).length > 0) {
                    var time: LocalDateTime = DateData((data.data as String)).data as LocalDateTime
                    holder.contentView.text = "${time.hour}:${time.minute}"
                } else {
                    holder.contentView.isVisible = false
                }
            }
            else -> throw Error("ViewType not correctly implemented: ${item.viewType}")
        }
    }

    override fun getItemCount(): Int = registration_rows.size

    override fun getItemViewType(position: Int): Int {
        return registration_rows[position].viewType
    }

    inner class BooleanViewHolder(binding: RegistrationitemBooleanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: CheckBox = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    //TODO CUSTOM
    inner class ByteViewHolder(binding: RegistrationitemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    //TODO CUSTOM
    inner class CharViewHolder(binding: RegistrationitemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }


    inner class DoubleViewHolder(binding: RegistrationitemIntBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }


    inner class FloatViewHolder(binding: RegistrationitemIntBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class IntegerViewHolder(binding: RegistrationitemIntBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class LongViewHolder(binding: RegistrationitemIntBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class ShortViewHolder(binding: RegistrationitemIntBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class TextViewHolder(binding: RegistrationitemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class DateViewHolder(binding: RegistrationitemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        override fun toString(): String {
            return super.toString() + " '${contentView.text}'"
        }
    }
    //TODO MODIFICA
    inner class HourViewHolder(binding: RegistrationitemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        init {
            //disable editing from GUI
            contentView.keyListener = null
        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun toString(): String {
            return super.toString() + " '$contentView.text'"
        }
    }

    data class RegistrationViewData<T>(val viewType: Int, val registrationData: SourceDataInterface<*>)
}