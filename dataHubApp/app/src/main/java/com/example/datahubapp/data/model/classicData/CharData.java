package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;

public class CharData implements SourceDataInterface {

    private Character val;

    public CharData(char x){
        this.val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (char)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
