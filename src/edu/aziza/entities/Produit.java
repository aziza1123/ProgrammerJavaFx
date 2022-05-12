/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.entities;

import java.util.Objects;
import com.google.gson.JsonObject;
//import static com.mysql.cj.util.SaslPrep.StringType.QUERY;
//import java.net.URLEncoder;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 *
 * @author Chedi-Amdouni
 */


/**
 *
 * @author lenovo
 */
public class Produit { 
    
     private int id,quantite;
    private String nom,description,img;
    private float prix;
    

    public Produit() {
    }

    public Produit(int id,  String nom, String description,float prix,int quantite, String img) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.quantite = quantite;
        this.prix = prix;
         this.img = img;
    }

   

    public Produit(String nom, String description, float prix,int quantite, String img) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
         this.img = img;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", quantite=" + quantite + ", nom=" + nom + ", description=" + description + ", img=" + img + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + this.quantite;
        hash = 23 * hash + Objects.hashCode(this.nom);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.img);
        hash = 23 * hash + Float.floatToIntBits(this.prix);
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
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.img, other.img)) {
            return false;
        }
        return true;
    }

  
//   public interface giveawysApi {
//    @GET("https://www.gamerpower.com/api/giveaways/")
//    Call<JsonObject> getGlobalData();
//    
//    @GET("https://www.gamerpower.com/api/giveaways?platform={pc}")
//    Call<JsonObject> getpcData(@Path(value="pc")String pc);

    

     
    
}

