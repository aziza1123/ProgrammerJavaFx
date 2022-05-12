/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import edu.aziza.entities.Publication;

import edu.aziza.services.Iservices;
import edu.aziza.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author mahdi
 */
public class ServicePublication implements Iservices<Publication> {
    
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void Ajouter(Publication u) throws SQLException {
       PreparedStatement ps;
        
   
        String query = "INSERT INTO `publication`( `media`, `titre`,`description`) VALUES ('"+u.getMedia()+"','"+u.getTitre()+"','"+u.getDescription()+"')";
        try {
            ps = cnx.prepareStatement(query);

           
            ps.execute();    
            System.out.println(u);
         
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }
    
    @Override
    public List<Publication> Affichertout()  {
   // List<Publication> list = new ArrayList();
        ObservableList<Publication> Publication=FXCollections.observableArrayList();

        String requete = "SELECT * FROM `publication`";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Publication r = new Publication();
               Publication.add(new Publication(rs.getInt("id"),rs.getString("media"),rs.getString("titre"),rs.getString("description")));
//            r.setId(rs.getInt(1));
//            r.setMedia(rs.getString(2));
//             r.setTitre(rs.getString(3));
//            r.setDescription(rs.getString(4));
           
            
            Publication.add(r);
            //publication.add(r);
           
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Publication;
    
    }
    
    
    @Override
     public void Supprimer(Publication p,int id) throws SQLException {
        PreparedStatement ps;

        //String query = " DELETE `publication` WHERE `publication`.`id`='"+id+"'";
         String query = "DELETE FROM `publication` WHERE id = " + id;
        
  
        try {
            ps = cnx.prepareStatement(query);

//           
            ps.execute();

            System.out.println("suppression ");
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
     
     
     @Override
  public void Modifier(Publication p, int id) {
       PreparedStatement ps;
        //String  = "UPDATE `publication` SET `media`=?,`titre`=?,`description`=? WHERE `publication`.`id` = " + p.getId();
        String query = "UPDATE `publication` SET `media` = '" + p.getMedia() + "',`titre` = '" + p.getTitre() + "',`description` = '" + p.getDescription() + "' WHERE `publication`.`id` = " + p.getId();

        try {
            
            ps = cnx.prepareStatement(query);
         
//            ps.setString(1, p.getMedia());
//            ps.setString(2, p.getTitre());
//            ps.setString(3, p.getDescription());
          
            ps.execute();
   

        } catch (Exception e) {
            System.out.println(e);
        }
    
    }
}
