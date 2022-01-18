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
        //TODO: implementare questa funzione
        //da chiamare dpo il logout
    }



    /*public fun getUserData(): LiveData<UserData?> {
        userData.postValue(repository.getUserData(user?.value))
        return null as LiveData<UserData?>
    }*/

    /*fun getCourseAvailable(year: Int): LiveData<List<Course>> {
        courseAvailable.postValue(repository.getCourseAvailable(year))
        return courseAvailable
    }

    fun getRepetitionAvailable(
        selectedTeacher: Teacher?,
        selectedDayOfWeek: String?,
        selectedHourStart: Int?
    ): LiveData<List<Repetition>> {
        this.selectedTeacher = selectedTeacher
        this.selectedDayOfWeek = selectedDayOfWeek
        this.selectedHourStart = selectedHourStart
        repetitionAvailable.postValue(
            repository.getRepetitionAvailable(
                selectedCourse,
                selectedTeacher,
                selectedDayOfWeek,
                selectedHourStart
            )
        )
        return repetitionAvailable
    }

    fun getActiveRepetition(): LiveData<List<Repetition>> {
        if (SaveSharedPreference.getLoggedStatus(context)) activeRepetition.postValue(repository.getActiveRepetition()) else activeRepetition.postValue(
            ArrayList<Repetition>()
        )
        return activeRepetition
    }

    fun getPastRepetition(): LiveData<List<Repetition>> {
        if (SaveSharedPreference.getLoggedStatus(context)) pastRepetition.postValue(repository.getPastRepetition()) else pastRepetition.postValue(
            ArrayList<Repetition>()
        )
        return pastRepetition
    }

    fun getTeacherAvailable(): LiveData<List<Teacher>> {
        teacherAvailable.postValue(repository.getTeacherAvailable(selectedCourse))
        return teacherAvailable
    }

    fun login(user: String?, password: String?) {
        isLogged.postValue(!repository.login(user, password).equals("unknown"))
    }

    fun logout() { //new
        isLogged.postValue(repository.logout().equals("invalidate"))
    }

    fun createAccount(user: String?, password: String?) {
        isLogged.postValue(!repository.createAccount(user, password).equals("UserAlreadyExists"))
    }

    fun isLogged(): LiveData<Boolean> {
        return isLogged
    }

    fun changePassword(user: String?, newPassword: String?, confirmNewPassword: String?): Boolean {
        return repository.changePassword(user, newPassword, confirmNewPassword).equals("success")
    }

    fun changeState(toChangeState: Repetition?, newState: String?) {
        if (repository.changeState(toChangeState, newState).equals("success")) {
            getActiveRepetition()
            getPastRepetition()
        }
    }

    fun book(toBook: Repetition?) {
        if (repository.book(toBook).equals("success")) {
            getActiveRepetition()
            getRepetitionAvailable(selectedTeacher, selectedDayOfWeek, selectedHourStart)
        }
    }*/

    /*init {
        courseAvailable = MutableLiveData<List<Course>>()
        teacherAvailable = MutableLiveData<List<Teacher>>()
        repetitionAvailable = MutableLiveData<List<Repetition>>()
        activeRepetition = MutableLiveData<List<Repetition>>()
        pastRepetition = MutableLiveData<List<Repetition>>()
        isLogged = MutableLiveData(false)
        repository = Repository(context)
        this.context = context
    }*/
}