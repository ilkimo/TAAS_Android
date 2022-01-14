package com.example.datahubapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.datahubapp.data.model.DataInfoPair
import com.example.datahubapp.data.model.SourceDataInterface
import com.example.datahubapp.data.model.classicData.*
import com.example.datahubapp.databinding.RegistrationitemBooleanBinding

import com.example.datahubapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.datahubapp.databinding.RegistrationitemIntBinding
import com.example.datahubapp.databinding.RegistrationitemTextBinding

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
        STRINGDATA(8)
    }

    init {
        var itemViewType: Int
        for(items in registration_inputs ) {
            itemViewType = when(items::class) {
                BooleanData::class -> DATA_TYPES.BOOLEANDATA.value
                ByteData::class -> DATA_TYPES.BYTEDATA.value
                CharData::class -> DATA_TYPES.CHARDATA.value
                DoubleData::class -> DATA_TYPES.DOUBLEDATA.value
                FloatData::class -> DATA_TYPES.FLOATDATA.value
                IntegerData::class -> DATA_TYPES.INTEGERDATA.value
                LongData::class -> DATA_TYPES.LONGDATA.value
                ShortData::class -> DATA_TYPES.SHORTDATA.value
                StringData::class -> DATA_TYPES.STRINGDATA.value
                else -> throw Error("Given class has not been matched to a ViewType in the Android application")
            }

            registration_rows.add(RegistrationViewData(itemViewType, items))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            DATA_TYPES.INTEGERDATA.value -> IntegerViewHolder(
                RegistrationitemIntBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.BOOLEANDATA.value -> BooleanViewHolder(
                RegistrationitemBooleanBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            DATA_TYPES.STRINGDATA.value -> TextViewHolder(
                RegistrationitemTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
            else -> throw Error("Given class has not been matched to a ViewType in the Android application")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: RegistrationViewData<*> = registration_rows[position]

        /*
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.creationDate.toString()
         */

        when(item.viewType) {
            DATA_TYPES.BOOLEANDATA.value -> {
                var data: BooleanData = item.registrationData as BooleanData
                (holder as BooleanViewHolder).name.text = nameTypes[position].name
                holder.contentView.isChecked = data.data as Boolean
            }
            /*DATA_TYPES.BYTEDATA.value -> {}
            DATA_TYPES.CHARDATA.value -> {}
            DATA_TYPES.DOUBLEDATA.value -> {}
            DATA_TYPES.FLOATDATA.value -> {}*/
            DATA_TYPES.INTEGERDATA.value -> {
                var data: IntegerData = item.registrationData as IntegerData
                (holder as IntegerViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Integer).toString()
            }
            /*DATA_TYPES.LONGDATA.value -> {}
            DATA_TYPES.SHORTDATA.value -> {}*/
            DATA_TYPES.STRINGDATA.value -> {
                var data: StringData = item.registrationData as StringData
                (holder as TextViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = data.data as String
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

    inner class IntegerViewHolder(binding: RegistrationitemIntBinding) :
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

    data class RegistrationViewData<T>(val viewType: Int, val registrationData: SourceDataInterface<*>)
}