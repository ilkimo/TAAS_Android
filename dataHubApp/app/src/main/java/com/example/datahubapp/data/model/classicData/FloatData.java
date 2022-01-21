package com.example.datahubapp.data.model.classicData;

import android.util.Log;

import com.example.datahubapp.data.model.SourceDataInterface;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FloatData implements SourceDataInterface {
    private final String TAG = "FloatData";
    private Float val;

    public FloatData(float x){
        this.val = x;
    }

    public static FloatData createEmptyInstance() {
        return new FloatData();
    }

    private FloatData() {}

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        if(val instanceof Double) {
            //seems like converting Float in JSON and then converting it back creates parsin error
            //TODO check backend situation
            Log.d(TAG, "Sto settando un Double?!" + val.toString());
            this.val = ((Double) val).floatValue();
        } else {
            Log.d(TAG, "Setto un float, tutto in regola");
            this.val = (float)val;
        }
    }

    @Override
    public String toString() {
        String str = "";

        if(val != null) {
            str = val.toString();
        }

        return str;
    }

    @JsonCreator
    public FloatData(@JsonProperty("val")Float val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            FloatData castedObj = (FloatData) obj;

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
