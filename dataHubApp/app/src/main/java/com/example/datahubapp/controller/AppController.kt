package com.example.datahubapp.controller

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.datahubapp.data.model.*
import com.example.datahubapp.data.model.classicData.DoubleData
import com.example.datahubapp.data.model.classicData.IntegerData
import com.example.datahubapp.data.model.classicData.StringData
import com.example.datahubapp.data.viewmodel.AppViewModel
import java.time.LocalDate
import java.util.ArrayList

class AppController(val model: AppViewModel, val repository: Repository) {
    /*@RequiresApi(Build.VERSION_CODES.O)
    fun addTopic(topic: Topic, context: Context) {
        // try to push new topic to backend
        //TODO SORROUND AYNKTASK
        var userData: UserData? = model.getUserData().value

        if(repository.addTopicSuccessfull(userData, topic)) {
            userData?.topicList?.add(topic)
            model.getUserData().postValue()
            Log.d("testino", "aggiunto: numero topic=${userData?.topicList?.size}")
        } else {
            Toast.makeText(context, "Error in adding topic", Toast.LENGTH_LONG).show()
        }
    }*/

    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        fun fakeLogin(): UserData {
            //INIZIALIZZO OGGETTO USERDATA
            val idUser = "myUser@gmail.com"
            val nameUser = "User_name"
            val topicList = ArrayList<Topic>()
            val colors = ArrayList<String>()
            colors.add("#f44336")
            colors.add("#ea392c")
            colors.add("#e02f22")

            val nameType = ArrayList<DataInfoPair>()
            val parameter1 = DataInfoPair("descrizione", "Text")
            val parameter2 = DataInfoPair("valore", "Integer")
            nameType.add(parameter1)
            nameType.add(parameter2)

            val descrizioni = arrayOf("descrizione uno", "descrizione due", "descrizione tre", "descrizione quattro", "descrizione cinque")
            val misurazioni_valori = intArrayOf(10, 20, 30, 40, 50)

            val listaCampiRegistrati1 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati1.add(StringData(descrizioni[0]))
            listaCampiRegistrati1.add(IntegerData(misurazioni_valori[0]))
            val registration1 = Registration(1L, LocalDate.ofYearDay(2022, 1), listaCampiRegistrati1)

            val listaCampiRegistrati2 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati2.add(StringData(descrizioni[1]))
            listaCampiRegistrati2.add(IntegerData(misurazioni_valori[1]))
            val registration2 = Registration(2L, LocalDate.ofYearDay(2022, 2), listaCampiRegistrati2)

            val listaCampiRegistrati3 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati3.add(StringData(descrizioni[2]))
            listaCampiRegistrati3.add(IntegerData(misurazioni_valori[2]))
            val registration3 = Registration(3L, LocalDate.ofYearDay(2022, 3), listaCampiRegistrati3)


            val listaCampiRegistrati4 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati4.add(StringData(descrizioni[2]))
            listaCampiRegistrati4.add(IntegerData(misurazioni_valori[2]))
            val registration4 = Registration(4L, LocalDate.ofYearDay(2022, 4), listaCampiRegistrati4)


            val listaCampiRegistrati5 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati5.add(StringData(descrizioni[2]))
            listaCampiRegistrati5.add(IntegerData(misurazioni_valori[2]))
            val registration5 = Registration(5L, LocalDate.ofYearDay(2022, 5), listaCampiRegistrati5)

            val registrazioni_box = ArrayList<Registration>()
            registrazioni_box.add(registration1)
            registrazioni_box.add(registration2)
            registrazioni_box.add(registration3)
            registrazioni_box.add(registration4)
            registrazioni_box.add(registration5)

            val nameType_peso = ArrayList<DataInfoPair>().apply { add(DataInfoPair("peso", "Double")) }

            val listaCampiRegistrati_peso1 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso1.add(DoubleData(70.0))
            val registration_peso1 = Registration(1L, LocalDate.ofYearDay(2022, 1), listaCampiRegistrati_peso1)

            val listaCampiRegistrati_peso2 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso2.add(DoubleData(69.5))
            val registration_peso2 = Registration(2L, LocalDate.ofYearDay(2022, 2), listaCampiRegistrati_peso2)

            val listaCampiRegistrati_peso3 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso3.add(DoubleData(70.1))
            val registration_peso3 = Registration(3L, LocalDate.ofYearDay(2022, 3), listaCampiRegistrati_peso3)

            val listaCampiRegistrati_peso4 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso4.add(DoubleData(70.2))
            val registration_peso4 = Registration(4L, LocalDate.ofYearDay(2022, 4), listaCampiRegistrati_peso4)

            val listaCampiRegistrati_peso5 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso5.add(DoubleData(69.7))
            val registration_peso5 = Registration(5L, LocalDate.ofYearDay(2022, 5), listaCampiRegistrati_peso5)

            val listaCampiRegistrati_peso6 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso6.add(DoubleData(70.3))
            val registration_peso6 = Registration(6L, LocalDate.ofYearDay(2022, 6), listaCampiRegistrati_peso6)

            val listaCampiRegistrati_peso7 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso7.add(DoubleData(70.1))
            val registration_peso7 = Registration(7L, LocalDate.ofYearDay(2022, 7), listaCampiRegistrati_peso7)

            val listaCampiRegistrati_peso8 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso8.add(DoubleData(70.1))
            val registration_peso8 = Registration(8L, LocalDate.ofYearDay(2022, 8), listaCampiRegistrati_peso8)

            val listaCampiRegistrati_peso9 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso9.add(DoubleData(69.9))
            val registration_peso9 = Registration(9L, LocalDate.ofYearDay(2022, 9), listaCampiRegistrati_peso9)

            val registrazioni_peso = ArrayList<Registration>()
            registrazioni_peso.add(registration_peso1)
            registrazioni_peso.add(registration_peso2)
            registrazioni_peso.add(registration_peso3)
            registrazioni_peso.add(registration_peso4)
            registrazioni_peso.add(registration_peso5)
            registrazioni_peso.add(registration_peso6)
            registrazioni_peso.add(registration_peso7)
            registrazioni_peso.add(registration_peso8)
            registrazioni_peso.add(registration_peso9)

            val topic_box = Topic(
                1L, "Allenamenti box",  "topic description", LocalDate.now(),
                colors, registrazioni_box, nameType, 8L, false
            )
            val topic2 = Topic(2L, "Peso Corporeo", "topic description", LocalDate.now(), colors, registrazioni_peso, nameType, 9L, false)
            val topic3 = Topic("Sessioni di Studio", "topic description", colors, nameType_peso, false)
            val topic4 = Topic("Spese vacanze", "topic description", colors, nameType, false)
            val topic5 = Topic("Spese alimentari", "topic description", colors, nameType, false)
            val topic6 = Topic("Entrate economiche", "topic description", colors, nameType, false)
            val topic7 = Topic("Investimenti cryptovalute", "topic description", colors, nameType, false)
            val topic8 = Topic("Ore di sonno", "topic description", colors, nameType, false)
            val topic9 = Topic("Libri letti", "topic description", colors, nameType, false)
            val topic10 = Topic("Escursioni", "topic description", colors, nameType, false)
            topicList.add(topic_box)
            topicList.add(topic2)
            topicList.add(topic3)
            topicList.add(topic4)
            topicList.add(topic5)
            topicList.add(topic6)
            topicList.add(topic7)
            topicList.add(topic8)
            topicList.add(topic9)
            topicList.add(topic10)

            return UserData("1", idUser, topicList)
        }


