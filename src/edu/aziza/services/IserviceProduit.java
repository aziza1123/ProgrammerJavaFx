/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import edu.aziza.entities.Produit;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
public interface IserviceProduit<T> {
     public void ajouter(T t);
    public void modifier( int id,T t);
    public void supprimer (int id); 
    public List<T> recuperer();
    public ObservableList<Produit> trierProduitNom();
    public ObservableList<Produit>  trierProduitPrix();
     public ObservableList<Produit> trierProduitDesc();
      
    
}
