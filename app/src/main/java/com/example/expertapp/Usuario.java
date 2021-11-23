package com.example.expertapp;

public class Usuario {
    private String nom;
    private String cel;
    private String email;
    private String pass;

    public Usuario() {
        this.nom = "";
        this.cel = "";
        this.email = "";
        this.pass = "";

    }

    public Usuario(String nom, String cel, String email, String pass) {
        this.nom = nom;
        this.cel = cel;
        this.email = email;
        this.pass = pass;
    }

    public String getNom() {
        return nom;
    }

    public String getCel() {
        return cel;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setCel(String cel) {
        this.cel = cel;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}
