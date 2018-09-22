package com.apo.springbootmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String firstName, lastName, birthday, nameday;

    public User() {
    }

    public User(String firstName, String lastName, String birthday, String nameday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.nameday = nameday;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNameday() {
        return nameday;
    }

    public void setNameday(String nameday) {
        this.nameday = nameday;
    }
}
