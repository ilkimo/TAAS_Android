package com.example.datahubapp;

import static org.junit.Assert.assertEquals;

import com.example.datahubapp.data.model.*;
import com.example.datahubapp.data.model.classicData.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Test_JSON_Jackson {
    /**
     * This test check if creating a UserData object, transform it to GSON and back
     * gives the same object through the UserData.equals() comparison
     */
    @Test
    public void testJSON_bidirectional() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        UserData userData, userDataParsedFromJSON = null;

        //INIZIALIZZO OGGETTO USERDATA
        String idUser = "myUser@gmail.com", nameUser = "User_name";
        ArrayList<Topic> topicList = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("#f44336");
        colors.add("#ea392c");
        colors.add("#e02f22");

        ArrayList<DataInfoPair> nameType = new ArrayList<>();
        DataInfoPair parameter1 = new DataInfoPair("descrizione", "Text");
        DataInfoPair parameter2 = new DataInfoPair("valore", "Integer");
        nameType.add(parameter1);
        nameType.add(parameter2);

        String[] descrizioni = {"descrizione uno", "descrizione due", "descrizione tre"};
        int[] misurazioni_valori = {10, 20, 30};

        ArrayList<SourceDataInterface> listaCampiRegistrati1 = new ArrayList<>();
        listaCampiRegistrati1.add(new StringData(descrizioni[0]));
        listaCampiRegistrati1.add(new IntegerData(misurazioni_valori[0]));
        Registration registration1 = new Registration(1L, LocalDate.now(), listaCampiRegistrati1);

        ArrayList<SourceDataInterface> listaCampiRegistrati2 = new ArrayList<>();
        listaCampiRegistrati2.add(new StringData(descrizioni[1]));
        listaCampiRegistrati2.add(new IntegerData(misurazioni_valori[1]));
        Registration registration2 = new Registration(2L, LocalDate.now(), listaCampiRegistrati2);

        ArrayList<SourceDataInterface> listaCampiRegistrati3 = new ArrayList<>();
        listaCampiRegistrati3.add(new StringData(descrizioni[2]));
        listaCampiRegistrati3.add(new IntegerData(misurazioni_valori[2]));
        Registration registration3 = new Registration(3L, LocalDate.now(), listaCampiRegistrati3);

        ArrayList<Registration> registrazioni_box = new ArrayList<>();
        registrazioni_box.add(registration1);
        registrazioni_box.add(registration2);
        registrazioni_box.add(registration3);

        Topic topic_box = new Topic(1L, nameUser, "Allenamenti box", LocalDate.now(),
                colors, registrazioni_box, nameType, 3L, false);
        Topic topic2 = new Topic(nameUser, "Peso Corporeo", colors, nameType, false);
        topicList.add(topic_box);
        topicList.add(topic2);

        userData = new UserData("1", idUser, topicList);
        //FINE INIZIALIZZAZIONE OGGETTO USERDATA

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(userData);
            System.out.println(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("STAMPO OGGETTO DI PARTENZA, SUCCESSIVAMENTE L'OGGETTO PARSIFICATO DA JSON:");
        System.out.println(userData);

        //STAMPO IN LOGCAT (FILTRARE PER TEST1 PER TROVARE SUBITO) L'OGGETTO
        //System.out.println(jsonString);

        try {
            //PARSIFICO IL JSON
            userDataParsedFromJSON = mapper.readValue(jsonString, UserData.class);
        }
        catch (JsonParseException e) {
            // TODO Auto-generated catch block
            //Log.d("TEST1",e.getMessage());
        }
        catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            //Log.d("TEST1",e.getMessage());
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            //Log.d("TEST1",e.getMessage());
        }

        System.out.println(userDataParsedFromJSON);
        assertEquals(true, userData.equals(userDataParsedFromJSON));
    }
}
