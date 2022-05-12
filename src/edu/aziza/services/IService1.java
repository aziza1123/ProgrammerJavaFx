/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import java.util.List;

/**
 *
 * @author abdelazizmezri
 */
public interface IService1 <T>{
    public void ajouter(T p);
    public void supprimer(int id);
    public void modifier(T p);
    public void modifier2(T p, int id );
    public List<T> getAll();
    
}
