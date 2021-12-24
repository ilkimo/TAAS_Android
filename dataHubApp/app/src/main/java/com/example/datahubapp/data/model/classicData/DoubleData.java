package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;

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
}

