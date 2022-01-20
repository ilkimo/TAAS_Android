package com.example.datahubapp

import org.junit.Test

import org.junit.Assert.*
import com.example.datahubapp.data.model.LoggedInUser

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UserTest {
    @Test
    fun userCreation() {
        var loggedUser: LoggedInUser = LoggedInUser(
            "userId",
            "Mario",
            "Rossi",
            "mariorossi@gmail.com",
            "password")

        assertEquals(loggedUser.id, "userId")
        assertEquals(loggedUser.name, "Mario")
        assertEquals(loggedUser.surname, "Rossi")
        assertEquals(loggedUser.email, "mariorossi@gmail.com")
        assertEquals(loggedUser.password, "password")
    }
}