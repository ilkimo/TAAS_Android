package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;

public class FloatData implements SourceDataInterface {

    private Float val;

    public FloatData(float x){
        this.val = x;
    }

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
}
