package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;

public class IntegerData implements SourceDataInterface {

    //come attributo abbiamo un Integer ovvero la classe wrapper di int
    //cosi possimo sfruttare tutti i supi metodi
    private Integer val;

    public IntegerData(int x){
        val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (Integer) val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}