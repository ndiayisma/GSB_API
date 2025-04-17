package com.example.gsb_api.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Visiteur implements java.io.Serializable {
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String emailHash;
    private Date dateEmbauche;
    @SerializedName("date_embauche")
    private String password;
    private List<String> visites;
    private String visiteurId;
    private String token;
    private List<Praticien> praticiensPortefeuille;

    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getVisites() {
        return visites;
    }

    public void setVisites(List<String> visites) {
        this.visites = visites;
    }

    public Visiteur(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getVisiteurId() {
        return visiteurId;
    }
    public void setVisiteurId(String visiteurId) {
        this.visiteurId = visiteurId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Praticien> getPraticiensPortefeuille() {
        return praticiensPortefeuille;
    }

    public void setPraticiensPortefeuille(List<Praticien> praticiensPortefeuille) {
        this.praticiensPortefeuille = praticiensPortefeuille;
    }
}
