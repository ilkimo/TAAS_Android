package com.example.datahubapp.controller

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import com.example.datahubapp.data.model.Topic
import com.example.datahubapp.data.model.User
import com.example.datahubapp.data.model.UserData
import com.example.datahubapp.data.parseJSON
import java.io.BufferedReader
import java.lang.Exception
import java.lang.RuntimeException
import java.net.*

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

private val TAG: String = "Repository"

fun makeRequest(urlStr: String, jsonBody: String): Result<*> {
    Log.d("$TAG", "makeRequest")

    val url = getURL(urlStr)

    (url.openConnection() as? HttpURLConnection)?.run {
        requestMethod = "POST"
        setRequestProperty("Content-Type", "application/json; utf-8")
        setRequestProperty("Accept", "application/json")
        doOutput = true
        try {
            outputStream.write(jsonBody.toByteArray())
        } catch(e: RuntimeException) {
            throw Error("RuntimeException: ${e.message}")
        } catch(e: Exception) {
            throw Error(e.message)
        }
        return Result.Success(inputStream.bufferedReader().use(BufferedReader::readText))
    }
    return Result.Error(Exception("Cannot open HttpURLConnection"))
}

private fun getURL(str: String): URL {
    lateinit var url: URL
    Log.d("$TAG", "getUrl=$str")

    try {
        url = URL(str)
    } catch(e: MalformedURLException) {
        TODO()
    }

    return url
}


fun getUserData(user: User?): UserData? {
    val userData: UserData? = null

    if(user != null) {
        //user is logged in, contact backed to fetch data! TODO()
    }

    return userData
}

fun addTopic(userData: UserData?, topic: Topic): Boolean {
    return false; //TODO CHANGE ME
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