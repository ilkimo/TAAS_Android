package com.example.datahubapp.data.model;

import com.example.datahubapp.data.model.classicData.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BooleanData.class, name = "BooleanData"),
        @JsonSubTypes.Type(value = ByteData.class, name = "ByteData"),
        @JsonSubTypes.Type(value = CharData.class, name = "CharData"),
        @JsonSubTypes.Type(value = DoubleData.class, name = "DoubleData"),
        @JsonSubTypes.Type(value = FloatData.class, name = "FloatData"),
        @JsonSubTypes.Type(value = IntegerData.class, name = "IntegerData"),
        @JsonSubTypes.Type(value = LongData.class, name = "LongData"),
        @JsonSubTypes.Type(value = ShortData.class, name = "ShortData"),
        @JsonSubTypes.Type(value = StringData.class, name = "StringData")
})
public interface SourceDataInterface<T> {
    abstract T getData();
    abstract void setData(T val);
}
