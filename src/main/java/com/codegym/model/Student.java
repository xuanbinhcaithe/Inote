package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = 2,max = 18)
    private String name;

    private String address;

    @Email
    private String email;

    private String gender;
    private String[] favorites;
    private String position;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Lop lop;

    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public Student() {
    }

    public Student(@NotEmpty @Size(min = 2, max = 18) String name, String address, @Email String email, String gender, String[] favorites, String position, Lop lop) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.gender = gender;
        this.favorites = favorites;
        this.position = position;
        this.lop = lop;
    }

    public Student(@NotEmpty @Size(min = 2, max = 18) String name, String address, @Email String email, String gender, String[] favorites, String position) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.gender = gender;
        this.favorites = favorites;
        this.position = position;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getFavorites() {
        return favorites;
    }

    public void setFavorites(String[] favorites) {
        this.favorites = favorites;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
