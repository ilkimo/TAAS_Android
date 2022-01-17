package com.example.datahubapp.data

import android.util.Log
import com.example.datahubapp.controller.REQUEST
import com.example.datahubapp.data.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import java.io.InputStream

val TAG: String = "JSONMapper"

fun parseJSON(jsonBody: String, type: REQUEST): Any {
    Log.d("$TAG", "parseData")

    val mapper = ObjectMapper()
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true)

    val mappedClass = when(type) {
        REQUEST.ALL_USERS -> TODO()
        REQUEST.CREATE_ACCOUNT -> TODO()
        REQUEST.CHANGE_PASSWORD -> TODO()
        REQUEST.LOGIN -> UserAndData::class.java
        REQUEST.GET_TOPICS_USER -> TODO()
        REQUEST.NEW_TOPIC -> NewTopic::class.java
        REQUEST.NEW_REGISTRATION -> TODO()
        REQUEST.DELETE_TOPIC -> TODO()
        REQUEST.DELETE_REGISTRATION -> TODO()
        REQUEST.GET_SHARED_TOPICS -> TODO()
        REQUEST.CHANGE_TOPIC_SHARED_STATUS -> TODO()
        REQUEST.CHANGE_NAME_TOPIC -> TODO()
        REQUEST.DELETE_USER -> TODO()
    }

    return mapper.readValue(jsonBody, mappedClass) as Any
}

fun <T> convertToJSON(obj: Any, type: Class<T>): String {
    Log.d("$TAG", "convertToJSON")

    val mapper = ObjectMapper()
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true)

    return when(type) {
        User::class.java -> mapper.writeValueAsString(obj as User)
        UserAndData::class.java -> mapper.writeValueAsString(obj as UserAndData)
        UserData::class.java -> mapper.writeValueAsString(obj as UserData)
        NewTopic::class.java -> mapper.writeValueAsString(obj as NewTopic)
        else -> TODO()
    }
}

/*fun convertToJSON(user: User): String {
    val mapper = ObjectMapper()
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true)

    return mapper.writeValueAsString(user)
}

fun parseUserData(jsonBody: String): UserData {
    val mapper = ObjectMapper()
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true)

    return mapper.readValue(jsonBody, UserData::class.java)
}

fun parseUserAndData(jsonBody: String): UserAndData {
    val mapper = ObjectMapper()
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true)

    return mapper.readValue(jsonBody, UserAndData::class.java)
}*/