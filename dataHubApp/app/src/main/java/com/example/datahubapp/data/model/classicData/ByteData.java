package com.example.datahubapp.data.model.classicData;

import com.example.datahubapp.data.model.SourceDataInterface;

public class ByteData implements SourceDataInterface {

    private Byte val;

    public ByteData(byte x){
        this.val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (byte)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}