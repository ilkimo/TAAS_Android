package com.example.datahubapp.data.model;

public class User {

    private long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    public User(String name,String surname, String email, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public User() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            User castedObj = (User) obj;

            //check id
            if(id != castedObj.getId()) {
                res = false;
            }

            //check name
            if((name != null) && (castedObj.getName() != null)) {
                if(!name.equals(castedObj.getName())) {
                    res = false;
                }
            } else if(!((name == null) && (castedObj.getName() == null))) {
                res = false;
            }

            //check surname
            if((surname != null) && (castedObj.getSurname() != null)) {
                if(!surname.equals(castedObj.getSurname())) {
                    res = false;
                }
            } else if(!((surname == null) && (castedObj.getSurname() == null))) {
                res = false;
            }

            //check email
            if((email != null) && (castedObj.getEmail() != null)) {
                if(!email.equals(castedObj.getEmail())) {
                    res = false;
                }
            } else if(!((email == null) && (castedObj.getEmail() == null))) {
                res = false;
            }

            //check password
            if((password != null) && (castedObj.getPassword() != null)) {
                if(!password.equals(castedObj.getPassword())) {
                    res = false;
                }
            } else if(!((password == null) && (castedObj.getPassword() == null))) {
                res = false;
            }

        } else {
            res = false;
        }

        return res;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}