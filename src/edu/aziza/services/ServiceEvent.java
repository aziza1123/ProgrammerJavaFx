/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;


import edu.aziza.entities.Event;
import edu.aziza.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceEvent implements IServiceEvent<Event> {

    Connection cnx = DataSource.getInstance().getCnx();

    
    public void ajouter2(Event p) {
        try {
            String req = "INSERT INTO `event`(`nom`, `description`, `lieux`, `place_dispo`, `image`) VALUES ('" + p.getNom() + "', '" + p.getDescription()+ "', '" + p.getLieux()+ "', '" + p.getPlace_dispo()+ "', '" + p.getImage()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Event  created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void ajouter(Event p) {
        try {
            String req = "INSERT INTO `event` ( `nom`, `description`, `lieux`, `place_dispo`, `image`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getLieux()); 
            ps.setString(4, p.getPlace_dispo());
            ps.setString(5, p.getImage());
            
           ps.executeUpdate();
            System.out.println("ajout d un evenement ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {        
            String req = "DELETE FROM `event` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Event deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  

    @Override
    public void modifier(int id,Event p) {
        try {
            String req = "UPDATE `event` SET `nom` = '" + p.getNom() + "', `description` = '" + p.getDescription() +  "', `lieux` = '" + p.getLieux() +  "', `place_dispo` = '" + p.getPlace_dispo() +  "', `image` = '" + p.getImage() +  "' WHERE `event`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Event updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Event> getAll() {
        
       ArrayList<Event> myList = new ArrayList<>();
      ObservableList<Event> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from event";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Event p = new Event( rs.getInt("id"),rs.getString("nom"), rs.getString("description"), rs.getString("lieux"), rs.getString("image"), rs.getString("place_dispo"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<Event> getAll1() {
           ArrayList<Event> myList = new ArrayList<>();
       try {
            String req = "Select * from event";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Event p = new Event( rs.getInt("id"),rs.getString("nom"), rs.getString("description"), rs.getString("lieux"), rs.getString("image"), rs.getString("place_dispo"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

}
