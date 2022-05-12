/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.ComboBox;

/**
 *
 * @author mahdi
 */
public interface Iservices <T>{
    void Ajouter(T t) throws SQLException;
    void Supprimer(T t,int id) throws SQLException;
    void Modifier(T t , int id) throws SQLException;
    List<T> Affichertout() throws SQLException;
    
}
