package com.example.datahubapp.data.model.classicData;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.datahubapp.data.model.SourceDataInterface;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringData implements SourceDataInterface {

    // in questo caso non abbiamo un wrapper ma direttamente la classe String
    private String val;

    @JsonCreator
    public StringData(@JsonProperty("val")String x) {
        this.val = x;
    }

    public static StringData createEmptyInstance() {
        return new StringData();
    }

    private StringData() {
        val = "";
    }

    @Override
    public Object getData(){
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (String)val;
    }

    @Override
    public String toString() {
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            StringData castedObj = (StringData) obj;

            //check val
            if((val != null) && (castedObj.getData() != null)) {
                if(!val.equals(castedObj.getData())) {
                    res = false;
                }
            } else if(!((val == null) && (castedObj.getData() == null))) {
                res = false;
            }
        } else {
            res = false;
        }

        return res;
    }
}
