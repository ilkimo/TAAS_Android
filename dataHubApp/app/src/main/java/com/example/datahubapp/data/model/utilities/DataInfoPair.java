package com.example.datahubapp.data.model.utilities;

/*
 * classe per le coppie di dati che vengon mandate dal server
 * durante la registrazione di un  nuovo topic
 */
public class DataInfoPair {

    private String name;

    private String data;

    public DataInfoPair(String name, String type){
        this.name = name;
        this.data = type;
    }

    public DataInfoPair(){}

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "dataInfoPair{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
