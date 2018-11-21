package com.iraoui.smi_s5.entities;

/**
 * Created by IRAOUI on 20/11/2018.
 */

public class User {

    private int id;
    private String nom;
    private String num;
    private String email;
    private String pwd;

    public User(String nom, String num, String email, String pwd) {
        this.nom = nom;
        this.num = num;
        this.email = email;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
