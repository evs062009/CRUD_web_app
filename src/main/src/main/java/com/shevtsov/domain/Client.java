package com.shevtsov.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CLIENTS")
public class Client implements Comparable<Client> {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String name;
    private String surname;
    private Integer age;
    private String phone;
    private String email;

    public Client() {                   //for Hiber
    }

    public Client(String name, String surname, String phone) {
        this();
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public Client(String name, String surname, int age, String phone, String email) {
        this(name, surname, phone);
        this.age = age;
        this.email = email;
    }

    public Client(long id, String name, String surname, int age, String phone, String email) {
        this(name, surname, age, phone, email);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(getId(), client.getId()) &&
                Objects.equals(getName(), client.getName()) &&
                Objects.equals(getSurname(), client.getSurname()) &&
                Objects.equals(getAge(), client.getAge()) &&
                Objects.equals(getEmail(), client.getEmail()) &&
                Objects.equals(getPhone(), client.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getAge(), getEmail(), getPhone());
    }
}