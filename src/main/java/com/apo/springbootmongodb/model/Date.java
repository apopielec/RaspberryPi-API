package com.apo.springbootmongodb.model;

public class Date {

    private int birthDay, birthMonth, nameDay, nameMonth;

    public Date() {
    }

    public Date(int birthDay, int birthMonth, int nameDay, int nameMonth) {
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.nameDay = nameDay;
        this.nameMonth = nameMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getNameDay() {
        return nameDay;
    }

    public int getNameMonth() {
        return nameMonth;
    }
}
