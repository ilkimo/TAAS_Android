package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;

public class LongData implements SourceDataInterface {

    private Long val;

    public LongData(long x){
        this.val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (long)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
