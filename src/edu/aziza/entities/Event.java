/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.entities;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class Event {
      int id ; 
    String nom , description ,lieux ,image ,place_dispo ;

Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlace_dispo() {
        return place_dispo;
    }

    public void setPlace_dispo(String place_dispo) {
        this.place_dispo = place_dispo;
    }

    public Event() {
    }
//
//    public Event(int id, String nom, String description, String lieux, String image, String place_dispo, Date date) {
//        this.id = id;
//        this.nom = nom;
//        this.description = description;
//        this.lieux = lieux;
//        this.image = image;
//        this.place_dispo = place_dispo;
//     
//        this.date = date;
//    }

    public Event(int id, String nom, String description, String lieux, String image, String place_dispo) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.lieux = lieux;
        this.image = image;
        this.place_dispo = place_dispo;
    }

    public Event(String nom, String description, String lieux, String image, String place_dispo) {
        this.nom = nom;
        this.description = description;
        this.lieux = lieux;
        this.image = image;
        this.place_dispo = place_dispo;
    }

    
    
    public Event(String nom, String description, String lieux, String image, String place_dispo, Date date) {
        this.nom = nom;
        this.description = description;
        this.lieux = lieux;
        this.image = image;
        this.place_dispo = place_dispo;
        this.date = date;
    }

 
    

    @Override
    public String toString() {
        return "Event{" + "nom=" + nom + ", description=" + description + ", lieux=" + lieux + ", image=" + image + ", place_dispo=" + place_dispo + ", date=" + date + '}';
    }


   


  

   
    
  
}
