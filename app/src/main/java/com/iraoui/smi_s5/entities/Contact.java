package com.iraoui.smi_s5.entities;

import java.io.Serializable;

/**
 * Created by IRAOUI on 19/11/2018.
 */

public class Contact implements Serializable{

    private int id;
    private String nomEtPrenom;
    private String email;
    private String tel;

    public Contact(String nomEtPrenom, String email, String tel) {
        this.nomEtPrenom = nomEtPrenom;
        this.email = email;
        this.tel = tel;
    }

    public Contact()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEtPrenom() {
        return nomEtPrenom;
    }

    public void setNomEtPrenom(String nomEtPrenom) {
        this.nomEtPrenom = nomEtPrenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
