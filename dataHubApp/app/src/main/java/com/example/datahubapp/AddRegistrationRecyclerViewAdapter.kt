package com.example.datahubapp

import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.datahubapp.data.model.DataInfoPair
import com.example.datahubapp.data.model.SourceDataInterface
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.model.classicData.*
import com.example.datahubapp.databinding.*

import com.example.datahubapp.placeholder.PlaceholderContent.PlaceholderItem
import java.time.LocalDate

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
            itemViewType = when(topic.nameType[i].data) {
                "Text" -> DATA_TYPES.STRINGDATA.value
                "Integer Number" -> DATA_TYPES.INTEGERDATA.value
                "Floating Point Number" -> DATA_TYPES.FLOATDATA.value
                "Date" -> DATA_TYPES.DATEDATA.value
                "Hour" -> DATA_TYPES.HOURDATA.value
                //TODO ADD NEW TYPE MAPPING HERE
                else -> throw Error("Given class has not been matched to a ViewType in the Android application")
            }

            emptyRegistration = when(topic.nameType[i].data) {
                "Text" -> StringData.createEmptyInstance()
                "Integer Number" -> IntegerData.createEmptyInstance()
                "Floating Point Number" -> FloatData.createEmptyInstance()
                "Date" -> StringData.createEmptyInstance()
                "Hour" -> StringData.createEmptyInstance()
                //TODO ADD NEW TYPE MAPPING HERE
                else -> throw Error("Given class has not been matched to a ViewType in the Android application")
            }

            registration_rows.add(RegistrationViewData(itemViewType, emptyRegistration))
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

        when(item.viewType) {
            DATA_TYPES.BOOLEANDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: BooleanData = item.registrationData as BooleanData
                (holder as BooleanViewHolder).name.text = topic.nameType[position].name
                holder.contentView.isChecked = data.data as Boolean
            }
            DATA_TYPES.BYTEDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: ByteData = item.registrationData as ByteData
                (holder as ByteViewHolder).name.text = topic.nameType[position].name
                holder.contentView.text = (data.data as Byte).toString()
            }
            DATA_TYPES.CHARDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: CharData = item.registrationData as CharData
                (holder as CharViewHolder).name.text = topic.nameType[position].name
                holder.contentView.text = (data.data as Char).toString()
            }
            DATA_TYPES.DOUBLEDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: DoubleData = item.registrationData as DoubleData
                (holder as DoubleViewHolder).name.text = topic.nameType[position].name
                holder.contentView.text = (data.data as Double).toString()
            }
            DATA_TYPES.FLOATDATA.value -> {
                var data: FloatData = item.registrationData as FloatData
                (holder as FloatViewHolder).name.text = topic.nameType[position].name
                if(data.data != null) {
                    holder.contentView.text = (data.data as Float).toString()
                } else {
                    holder.contentView.text = ""
                }
            }
            DATA_TYPES.INTEGERDATA.value -> {
                var data: IntegerData = item.registrationData as IntegerData
                (holder as IntegerViewHolder).name.text = topic.nameType[position].name

                if(data.data != null) {
                    holder.contentView.text = (data.data as Integer).toString()
                } else {
                    holder.contentView.text = ""
                }
            }
            DATA_TYPES.LONGDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: LongData = item.registrationData as LongData
                (holder as LongViewHolder).name.text = topic.nameType[position].name
                holder.contentView.text = (data.data as Long).toString()
            }
            DATA_TYPES.SHORTDATA.value -> { //TODO HANDLE NULL OR EMMPTY
                var data: ShortData = item.registrationData as ShortData
                (holder as ShortViewHolder).name.text = topic.nameType[position].name
                holder.contentView.text = (data.data as Short).toString()
            }
            DATA_TYPES.STRINGDATA.value -> {
                var data: StringData = item.registrationData as StringData
                (holder as TextViewHolder).name.text = topic.nameType[position].name
                if(data.data != null) {
                    holder.contentView.text = data.data as String
                } else {
                    holder.contentView.text = ""
                }
            }
            DATA_TYPES.DATEDATA.value -> {
                var data: StringData = item.registrationData as StringData
                (holder as DateViewHolder).name.text = topic.nameType[position].name
                if((data.data as String).length > 0) {
                    var date: LocalDate = LocalDate.parse(data.data as CharSequence?)
                    holder.contentView.updateDate(date.year, date.monthValue, date.dayOfMonth)//data.data as String
                }
            }
            DATA_TYPES.HOURDATA.value -> {
                var data: StringData = item.registrationData as StringData
                (holder as HourViewHolder).name.text = topic.nameType[position].name
                var time = (data.data as String).split(":")
                if(time?.size >= 2) {
                    holder.contentView.hour = time[0].toInt()
                    holder.contentView.minute = time[1].toInt()
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

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    //TODO CUSTOM
    inner class ByteViewHolder(binding: RegistrationitemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    //TODO CUSTOM
    inner class CharViewHolder(binding: RegistrationitemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }


    inner class DoubleViewHolder(binding: RegistrationitemIntBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }


    inner class FloatViewHolder(binding: RegistrationitemIntBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class IntegerViewHolder(binding: RegistrationitemIntBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class LongViewHolder(binding: RegistrationitemIntBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class ShortViewHolder(binding: RegistrationitemIntBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class TextViewHolder(binding: RegistrationitemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class DateViewHolder(binding: RegistrationitemDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: DatePicker = binding.content

        override fun toString(): String {
            return super.toString() + " '${contentView.year}-${contentView.month}-${contentView.dayOfMonth}'"
        }
    }
    //TODO MODIFICA
    inner class HourViewHolder(binding: RegistrationitemHourBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val contentView: TimePicker = binding.content

        @RequiresApi(Build.VERSION_CODES.M)
        override fun toString(): String {
            return super.toString() + " '${contentView.hour}:${contentView.minute}'"
        }
    }

    data class RegistrationViewData<T>(val viewType: Int, val registrationData: SourceDataInterface<*>)
}