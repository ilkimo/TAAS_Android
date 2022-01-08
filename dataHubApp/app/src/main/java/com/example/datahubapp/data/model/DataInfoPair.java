package com.example.datahubapp.data.model;

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

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            DataInfoPair castedObj = (DataInfoPair) obj;

            //check name
            if((name != null) && (castedObj.getName() != null)) {
                if(!name.equals(castedObj.getName())) {
                    res = false;
                }
            } else if(!((name == null) && (castedObj.getName() == null))) {
                res = false;
            }

            //check data
            if((data != null) && (castedObj.getData() != null)) {
                if(!data.equals((castedObj.getData()))) {
                    res = false;
                }
            } else if(!((data == null) && (castedObj.getData() == null))) {
                res = false;
            }
        } else {
            res = false;
        }

        return res;
    }
}
