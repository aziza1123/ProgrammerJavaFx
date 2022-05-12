/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author abdelazizmezri
 */
public class Publication {
    private int id;
    private String media,titre ,description;
  //  private Date datecreation;

    public Publication(String media, String titre, String description) {
        this.media = media;
        this.titre = titre;
        this.description = description;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Date getDatecreation() {
//        return datecreation;
//    }

//    public void setDatecreation(Date datecreation) {
//        this.datecreation = datecreation;
//    }

    public Publication(String media, String description) {
        this.media = media;
        this.description = description;
    }

    public Publication(int id, String media, String titre, String description) {
        this.id = id;
        this.media = media;
        this.titre = titre;
        this.description = description;
    }
    

//    public Publication(int id, String media, String titre, String description, Date datecreation) {
//        this.id = id;
//        this.media = media;
//        this.titre = titre;
//        this.description = description;
//        this.datecreation = datecreation;
//    }

//    public Publication(String media, String titre, String description, Date datecreation) {
//        this.media = media;
//        this.titre = titre;
//        this.description = description;
//        this.datecreation = datecreation;
//    }

    public Publication() {
    }

    @Override
    public String toString() {
        return "Publication{" + "media=" + media + ", titre=" + titre + ", description=" + description +  '}';
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
        final Publication other = (Publication) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   
    
}
