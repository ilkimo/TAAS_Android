package com.example.datahubapp

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Test_Dates {
    @Test
    fun test() {
        val date = LocalDate.parse("2022-02-01T15:12:49.000Z", DateTimeFormatter.ISO_DATE_TIME) //DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ"))

        Assert.assertEquals(2022, date.year)
        Assert.assertEquals(2, date.monthValue)
        Assert.assertEquals(1, date.dayOfMonth)
    }
}