/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author yassin
 */
public class Sponsor {
    int id;
    Date DateFin;
    String nom,image;
    ImageView Imageview;

    public Sponsor() {
    }

    public Sponsor(int id, Date DateFin, String nom, String image, ImageView Imageview) {
        this.id = id;
        this.DateFin = DateFin;
        this.nom = nom;
        this.image = image;
        this.Imageview = Imageview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getImageview() {
        return Imageview;
    }

    public void setImageview(ImageView Imageview) {
        this.Imageview = Imageview;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "id=" + id + ", DateFin=" + DateFin + ", nom=" + nom + ", image=" + image + ", Imageview=" + Imageview + '}';
    }
    
    
    
}
