package com.example.datahubapp.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.datahubapp.data.model.*
import com.example.datahubapp.data.model.classicData.DoubleData
import com.example.datahubapp.data.model.classicData.FloatData
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
            val parameter2 = DataInfoPair("luogo", "Text")
            val parameter3 = DataInfoPair("avversario", "Text")
            val parameter4 = DataInfoPair("numero rounds", "Integer Number")
            val parameter5 = DataInfoPair("data ultimo incontro", "Date")
            val parameter6 = DataInfoPair("orario inizio incontro", "Hour")
            val parameter7 = DataInfoPair("orario fine incontro", "Hour")
            val parameter8 = DataInfoPair("peso mio", "Floating Point Number")
            val parameter9 = DataInfoPair("peso avversario", "Floating Point Number")
            nameType.add(parameter1)
            nameType.add(parameter2)
            nameType.add(parameter3)
            nameType.add(parameter4)
            nameType.add(parameter5)
            nameType.add(parameter6)
            nameType.add(parameter7)
            nameType.add(parameter8)
            nameType.add(parameter9)

            val descrizioni = arrayOf("descrizione uno", "descrizione due", "descrizione tre", "descrizione quattro", "descrizione cinque")
            val luoghi = arrayOf("sacco", "ring", "sacco leggero", "sacco pesante", "ring")
            val avversari = arrayOf("Federico Verdi", "Alessandro Macchi", "Diego Taricco", "", "")
            val numero_round = intArrayOf(6, 9, 12, 0, 0)
            val date_ultimi_incontri = arrayOf("2018-10-10", "", "2021-12-08", "", "")
            val orari_inizio = arrayOf("15:20", "9:30", "12:00", "", "")
            val orari_fine = arrayOf("16:00", "10:25", "13:30", "", "")
            val miei_pesi = arrayOf(70.0f, 71.1f, 69.9f, 71.5f, 71.0f)
            val pesi_avversario = arrayOf(71.0f, 72.6f, 66.4f, 74.8f, 72.3f)

            val listaCampiRegistrati1 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati1.add(StringData(descrizioni[0]))
            listaCampiRegistrati1.add(StringData(luoghi[0]))
            listaCampiRegistrati1.add(StringData(avversari[0]))
            listaCampiRegistrati1.add(IntegerData(numero_round[0]))
            listaCampiRegistrati1.add(StringData(date_ultimi_incontri[0]))
            listaCampiRegistrati1.add(StringData(orari_inizio[0]))
            listaCampiRegistrati1.add(StringData(orari_fine[0]))
            listaCampiRegistrati1.add(FloatData(miei_pesi[0]))
            listaCampiRegistrati1.add(FloatData(pesi_avversario[0]))
            val registration1 = Registration(1L, LocalDate.ofYearDay(2022, 1), listaCampiRegistrati1)

            val listaCampiRegistrati2 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati2.add(StringData(descrizioni[1]))
            listaCampiRegistrati2.add(StringData(luoghi[1]))
            listaCampiRegistrati2.add(StringData(avversari[1]))
            listaCampiRegistrati2.add(IntegerData(numero_round[1]))
            listaCampiRegistrati2.add(StringData(date_ultimi_incontri[1]))
            listaCampiRegistrati2.add(StringData(orari_inizio[1]))
            listaCampiRegistrati2.add(StringData(orari_fine[1]))
            listaCampiRegistrati2.add(FloatData(miei_pesi[1]))
            listaCampiRegistrati2.add(FloatData(pesi_avversario[1]))
            val registration2 = Registration(2L, LocalDate.ofYearDay(2022, 2), listaCampiRegistrati2)

            val listaCampiRegistrati3 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati3.add(StringData(descrizioni[2]))
            listaCampiRegistrati3.add(StringData(luoghi[2]))
            listaCampiRegistrati3.add(StringData(avversari[2]))
            listaCampiRegistrati3.add(IntegerData(numero_round[2]))
            listaCampiRegistrati3.add(StringData(date_ultimi_incontri[2]))
            listaCampiRegistrati3.add(StringData(orari_inizio[2]))
            listaCampiRegistrati3.add(StringData(orari_fine[2]))
            listaCampiRegistrati3.add(FloatData(miei_pesi[2]))
            listaCampiRegistrati3.add(FloatData(pesi_avversario[2]))
            val registration3 = Registration(3L, LocalDate.ofYearDay(2022, 3), listaCampiRegistrati3)

            val listaCampiRegistrati4 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati4.add(StringData(descrizioni[3]))
            listaCampiRegistrati4.add(StringData(luoghi[3]))
            listaCampiRegistrati4.add(StringData(avversari[3]))
            listaCampiRegistrati4.add(IntegerData(numero_round[3]))
            listaCampiRegistrati4.add(StringData(date_ultimi_incontri[3]))
            listaCampiRegistrati4.add(StringData(orari_inizio[3]))
            listaCampiRegistrati4.add(StringData(orari_fine[3]))
            listaCampiRegistrati4.add(FloatData(miei_pesi[3]))
            listaCampiRegistrati4.add(FloatData(pesi_avversario[3]))
            val registration4 = Registration(4L, LocalDate.ofYearDay(2022, 4), listaCampiRegistrati4)

            val listaCampiRegistrati5 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati5.add(StringData(descrizioni[4]))
            listaCampiRegistrati5.add(StringData(luoghi[4]))
            listaCampiRegistrati5.add(StringData(avversari[4]))
            listaCampiRegistrati5.add(IntegerData(numero_round[4]))
            listaCampiRegistrati5.add(StringData(date_ultimi_incontri[4]))
            listaCampiRegistrati5.add(StringData(orari_inizio[4]))
            listaCampiRegistrati5.add(StringData(orari_fine[4]))
            listaCampiRegistrati5.add(FloatData(miei_pesi[4]))
            listaCampiRegistrati5.add(FloatData(pesi_avversario[4]))
            val registration5 = Registration(5L, LocalDate.ofYearDay(2022, 5), listaCampiRegistrati5)

            val registrazioni_box = ArrayList<Registration>()
            registrazioni_box.add(registration1)
            registrazioni_box.add(registration2)
            registrazioni_box.add(registration3)
            registrazioni_box.add(registration4)
            registrazioni_box.add(registration5)

            var dataInfoPairList_spese = ArrayList<DataInfoPair>().apply {
                add(DataInfoPair("spesa1", "Floating Point Number"))
                add(DataInfoPair("spesa2", "Floating Point Number"))
                add(DataInfoPair("spesa3", "Floating Point Number"))
                add(DataInfoPair("spesa4", "Floating Point Number"))
                add(DataInfoPair("spesa5", "Floating Point Number"))
                add(DataInfoPair("spesa6", "Floating Point Number"))
                add(DataInfoPair("spesa7", "Floating Point Number"))
                add(DataInfoPair("spesa8", "Floating Point Number"))
                add(DataInfoPair("spesa9", "Floating Point Number"))
                add(DataInfoPair("spesa10", "Floating Point Number"))
                add(DataInfoPair("spesa11", "Floating Point Number"))
                add(DataInfoPair("spesa12", "Floating Point Number"))
                add(DataInfoPair("spesa13", "Floating Point Number"))
                add(DataInfoPair("spesa14", "Floating Point Number"))
                add(DataInfoPair("spesa15", "Floating Point Number"))
                add(DataInfoPair("spesa16", "Floating Point Number"))
            }
            val registrazioni_spese = ArrayList<Registration>().apply{
                add(Registration(1L, LocalDate.ofYearDay(2022, 6), ArrayList<SourceDataInterface<*>>(). apply {
                    add(FloatData(1f))
                    add(FloatData(2f))
                    add(FloatData(3f))
                    add(FloatData(4f))
                    add(FloatData(5f))
                    add(FloatData(6f))
                    add(FloatData(7f))
                    add(FloatData(8f))
                    add(FloatData(9f))
                    add(FloatData(10f))
                    add(FloatData(11f))
                    add(FloatData(12f))
                    add(FloatData(13f))
                    add(FloatData(14f))
                    add(FloatData(15f))
                    add(FloatData(16f))
                }))
            }

            val nameType_peso = ArrayList<DataInfoPair>()
            val parameter_peso1 = DataInfoPair("peso", "Floating Point Number")
            nameType_peso.add(parameter_peso1)

            val listaCampiRegistrati_peso1 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso1.add(FloatData(70.0f))
            val registration_peso1 = Registration(1L, LocalDate.ofYearDay(2022, 1), listaCampiRegistrati_peso1)

            val listaCampiRegistrati_peso2 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso2.add(FloatData(69.5f))
            val registration_peso2 = Registration(2L, LocalDate.ofYearDay(2022, 2), listaCampiRegistrati_peso2)

            val listaCampiRegistrati_peso3 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso3.add(FloatData(70.1f))
            val registration_peso3 = Registration(3L, LocalDate.ofYearDay(2022, 3), listaCampiRegistrati_peso3)

            val listaCampiRegistrati_peso4 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso4.add(FloatData(70.2f))
            val registration_peso4 = Registration(4L, LocalDate.ofYearDay(2022, 4), listaCampiRegistrati_peso4)

            val listaCampiRegistrati_peso5 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso5.add(FloatData(69.7f))
            val registration_peso5 = Registration(5L, LocalDate.ofYearDay(2022, 5), listaCampiRegistrati_peso5)

            val listaCampiRegistrati_peso6 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso6.add(FloatData(70.3f))
            val registration_peso6 = Registration(6L, LocalDate.ofYearDay(2022, 6), listaCampiRegistrati_peso6)

            val listaCampiRegistrati_peso7 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso7.add(FloatData(70.1f))
            val registration_peso7 = Registration(7L, LocalDate.ofYearDay(2022, 7), listaCampiRegistrati_peso7)

            val listaCampiRegistrati_peso8 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso8.add(FloatData(70.1f))
            val registration_peso8 = Registration(8L, LocalDate.ofYearDay(2022, 8), listaCampiRegistrati_peso8)

            val listaCampiRegistrati_peso9 = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_peso9.add(FloatData(69.9f))
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

            val colors1 = ArrayList<String>()
            colors1.add("#00ff00")
            colors1.add("#00ff00")
            colors1.add("#00ff00")

            val topic_box = Topic(
                1L, "Allenamenti box",  "topic description", LocalDate.now(),
                colors1, registrazioni_box, nameType, 5L, false
            )
            val topic2 = Topic(2L, "Peso Corporeo", "topic description", LocalDate.now(), colors, registrazioni_peso, nameType_peso, 9L, false)
            val topic3 = Topic(3L, "Spese vacanze", "topic description", LocalDate.now(), colors, registrazioni_spese, dataInfoPairList_spese, 1L, false)
            val topic4 = Topic("Sessioni di Studio", "topic description", colors, nameType, false)
            val topic5 = Topic("Spese alimentari", "topic description", colors, nameType, false)
            val topic6 = Topic("Entrate economiche", "topic description", colors, nameType, false)
            val topic7 = Topic("Investimenti cryptovalute", "topic description", colors, nameType, false)
            val topic8 = Topic("Ore di sonno", "topic description", colors, nameType, false)
            val topic9 = Topic("Libri letti", "topic description", colors, nameType, false)
            val topic10 = Topic("Escursioni", "topic description", colors, nameType, false)

            var nameType_date = ArrayList<DataInfoPair>()
            val parameter_data1 = DataInfoPair("data", "Date") //Hour
            val parameter_data2 = DataInfoPair("data", "Date")
            val parameter_data3 = DataInfoPair("data", "Date")
            val parameter_data4 = DataInfoPair("data", "Date")
            val parameter_data5 = DataInfoPair("data", "Date")
            val parameter_data6 = DataInfoPair("data", "Date")
            val parameter_data7 = DataInfoPair("data", "Date")
            val parameter_data8 = DataInfoPair("data", "Date")
            val parameter_data9 = DataInfoPair("data", "Date")
            val parameter_data10 = DataInfoPair("data", "Date")
            nameType_date.add(parameter_data1)
            nameType_date.add(parameter_data2)
            nameType_date.add(parameter_data3)
            nameType_date.add(parameter_data4)
            nameType_date.add(parameter_data5)
            nameType_date.add(parameter_data6)
            nameType_date.add(parameter_data7)
            nameType_date.add(parameter_data8)
            nameType_date.add(parameter_data9)
            nameType_date.add(parameter_data10)

            var registrazioni_date: ArrayList<Registration> = ArrayList()
            val listaCampiRegistrati_date = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_date.add(StringData("2022-01-01"))
            listaCampiRegistrati_date.add(StringData("2022-02-02"))
            listaCampiRegistrati_date.add(StringData("2022-03-03"))
            listaCampiRegistrati_date.add(StringData("2022-04-04"))
            listaCampiRegistrati_date.add(StringData("2022-05-05"))
            listaCampiRegistrati_date.add(StringData("2022-06-06"))
            listaCampiRegistrati_date.add(StringData("2022-07-07"))
            listaCampiRegistrati_date.add(StringData("2022-08-08"))
            listaCampiRegistrati_date.add(StringData("2022-09-09"))
            listaCampiRegistrati_date.add(StringData("2022-10-10"))
            val registration_date = Registration(1L, LocalDate.ofYearDay(2022, 20), listaCampiRegistrati_date)
            registrazioni_date.add(registration_date)

            val topic11 = Topic(4L, "prova date", "prova di un topic con tante date per vedere se funzionano le rimozioni dei listeners", LocalDate.now(), colors, registrazioni_date, nameType_date, 1L, false)

            //--------------------------------------------------------------------------------------
            var nameType_ore = ArrayList<DataInfoPair>()
            val parameter_ora1 = DataInfoPair("ora", "Hour") //Hour
            val parameter_ora2 = DataInfoPair("ora", "Hour")
            val parameter_ora3 = DataInfoPair("ora", "Hour")
            val parameter_ora4 = DataInfoPair("ora", "Hour")
            val parameter_ora5 = DataInfoPair("ora", "Hour")
            val parameter_ora6 = DataInfoPair("ora", "Hour")
            val parameter_ora7 = DataInfoPair("ora", "Hour")
            val parameter_ora8 = DataInfoPair("ora", "Hour")
            val parameter_ora9 = DataInfoPair("ora", "Hour")
            val parameter_ora10 = DataInfoPair("ora", "Hour")
            nameType_ore.add(parameter_ora1)
            nameType_ore.add(parameter_ora2)
            nameType_ore.add(parameter_ora3)
            nameType_ore.add(parameter_ora4)
            nameType_ore.add(parameter_ora5)
            nameType_ore.add(parameter_ora6)
            nameType_ore.add(parameter_ora7)
            nameType_ore.add(parameter_ora8)
            nameType_ore.add(parameter_ora9)
            nameType_ore.add(parameter_ora10)

            var registrazioni_ore: ArrayList<Registration> = ArrayList()
            val listaCampiRegistrati_ore = ArrayList<SourceDataInterface<*>>()
            listaCampiRegistrati_ore.add(StringData("1:1"))
            listaCampiRegistrati_ore.add(StringData("2:2"))
            listaCampiRegistrati_ore.add(StringData("3:3"))
            listaCampiRegistrati_ore.add(StringData("4:4"))
            listaCampiRegistrati_ore.add(StringData("5:5"))
            listaCampiRegistrati_ore.add(StringData("6:6"))
            listaCampiRegistrati_ore.add(StringData("7:7"))
            listaCampiRegistrati_ore.add(StringData("8:8"))
            listaCampiRegistrati_ore.add(StringData("9:9"))
            listaCampiRegistrati_ore.add(StringData("10:10"))
            val registrazione_ore = Registration(1L, LocalDate.ofYearDay(2022, 20), listaCampiRegistrati_ore)
            registrazioni_ore.add(registrazione_ore)
            //--------------------------------------------------------------------------------------

            val topic12 = Topic(4L, "prova orari", "prova di un topic con tanti orari per vedere se funzionano le rimozioni dei listeners", LocalDate.now(), colors, registrazioni_ore, nameType_ore, 1L, false)
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
            topicList.add(topic11)
            topicList.add(topic12)

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