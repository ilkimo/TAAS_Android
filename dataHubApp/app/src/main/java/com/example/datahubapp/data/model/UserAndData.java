package com.example.datahubapp.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAndData {

    private User userInformation;
    private UserData dataInformation;

    @JsonCreator
    public UserAndData(@JsonProperty("user") User user,
                       @JsonProperty("userData")UserData userData) {
        this.dataInformation = userData;
        this.userInformation = user;
    }

    public void setDataInformation(UserData dataInformation) {
        this.dataInformation = dataInformation;
    }

    public User getUserInformation() {
        return userInformation;
    }

    public UserData getDataInformation() {
        return dataInformation;
    }

    public void setUserInformation(User userInformation) {
        this.userInformation = userInformation;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            UserAndData castedObj = (UserAndData) obj;

            //check userInformation
            if((userInformation != null) && (castedObj.getUserInformation() != null)) {
                if(!userInformation.equals(castedObj.getUserInformation())) {
                    res = false;
                }
            } else if(!((userInformation == null) && (castedObj.getUserInformation() == null))) {
                res = false;
            }

            //check dataInformation
            if((dataInformation != null) && (castedObj.getDataInformation() != null)) {
                if(!dataInformation.equals((castedObj.getDataInformation()))) {
                    res = false;
                }
            } else if(!((dataInformation == null) && (castedObj.getDataInformation() == null))) {
                res = false;
            }
        } else {
            res = false;
        }

        return res;
    }
}