        @RequiresApi(Build.VERSION_CODES.O)
        fun fakeSharedTopics(): ArrayList<Topic> {
            //INIZIALIZZO OGGETTO USERDATA
            val idUser = "myUser@gmail.com"
            val nameUser = "User_name"
            val topicList = ArrayList<Topic>()
            val colors = ArrayList<String>()
            colors.add("#f44336")
            colors.add("#ea392c")
            colors.add("#e02f22")

            val nameType = ArrayList<DataInfoPair>()
            val parameter1 = DataInfoPair("descrizione", "Text")
            val parameter2 = DataInfoPair("valore", "Integer")
            nameType.add(parameter1)
            nameType.add(parameter2)

            val descrizioni = arrayOf("descrizione uno", "descrizione due", "descrizione tre", "descrizione quattro", "descrizione cinque")
            val misurazioni_valori = intArrayOf(10, 20, 30, 40, 50)

            val listaCampiRegistrati1 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati1.add(StringData(descrizioni[0]))
            listaCampiRegistrati1.add(IntegerData(misurazioni_valori[0]))
            val registration1 = Registration(1L, LocalDate.ofYearDay(2022, 1), listaCampiRegistrati1)

            val listaCampiRegistrati2 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati2.add(StringData(descrizioni[1]))
            listaCampiRegistrati2.add(IntegerData(misurazioni_valori[1]))
            val registration2 = Registration(2L, LocalDate.ofYearDay(2022, 2), listaCampiRegistrati2)

            val listaCampiRegistrati3 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati3.add(StringData(descrizioni[2]))
            listaCampiRegistrati3.add(IntegerData(misurazioni_valori[2]))
            val registration3 = Registration(3L, LocalDate.ofYearDay(2022, 3), listaCampiRegistrati3)


            val listaCampiRegistrati4 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati4.add(StringData(descrizioni[2]))
            listaCampiRegistrati4.add(IntegerData(misurazioni_valori[2]))
            val registration4 = Registration(4L, LocalDate.ofYearDay(2022, 4), listaCampiRegistrati4)


            val listaCampiRegistrati5 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati5.add(StringData(descrizioni[2]))
            listaCampiRegistrati5.add(IntegerData(misurazioni_valori[2]))
            val registration5 = Registration(5L, LocalDate.ofYearDay(2022, 5), listaCampiRegistrati5)

            val registrazioni_box = ArrayList<Registration>()
            registrazioni_box.add(registration1)
            registrazioni_box.add(registration2)
            registrazioni_box.add(registration3)
            registrazioni_box.add(registration4)
            registrazioni_box.add(registration5)

            val nameType_peso = ArrayList<DataInfoPair>().apply { add(DataInfoPair("peso", "Double")) }

            val listaCampiRegistrati_peso1 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso1.add(DoubleData(70.0))
            val registration_peso1 = Registration(1L, LocalDate.ofYearDay(2022, 1), listaCampiRegistrati_peso1)

            val listaCampiRegistrati_peso2 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso2.add(DoubleData(69.5))
            val registration_peso2 = Registration(2L, LocalDate.ofYearDay(2022, 2), listaCampiRegistrati_peso2)

            val listaCampiRegistrati_peso3 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso3.add(DoubleData(70.1))
            val registration_peso3 = Registration(3L, LocalDate.ofYearDay(2022, 3), listaCampiRegistrati_peso3)

            val listaCampiRegistrati_peso4 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso4.add(DoubleData(70.2))
            val registration_peso4 = Registration(4L, LocalDate.ofYearDay(2022, 4), listaCampiRegistrati_peso4)

            val listaCampiRegistrati_peso5 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso5.add(DoubleData(69.7))
            val registration_peso5 = Registration(5L, LocalDate.ofYearDay(2022, 5), listaCampiRegistrati_peso5)

            val listaCampiRegistrati_peso6 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso6.add(DoubleData(70.3))
            val registration_peso6 = Registration(6L, LocalDate.ofYearDay(2022, 6), listaCampiRegistrati_peso6)

            val listaCampiRegistrati_peso7 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso7.add(DoubleData(70.1))
            val registration_peso7 = Registration(7L, LocalDate.ofYearDay(2022, 7), listaCampiRegistrati_peso7)

            val listaCampiRegistrati_peso8 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso8.add(DoubleData(70.1))
            val registration_peso8 = Registration(8L, LocalDate.ofYearDay(2022, 8), listaCampiRegistrati_peso8)

            val listaCampiRegistrati_peso9 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso9.add(DoubleData(69.9))
            val registration_peso9 = Registration(9L, LocalDate.ofYearDay(2022, 9), listaCampiRegistrati_peso9)

            val registrazioni_peso = ArrayList<Registration>()
            registrazioni_peso.add(registration_peso1)
            registrazioni_peso.add(registration_peso2)
            registrazioni_peso.add(registration_peso3)
            registrazioni_peso.add(registration_peso4)
            registrazioni_peso.add(registration_peso5)
            registrazioni_peso.add(registration_peso6)
            registrazioni_peso.add(registration_peso7)
            registrazioni_peso.add(registration_peso8)
            registrazioni_peso.add(registration_peso9)

            val topic_box = Topic(
                1L, "SHARED Allenamenti box",  "topic description", LocalDate.now(),
                colors, registrazioni_box, nameType, 8L, false
            )
            val topic2 = Topic(2L, "SHARED Peso Corporeo", "topic description", LocalDate.now(), colors, registrazioni_peso, nameType, 9L, false)
            val topic3 = Topic("SHARED Sessioni di Studio", "topic description", colors, nameType_peso, false)
            val topic4 = Topic("SHARED Spese vacanze", "topic description", colors, nameType, false)
            val topic5 = Topic("SHARED Spese alimentari", "topic description", colors, nameType, false)
            val topic6 = Topic("SHARED Entrate economiche", "topic description", colors, nameType, false)
            val topic7 = Topic("SHARED Investimenti cryptovalute", "topic description", colors, nameType, false)
            val topic8 = Topic("SHARED Ore di sonno", "topic description", colors, nameType, false)
            val topic9 = Topic("SHARED Libri letti", "topic description", colors, nameType, false)
            val topic10 = Topic("SHARED Escursioni", "topic description", colors, nameType, false)
            topicList.add(topic_box)
            topicList.add(topic2)
            topicList.add(topic3)
            topicList.add(topic4)
            topicList.add(topic5)
            topicList.add(topic6)
            topicList.add(topic7)
            topicList.add(topic8)
            topicList.add(topic9)
            topicList.add(topic10)

            return topicList
        }
    }
}