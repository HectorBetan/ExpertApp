package com.example.expertapp;

public class Servicio {
    private String nom;
    private String cat;
    private String cel;
    private String email;
    private String dir;
    private String desc;

    public Servicio() {
        this.nom = "";
        this.cat = "";
        this.cel = "";
        this.email = "";
        this.dir = "";
        this.desc = "";

    }

    public Servicio(String nom, String cat, String cel, String email, String dir, String desc) {
        this.nom = nom;
        this.cat = cat;
        this.cel = cel;
        this.email = email;
        this.dir = dir;
        this.desc = desc;
    }

    public String getNom() {
        return nom;
    }

    public String getCat() {
        return cat;
    }

    public String getCel() {
        return cel;
    }

    public String getEmail() {
        return email;
    }

    public String getDir() {
        return dir;
    }

    public String getDesc() {
        return desc;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setCat(String cat) {
        this.cat = cat;
    }
    public void setCel(String cel) {
        this.cel = cel;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDir(String dir) {
        this.dir = dir;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
