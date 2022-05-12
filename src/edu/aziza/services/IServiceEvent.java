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
public interface IServiceEvent <T>{
    public void ajouter(T p);
    public void supprimer(int id);
    public void modifier(int id,T p);
    public List<T> getAll();
    public List<T> getAll1();
    
}
