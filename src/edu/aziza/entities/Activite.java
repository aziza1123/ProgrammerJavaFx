/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.entities;

import java.sql.Date;

/**
 *
 * @author abdelazizmezri
 */
public class Activite {
    private int id;
    private String nom, description, photo ;
    private Date date_debut,date_fin;
    private float prix;

    public Activite(int id, String nom, String description, String photo, Date date_debut, Date date_fin, float prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
    }

    public Activite(String nom, String description, String photo, float prix) {
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.prix = prix;
    }

    public Activite(String nom, String description, float prix) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }
    
    

    public Activite(String nom, String description, String photo, Date date_debut, Date date_fin, float prix) {
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
    }

 

  
    public Activite() {
    }

   

//    public Activite(String nom, String description, String prix) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    public Activite(String nom, String description, String prix) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Activite(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    
    
    

    

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", photo=" + photo + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prix=" + prix + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Activite other = (Activite) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
