package com.example.datahubapp.controller

import android.content.Context
import com.example.datahubapp.data.model.User
import com.example.datahubapp.data.model.UserData
import java.io.BufferedReader
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

/**
 * This class is responsible for querying data from the application's backend
 */
class Repository {
    private var exec: ExecutorService? = null
    private var url: URL? = null
    //private var gson: Gson? = null
    private var context: Context? = null
    private val TAG = "Repository"

    fun Repository(context: Context?) {
        exec = Executors.newSingleThreadExecutor()
        //gson = Gson()
        this.context = context
        try {
            url = URL("http://10.0.2.2:8080/TWEBProject_war_exploded/FrontServlet")
        } catch (e: MalformedURLException) {
            println(e.message)
        }
    }

    fun getUserData(user: User?): UserData? {
        val userData: UserData? = null

        if(user != null) {
            //user is logged in, contact backed to fetch data!
            //TODO
        }

        return userData
    }


    /*fun getCourseAvailable(year: Int): List<Course?>? {
        val courseAvailable: List<Course?>
        var reader: BufferedReader? = null
        val ft = FutureTask<BufferedReader>(RequestServlet("availableCourse", year, url))
        try {
            exec!!.execute(ft)
            reader = ft.get()
        } catch (ignored: Exception) {
        }
        courseAvailable = gson.fromJson(reader, object : TypeToken<List<Course?>?>() {}.getType())
        return courseAvailable
    }

    fun getRepetitionAvailable(
        courseSelected: Course?,
        selectedTeacher: Teacher?,
        selectedDayOfWeek: String?,
        selectedHourStart: Int?
    ): List<Repetition?>? {
        val repetitionAvailable: List<Repetition?>
        var reader: BufferedReader? = null
        val ft = FutureTask<BufferedReader>(
            RequestServlet(
                "availableRepetition", courseSelected,
                selectedTeacher, selectedDayOfWeek, selectedHourStart, url
            )
        )
        exec!!.execute(ft)
        try {
            reader = ft.get()
        } catch (e: Exception) {
            println(e.message)
        }
        repetitionAvailable =
            gson.fromJson(reader, object : TypeToken<List<Repetition?>?>() {}.getType())
        return repetitionAvailable
    }

    fun getActiveRepetition(): List<Repetition?>? {
        var reader: BufferedReader? = null
        val activeRepetition: List<Repetition?>
        val ft = FutureTask<BufferedReader>(RequestServlet("activeRepetition", context, url))
        exec!!.execute(ft)
        try {
            reader = ft.get()
        } catch (e: Exception) {
            println(e.message)
        }
        activeRepetition =
            gson.fromJson(reader, object : TypeToken<List<Repetition?>?>() {}.getType())
        return activeRepetition
    }

    fun getPastRepetition(): List<Repetition?>? {
        var reader: BufferedReader? = null
        val ft = FutureTask<BufferedReader>(RequestServlet("pastRepetition", context, url))
        val pastRepetition: List<Repetition?>
        exec!!.execute(ft)
        try {
            reader = ft.get()
        } catch (e: Exception) {
            println(e.message)
        }
        pastRepetition =
            gson.fromJson(reader, object : TypeToken<List<Repetition?>?>() {}.getType())
        return pastRepetition
    }

    fun getTeacherAvailable(courseSelected: Course?): List<Teacher?>? {
        val teacherAvailable: List<Teacher?>
        var reader: BufferedReader? = null
        val ft = FutureTask<BufferedReader>(
            RequestServlet(
                "availableTeacherForCourse",
                courseSelected,
                url
            )
        )
        exec!!.execute(ft)
        try {
            reader = ft.get()
        } catch (e: Exception) {
            println(e.message)
        }
        teacherAvailable = gson.fromJson(reader, object : TypeToken<List<Teacher?>?>() {}.getType())
        return teacherAvailable
    }

    fun login(username: String?, password: String?): String? {
        val role: Map<String, String>
        var reader: BufferedReader? = null
        val ft = FutureTask<BufferedReader>(RequestServlet("login", username, password, url))
        exec!!.execute(ft)
        try {
            reader = ft.get()
        } catch (e: Exception) {
            println(e.message)
        }
        role = gson.fromJson(reader, object : TypeToken<Map<String?, String?>?>() {}.getType())
        SaveSharedPreference.setIDSession(context, role["session"])
        SaveSharedPreference.setRole(context, role["role"])
        return role["role"]
    }

    fun logout(): String? {
        val result: Map<String, String>
        var reader: BufferedReader? = null
        val ft = FutureTask<BufferedReader>(RequestServlet("logout", url))
        exec!!.execute(ft)
        try {
            reader = ft.get()
        } catch (e: Exception) {
            println(e.message)
        }
        result = gson.fromJson(reader, object : TypeToken<Map<String?, String?>?>() {}.getType())
        return result["result"]
    }

    fun changePassword(user: String?, newPassword: String?, confirmNewPassword: String?): String? {
        val result: Map<String, String>
        var reader: BufferedReader? = null
        val ft = FutureTask<BufferedReader>(
            RequestServlet(
                "changePassword", user,
                newPassword, context, url
            )
        )
        exec!!.execute(ft)
        try {
            reader = ft.get()
        } catch (e: Exception) {
            println(e.message)
        }
        result = gson.fromJson(reader, object : TypeToken<Map<String?, String?>?>() {}.getType())
        return result["result"]
    }

    fun createAccount(user: String?, password: String?): String? {
        var reader: BufferedReader? = null
        val result: Map<String, String>
        val ft = FutureTask<BufferedReader>(RequestServlet("createAccount", user, password, url))
        exec!!.execute(ft)
        try {
            reader = ft.get()
        } catch (e: Exception) {
            println(e.message)
        }
        result = gson.fromJson(reader, object : TypeToken<Map<String?, String?>?>() {}.getType())
        SaveSharedPreference.setIDSession(context, result["session"])
        SaveSharedPreference.setRole(context, result["role"])
        return result["role"]
    }

    fun book(toBook: Repetition?): String? {
        val result: Map<String, String>
        var reader: BufferedReader? = null
        val ft = FutureTask<BufferedReader>(RequestServlet("book", toBook, context, url))
        exec!!.execute(ft)
        try {
            reader = ft.get()
        } catch (e: Exception) {
            println(e.message)
        }
        result = gson.fromJson(reader, object : TypeToken<Map<String?, String?>?>() {}.getType())
        return result["result"]
    }

    fun changeState(toChangeState: Repetition?, newState: String?): String? {
        val result: Map<String, String>
        var reader: BufferedReader? = null
        val ft = FutureTask<BufferedReader>(
            RequestServlet(
                "changeState",
                toChangeState,
                newState,
                context,
                url
            )
        )
        exec!!.execute(ft)
        try {
            reader = ft.get()
        } catch (e: Exception) {
            println(e.message)
        }
        result = gson.fromJson(reader, object : TypeToken<Map<String?, String?>?>() {}.getType())
        return result["result"]
    }*/
}