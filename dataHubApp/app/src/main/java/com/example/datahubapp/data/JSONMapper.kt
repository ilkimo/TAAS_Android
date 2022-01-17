package com.example.datahubapp.data

import android.util.Log
import com.example.datahubapp.controller.REQUEST
import com.example.datahubapp.controller.RETURNTYPE
import com.example.datahubapp.data.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import java.io.InputStream

val TAG: String = "JSONMapper"

fun parseJSON(jsonBody: String, type: RETURNTYPE): Any {
    Log.d("$TAG", "parseJSON, object to parse=$jsonBody")

    val mapper = ObjectMapper()
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true)

    val mappedClass = when(type) {
        RETURNTYPE.USERANDDATA -> UserAndData::class.java
        RETURNTYPE.USER -> User::class.java
        RETURNTYPE.USERDATA -> UserData::class.java
        RETURNTYPE.TOPIC_LIST -> ArrayList::class.java
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