package com.example.datahubapp.data.model.classicData;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.datahubapp.data.model.SourceDataInterface;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HourData implements SourceDataInterface {
    private LocalDateTime val;
    private static final DateTimeFormatter format = DateTimeFormatter.ISO_DATE_TIME;

    @JsonCreator
    public HourData(@JsonProperty("val") String x) {
        val = LocalDateTime.parse(x, format);
    }

    public HourData(@JsonProperty("val") LocalDateTime x) {
        this.val = x;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public HourData(int hour, int minute) {
        val = LocalDateTime.of(2022, 1, 1, hour, minute, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static HourData createEmptyInstance() {
        return new HourData();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private HourData() {
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
        return val.format(format);
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            HourData castedObj = (HourData) obj;

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