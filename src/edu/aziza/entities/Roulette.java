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
 * @author 21650
 */
public class Roulette {
    private int id;
    private String name,description;
    private String debut,fin;

    public Roulette() {
    }

    public Roulette(int id, String name, String description, String debut, String fin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.debut = debut;
        this.fin = fin;
    }

    public Roulette(String name, String description, String debut, String fin) {
        this.name = name;
        this.description = description;
        this.debut = debut;
        this.fin = fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Roulette{" + "id=" + id + ", name=" + name + ", description=" + description + ", debut=" + debut + ", fin=" + fin + '}';
    }

  

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Roulette other = (Roulette) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.debut, other.debut)) {
            return false;
        }
        if (!Objects.equals(this.fin, other.fin)) {
            return false;
        }
        return true;
    }

    
 

  
   

   
    
}
