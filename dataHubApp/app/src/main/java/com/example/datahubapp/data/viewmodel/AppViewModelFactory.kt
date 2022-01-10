package com.example.datahubapp.data.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppViewModelFactory : ViewModelProvider.Factory {
    private var context: Context? = null
    fun AppViewModelFactory(context: Context?) {
        this.context = context
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppViewModel(context!!) as T
    }
}