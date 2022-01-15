package com.example.datahubapp.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.datahubapp.AddRegistrationRecyclerViewAdapter

abstract class BindableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    open fun bind(item: AddRegistrationRecyclerViewAdapter.RegistrationViewData<*>, name: String = "") {
        removeListeners()
    }

    abstract fun removeListeners()
}