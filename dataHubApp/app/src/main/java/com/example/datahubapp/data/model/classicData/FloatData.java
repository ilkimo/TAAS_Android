package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FloatData implements SourceDataInterface {

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
        this.val = (float)val;
    }

    @Override
    public String toString() {
        return val.toString();
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
