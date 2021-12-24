package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;

public class ShortData implements SourceDataInterface {

    private Short val;

    public ShortData(short x){
        this.val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (short)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
