/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.entities;

/**
 *
 * @author 21650
 */
public class Cadeau {
     private int id;
    private String name,type,description,image;
    int roulette_id;
    private Roulette Roulette ;
    public Cadeau() {
    }

    public Cadeau(int id, String name, String type, String description, String image, int roulette_id, Roulette Roulette) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.image = image;
        this.roulette_id = roulette_id;
        this.Roulette = Roulette;
    }


    public Cadeau( String name, String type, String description, int roulette_id,String image) {
       
        this.name = name;
        this.type = type;
        this.description = description;
        this.roulette_id = roulette_id;
        this.image = image;
        
    }


    public Cadeau(int id, String name, String type, String description, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.image = image;
    }

    public Cadeau(String name, String type, String description, String image) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Roulette getRoulette() {
        return Roulette;
    }

    public void setRoulette(Roulette Roulette) {
        this.Roulette = Roulette;
    }
    

    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }
    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
    public int getRoulette_id() {
        return roulette_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setRoulette_id(int roulette_id) {
        this.roulette_id = roulette_id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Cadeau{" + "id=" + id + ", name=" + name + ", type=" + type + ", description=" + description + ", image=" + image + ", roulette_id=" + roulette_id + ", Roulette=" + Roulette + '}';
    }

    

  
}
