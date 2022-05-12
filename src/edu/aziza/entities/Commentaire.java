/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.entities;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Commentaire {
    private int id;
    private String contenu,num;
    private Date date;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Commentaire(int id, String contenu, String num) {
        this.id = id;
        this.contenu = contenu;
        this.num = num;
    }

    public Commentaire(String contenu, String num) {
        this.contenu = contenu;
        this.num = num;
    }
    
    

    public Commentaire(int id, String contenu, Date date) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
    }

    public Commentaire() {
    }

    public Commentaire(String contenu, Date date) {
        this.contenu = contenu;
        this.date = date;
    }

    public Commentaire(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
    }

    public Commentaire(String contenu) {
        this.contenu = contenu;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        final Commentaire other = (Commentaire) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "contenu=" + contenu + ", num=" + num + ", date=" + date + '}';
    }

 
    
    
    
    
    
}
