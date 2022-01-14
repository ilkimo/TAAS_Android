package com.example.datahubapp

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
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
        STRINGDATA(8),
        DATEDATA(9),
        HOURDATA(10)
    }

    init {
        var itemViewType: Int
        Log.d("TESTERINO", "nameTypes.size()=${nameTypes.size}, registration_inputs.size()=${registration_inputs.size}")

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

        /*for(items in registration_inputs ) {
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
        }*/
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
            else -> throw Error("Given class has not been matched to a ViewType in the Android application")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: RegistrationViewData<*> = registration_rows[position]

        when(item.viewType) {
            DATA_TYPES.BOOLEANDATA.value -> {
                var data: BooleanData = item.registrationData as BooleanData
                (holder as BooleanViewHolder).name.text = nameTypes[position].name
                holder.contentView.isChecked = data.data as Boolean
            }
            DATA_TYPES.BYTEDATA.value -> {
                var data: ByteData = item.registrationData as ByteData
                (holder as ByteViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Byte).toString()
            }
            DATA_TYPES.CHARDATA.value -> {
                var data: CharData = item.registrationData as CharData
                (holder as CharViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Char).toString()
            }
            DATA_TYPES.DOUBLEDATA.value -> {
                var data: DoubleData = item.registrationData as DoubleData
                (holder as DoubleViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Double).toString()
            }
            DATA_TYPES.FLOATDATA.value -> {
                var data: FloatData = item.registrationData as FloatData
                (holder as FloatViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Float).toString()
            }
            DATA_TYPES.INTEGERDATA.value -> {
                var data: IntegerData = item.registrationData as IntegerData
                (holder as IntegerViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Integer).toString()
            }
            DATA_TYPES.LONGDATA.value -> {
                var data: LongData = item.registrationData as LongData
                (holder as LongViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Long).toString()
            }
            DATA_TYPES.SHORTDATA.value -> {
                var data: ShortData = item.registrationData as ShortData
                (holder as ShortViewHolder).name.text = nameTypes[position].name
                holder.contentView.text = (data.data as Short).toString()
            }
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

    data class RegistrationViewData<T>(val viewType: Int, val registrationData: SourceDataInterface<*>)
}