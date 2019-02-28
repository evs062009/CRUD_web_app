package com.shevtsov.domain;

public class Client implements Comparable<Client> {
    private long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;

    public Client(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public Client(String name, String surname, int age, String phone, String email) {
        this(name, surname, phone);
        this.age = age;
        this.email = email;
    }

    public Client(long id, String name, String surname, int age, String email, String phone) {
        this(name, surname, age, phone, email);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(Client o) {
        return surname.compareToIgnoreCase(o.surname);
    }
}