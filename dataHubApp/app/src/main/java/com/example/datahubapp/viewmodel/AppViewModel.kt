package com.example.datahubapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datahubapp.data.model.UserData
import java.util.ArrayList

class AppViewModel(context: Context) : ViewModel() {
    /*private val courseAvailable: MutableLiveData<List<Course>>
    private val teacherAvailable: MutableLiveData<List<Teacher>>
    private val repetitionAvailable: MutableLiveData<List<Repetition>>
    private val activeRepetition: MutableLiveData<List<Repetition>>
    private val pastRepetition: MutableLiveData<List<Repetition>>
    private val isLogged: MutableLiveData<Boolean>
    private val repository: Repository
    var selectedCourse: Course? = null
    private var selectedTeacher: Teacher? = null
    private var selectedDayOfWeek: String? = null
    private var selectedHourStart: Int? = null*/

    private val context: Context
    private val repository: Repository

    private val userData: MutableLiveData<UserData> by lazy {
        MutableLiveData<UserData>().also{
            loadUserData()
        }
    }

    init {
        this.context = context
        //repository = Repository()
    }

    private fun loadUserData() {
        //TODO
    }

    public getUserData(): LiveData<UseData> {
        return userData.postValue(repository.getUserData())
    }

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