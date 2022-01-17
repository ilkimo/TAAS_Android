package com.example.datahubapp.util

import com.example.datahubapp.data.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import java.io.InputStream

fun convertToJSON(user: User): String {
    val mapper = ObjectMapper()
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true)

    return mapper.writeValueAsString(user)
}

fun parseUserData(jsonBody: String): UserData {
    val mapper = ObjectMapper()
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true)

    return mapper.readValue(jsonBody, UserData::class.java)
}