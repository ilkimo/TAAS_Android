package com.example.datahubapp.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.datahubapp.data.model.*
import com.example.datahubapp.data.model.classicData.IntegerData
import com.example.datahubapp.data.model.classicData.StringData
import java.time.LocalDate
import java.util.ArrayList

class AppController {

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

            val descrizioni = arrayOf("descrizione uno", "descrizione due", "descrizione tre")
            val misurazioni_valori = intArrayOf(10, 20, 30)

            val listaCampiRegistrati1 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati1.add(StringData(descrizioni[0]))
            listaCampiRegistrati1.add(IntegerData(misurazioni_valori[0]))
            val registration1 = Registration(1L, LocalDate.now(), listaCampiRegistrati1)

            val listaCampiRegistrati2 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati2.add(StringData(descrizioni[1]))
            listaCampiRegistrati2.add(IntegerData(misurazioni_valori[1]))
            val registration2 = Registration(2L, LocalDate.now(), listaCampiRegistrati2)

            val listaCampiRegistrati3 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati3.add(StringData(descrizioni[2]))
            listaCampiRegistrati3.add(IntegerData(misurazioni_valori[2]))
            val registration3 = Registration(3L, LocalDate.now(), listaCampiRegistrati3)

            val registrazioni_box = ArrayList<Registration>()
            registrazioni_box.add(registration1)
            registrazioni_box.add(registration2)
            registrazioni_box.add(registration3)

            val topic_box = Topic(
                1L, "Allenamenti box",  "topic description", LocalDate.now(),
                colors, registrazioni_box, nameType, 3L, false
            )
            val topic2 = Topic("Peso Corporeo", "topic description", colors, nameType, false)
            val topic3 = Topic("Sessioni di Studio", "topic description", colors, nameType, false)
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
    }
}