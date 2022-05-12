/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.entities;

import javafx.scene.image.ImageView;

public class User {

    int id;
    String email, password , token , resetoken , nom , prenom , Genre, image;
    String roles;
    ImageView imageView;

    public User() {
    }

    public User(int id, String email, String password, String token, String resetoken, String nom, String prenom, String Genre, String image, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.token = token;
        this.resetoken = resetoken;
        this.nom = nom;
        this.prenom = prenom;
        this.Genre = Genre;
        this.image = image;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResetoken() {
        return resetoken;
    }

    public void setResetoken(String resetoken) {
        this.resetoken = resetoken;
    }

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

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", token=" + token + ", resetoken=" + resetoken + ", nom=" + nom + ", prenom=" + prenom + ", Genre=" + Genre + ", image=" + image + ", roles=" + roles + '}';
    }
    
    
}
