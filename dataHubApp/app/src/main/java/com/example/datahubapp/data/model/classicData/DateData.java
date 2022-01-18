package com.example.datahubapp.data.model.classicData;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.datahubapp.data.model.SourceDataInterface;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateData implements SourceDataInterface {
    // in questo caso non abbiamo un wrapper ma direttamente la classe String
    private LocalDateTime val;

    @JsonCreator
    public DateData(@JsonProperty("val") String x) {
        throw new Error("DateData constructor TODO!");
    }

    public DateData(@JsonProperty("val") LocalDateTime x) {
        this.val = x;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DateData(int year, int month, int day) {
        val = LocalDateTime.of(year, month, day, 0, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static DateData createEmptyInstance() {
        return new DateData();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private DateData() {
        val = null;
    }

    @Override
    public Object getData(){
        return val;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setData(Object val) {
        this.val = (LocalDateTime) val;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String toString() {
        return val.format(DateTimeFormatter.ISO_DATE_TIME);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String convertDateToString(LocalDate d) {
        return "" + d.getYear() + d.getMonthValue() + d.getDayOfMonth() + "T00:00:00.000Z";
    }
}
