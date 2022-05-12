/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import edu.aziza.entities.Cadeau;
import edu.aziza.entities.Roulette;
import edu.aziza.tests.MainClass;
import edu.aziza.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Calendar.getInstance;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author 21650
 */
public class CadeauService implements Iservice <Cadeau> {
    Connection cnx = DataSource.getInstance().getCnx();
 

//    @Override
//    public void modifer(Cadeau c) {
//          try {
//            String req = "UPDATE `cadeau` SET `name` = '" + c.getName() + "', `type` = '" + c.getType() + "', `description` = '" + c.getDescription()+ "', `image` = '" + c.getImage()+ "' WHERE `cadeau`.`id` = " + c.getId();
//            Statement st = cnx.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Cadeau updated !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    @Override
    public void ajouter(Cadeau c) {
        
        try {
            Statement st=cnx.createStatement();
             String query = "INSERT INTO `cadeau`( `name`,`type`,`description`,`roulette_id`,`image`) "
                    + "VALUES ('"
                    + c.getName() + "','"
                    + c.getType() + "','"
                    + c.getDescription() + "','"
                    
                    +c.getRoulette_id()+ "','"
          
                    + c.getImage()+"')";
             st.executeUpdate(query);
             System.out.println("added");
                     
        } catch (SQLException ex) {
            System.out.println( ex);
        }
       
//         try {
//           String req1 = "INSERT INTO `cadeau`(`name`,`type`,`description`,`roulette_id`,`image`) values (?,?,?,?,?)";
//            PreparedStatement ps = cnx.prepareStatement(req1);
//            ps.setString(1, c.getName());
//            ps.setString(2, c.getType());
//            ps.setString(3, c.getDescription());
//            ps.setString(5, c.getImage());
//            ps.setInt(4,c.getRoulette_id());
//            
//            ps.executeUpdate();
//            System.out.println("ajout done");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
    }

    @Override
    public void supprimer(int id) {
          try {
            String req = "DELETE FROM `cadeau` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Cadeau deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    


    

    @Override
    public List<Cadeau> recuperer() {
    //List<Cadeau> list = new ArrayList<>();
           ObservableList<Cadeau> Cadeau = FXCollections.observableArrayList();

        try {
            String req = "Select * from cadeau";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Cadeau c = new Cadeau();
                c.setId(rs.getInt(1));
                c.setName(rs.getString("name"));
                c.setType(rs.getString("type"));
                c.setDescription(rs.getString("description"));
             
                c.setImage(rs.getString("image"));
                Cadeau.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Cadeau;
    }

    
   
      



    @Override
    public void modifier(int id,Cadeau c) {
 try {
            PreparedStatement ps = cnx.prepareStatement("UPDATE cadeau SET `name`=? , `type`= ? , `description`= ? , `image`= ? WHERE id=  '"+id+"'");
            ps.setString(1, c.getName());
            ps.setString(2, c.getType());
            ps.setString(3, c.getDescription());
            ps.setString(4, c.getImage());

             if(ps.executeUpdate() > 0)
            {
               
                JOptionPane.showMessageDialog(null, "Cadeaau Updated", "Edit Cadeau", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("Product Updated");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Cadeau Not Updated", "Edit Cadeau", JOptionPane.ERROR_MESSAGE);
              //System.out.println("Some Error Message Here");  
            }
            System.out.println("Cadeau Modifieé avec succées ");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
      

    
    @Override
    public ObservableList<Cadeau> trierCadeauName() {
          
         ObservableList<Cadeau> Cadeau = FXCollections.observableArrayList();
        CadeauService A = new CadeauService();
        RouletteService Cat = new RouletteService();
       
       
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `cadeau` ORDER BY name";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cadeau Ar = new Cadeau();
            
               
               
                
                  Ar.setRoulette(Cat.load_data_modify(rs.getString(5)));

                Ar.setId(rs.getInt(1));               
                
                Ar.setName(rs.getString(2));
                Ar.setDescription(rs.getString(4));
                Ar.setType(rs.getString(3));
                Cadeau.add(Ar);
                
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier Cadeau  de par Name");
            System.out.println(ex);
        }
        return Cadeau;
       
    }
    
    
    
    
     @Override
    public ObservableList<Cadeau> trierCadeauType() {
          
         ObservableList<Cadeau> Cadeau = FXCollections.observableArrayList();
        CadeauService A = new CadeauService();
        RouletteService Cat = new RouletteService();
       
       
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `cadeau` ORDER BY type";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cadeau Ar = new Cadeau();
            
               
               
                
                  Ar.setRoulette(Cat.load_data_modify(rs.getString(5)));

                Ar.setId(rs.getInt(1));               
                
                Ar.setName(rs.getString(2));
                Ar.setDescription(rs.getString(4));
                Ar.setType(rs.getString(3));
                Cadeau.add(Ar);
                
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier Cadeau par type");
            System.out.println(ex);
        }
        return Cadeau;
       
    }
      @Override
    public ObservableList<Cadeau> trierCadeauDesc() {
          
         ObservableList<Cadeau> Cadeau = FXCollections.observableArrayList();
        CadeauService A = new CadeauService();
        RouletteService Cat = new RouletteService();
       
       
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `cadeau` ORDER BY description";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cadeau Ar = new Cadeau();
            
               
               
                
                  Ar.setRoulette(Cat.load_data_modify(rs.getString(2)));

                Ar.setId(rs.getInt(1));               
                
                Ar.setName(rs.getString(3));
                Ar.setDescription(rs.getString(5));
                Ar.setType(rs.getString(4));
                Cadeau.add(Ar);
                
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier Cadeau par Description");
            System.out.println(ex);
        }
        return Cadeau;
       
    }
   

   

    }
    
    
