package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;

public class BooleanData implements SourceDataInterface {

    private Boolean val;

    public BooleanData(boolean x){
        val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (Boolean)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
