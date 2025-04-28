package com.example.gsb_api.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Visite implements java.io.Serializable {
    @SerializedName("dateVisite")
    private Date dateVisite;
    @SerializedName("commentaire")
    private String commentaire;
    @SerializedName("visiteur")
    private String visiteur;
    @SerializedName("praticien")
    private String praticien;
    @SerializedName("motif")
    private String motif;

    // Getters and Setters
    public Date getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(String visiteur) {
        this.visiteur = visiteur;
    }

    public String getPraticien() {
        return praticien;
    }

    public void setPraticien(String praticien) {
        this.praticien = praticien;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
}