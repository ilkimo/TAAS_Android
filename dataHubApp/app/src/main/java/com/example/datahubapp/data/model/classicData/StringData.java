package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;

public class StringData implements SourceDataInterface {

    // in questo caso non abbiamo un wrapper ma direttamente la classe String
    private String val;

    public StringData(String x){
        this.val = x;
    }

    @Override
    public Object getData(){
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (String)val;
    }

    @Override
    public String toString() {
        return val;
    }
}
