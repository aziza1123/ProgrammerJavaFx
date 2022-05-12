/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import java.util.List;


/**
 *
 * @author 21650
 */
public interface Cat <T> {
        
public void ajouter(T t);
    public void modifier( int id,T t);
    public void supprimer (int id); 
    public List<T> recuperer();
   
    
}