package com.example.datahubapp.data.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/*class AppViewModelFactory: ViewModelProvider.Factory {
    private var context: Context? = null

    constructor(context: Context?) {
        this.context = context
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppViewModel(context!!) as T
    }
}*/

class AppViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}