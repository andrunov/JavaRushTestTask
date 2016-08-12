package ru.andrunov;

import java.util.Date;

/**
 * Created by Admin on 14.07.2016.
 */
public class User {
    private int id;
    private String name;
    private int age;
    private boolean isAdmin;
    private Date created;

    public User() {
    }

    public User(int id, String name, int age, boolean isAdmin, Date created) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isAdmin = isAdmin;
        this.created = created;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIsAdmin() {
        if (isAdmin) return 1;
        else return 0;
    }

    public void setIsAdmin(int isAdmin) {
        if (isAdmin == 1) this.isAdmin=true;
        else if (isAdmin == 0) this.isAdmin = false;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return String.format("User: id - %s, name - %s, age - %s, admin - %s, created - %s",
                this.getId(),
                this.getName(),
                this.getAge(),
                this.isAdmin(),
                this.getCreated());
    }
}
