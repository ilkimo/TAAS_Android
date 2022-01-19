package com.example.datahubapp.data.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.model.User
import com.example.datahubapp.data.model.UserData
import java.lang.Exception

@RequiresApi(Build.VERSION_CODES.O)
class AppViewModel(context: Context) : ViewModel() {
    private val TAG = "AppViewModel"

    /**
     * Contains User data of the logged user
     */
    private val user: MutableLiveData<User> = MutableLiveData<User>()

    /**
     * Contains UserData of the logged user
     */
    private var userData: MutableLiveData<UserData> = MutableLiveData<UserData>()

    /**
     * Contains all topics that users shared
     */
    private var sharedTopics: MutableLiveData<ArrayList<Topic>> = MutableLiveData<ArrayList<Topic>>()

    fun getUser(): LiveData<User> {
        return user
    }

    fun setUser(user: User) {
        this.user.postValue(user)
    }

    fun getUserData(): LiveData<UserData> {
        return userData
    }

    fun setUserData(userData: UserData) {
        Log.d("$TAG", "uno=${userData.topicList}")
        this.userData.postValue(userData)
    }

    fun getSharedTopics(): LiveData<ArrayList<Topic>> {
        return sharedTopics
    }

    fun setSharedTopics(sharedTopics: ArrayList<Topic>) {
        this.sharedTopics.postValue(sharedTopics)
    }

    private fun userIsLoggedIn(): Boolean {
        return user.value != null;
    }

    fun deletAll() {
        TODO() //da chiamare dpo il logout, ma prima bisogna sistemare i bordelli dei tipi nullable
        //setUser(null)
        //setUserData(null)
        //setSharedPreferences(null)
    }
}