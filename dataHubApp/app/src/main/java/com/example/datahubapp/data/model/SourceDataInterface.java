package com.example.datahubapp.data.model;

public interface SourceDataInterface<T> {
    T getData();
    void setData(T val);
}
