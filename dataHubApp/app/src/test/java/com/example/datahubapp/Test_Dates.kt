package com.example.datahubapp

import com.example.datahubapp.data.model.classicData.DateData
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.regex.Pattern

class Test_Dates {
    @Test
    fun test1() {
        val date1 = LocalDateTime.parse("2022-02-01T00:00:00.000Z", DateTimeFormatter.ISO_DATE_TIME) //DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ"))

        Assert.assertEquals(2022, date1.year)
        Assert.assertEquals(2, date1.monthValue)
        Assert.assertEquals(1, date1.dayOfMonth)

        val date2 = LocalDate.parse("2022-01-01T15:12:49.000Z", DateTimeFormatter.ISO_DATE_TIME) //DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ"))

        Assert.assertEquals(2022, date2.year)
        Assert.assertEquals(1, date2.monthValue)
        Assert.assertEquals(1, date2.dayOfMonth)

        var exceptions = 0
        try {
            val date3 = LocalDate.parse("2022-00-01T15:12:49.000Z", DateTimeFormatter.ISO_DATE_TIME) //DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ"))
        } catch(e: DateTimeParseException) {
            exceptions++
        } catch(e: Exception) {
            //nothing
        }

        Assert.assertEquals(1, exceptions) //months go from 1 to 12 for LocalDate

        try {
            val date4 = LocalDate.parse("2022-1-01T15:12:49.000Z", DateTimeFormatter.ISO_DATE_TIME) //DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ"))
        } catch(e: DateTimeParseException) {
            exceptions++
        } catch(e: Exception) {
            //nothing
        }

        Assert.assertEquals(2, exceptions) //LocalDate wants 2 cyphers, so 2022-01-01 and not 2022-1-01
    }

    @Test
    fun test2() {
        val p = Pattern.compile("[0-9]+-[0-9]+-[0-9]+T[0-9]+:[0-9]+:[0-9]+.[0-9]+Z")

        Assert.assertEquals(true, p.matcher("2022-02-01T15:12:49.000Z").matches())

        Assert.assertEquals(false, p.matcher("2022-02-01").matches())
        Assert.assertEquals(false, p.matcher("2022-02-0115:12:49.000Z").matches())
        Assert.assertEquals(false, p.matcher("2022-01T15:12:49.000Z").matches())
    }

    @Test
    fun test3() {
        var d = LocalDateTime.now()
        var d2 = LocalDateTime.parse(d.toString(), DateTimeFormatter.ISO_DATE_TIME)

        println("d2=$d2")
        Assert.assertEquals(true, d.equals(d2))
    }

    @Test
    fun test4() {
        var d =
            DateData(LocalDateTime.now())
        var d2 = LocalDateTime.parse(d.toString(), DateTimeFormatter.ISO_DATE_TIME)

        println(" d=$d")
        println("d2=$d2")

        Assert.assertEquals(true, d2.equals(d.data))
    }
}