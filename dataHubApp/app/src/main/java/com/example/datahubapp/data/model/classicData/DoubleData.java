package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DoubleData implements SourceDataInterface {
    private Double val;

    public  DoubleData(double x){
        val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (Double) val;
    }

    @Override
    public String toString() {
        return val.toString();
    }

    @JsonCreator
    public DoubleData(@JsonProperty("val")Double val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            DoubleData castedObj = (DoubleData) obj;

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

