/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;



import edu.aziza.entities.Roulette;
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
import javax.swing.JOptionPane;

/**
 *
 * @author 21650
 */
public class RouletteService implements Roult <Roulette>{
     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Roulette t) {
       try {
           String req2 = "INSERT INTO `roulette`(`name`,`description`,`debut`,`fin`) values (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getDebut());
            ps.setString(4, t.getFin());
            
            ps.executeUpdate();
            System.out.println("ajout d une roulette");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
    
        

//    public void modifer(Roulette t) {
//        try {
//            String req = "UPDATE `roulette` SET `name` = '" + t.getName() + "', `description` = '" + t.getDescription() + "', `debut` = '" + t.getDebut()+ "', `fin` = '" + t.getFin()+ "' WHERE `roulette`.`id` = " + t.getId();
//            Statement st = cnx.createStatement();
//            st.executeUpdate(req);
//            System.out.println("roulette updated !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    @Override
    public void supprimer(int id) {
           try {
            String req = "DELETE FROM `roulette` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("roulette deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List <Roulette> recuperer() {
     //List <Roulette> list = new ArrayList<>();
     ObservableList<Roulette> Roulette = FXCollections.observableArrayList();
        try {
            String req = "Select * from roulette";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Roulette t = new Roulette();
                t.setId(rs.getInt(1));
                t.setName(rs.getString("name"));
                t.setDescription(rs.getString("description"));
                t.setDebut(rs.getString("debut"));
                t.setFin(rs.getString("fin"));

                Roulette.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Roulette;
    }

    @Override
    public void modifier(int id, Roulette t) {
        try {
     PreparedStatement ps = cnx.prepareStatement("UPDATE roulette SET `name`=?  , `description`= ? ,`debut`= ? ,`fin`= ? WHERE id= '"+id+"'");
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getDebut());
            ps.setString(4, t.getFin());

             if(ps.executeUpdate() > 0)
            {
               
                JOptionPane.showMessageDialog(null, "Product Updated", "Edit Product", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("Product Updated");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Product Not Updated", "Edit Product", JOptionPane.ERROR_MESSAGE);
              //System.out.println("Some Error Message Here");  
            }
            System.out.println("Roulette Modifieé avec succées ");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Roulette load_data(String name) {
       Statement stm = null;
        Roulette Cat = new Roulette();
        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `roulette` WHERE name='" + name +  "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                Cat.setId(rs.getInt(1));
     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Cat;
    }
    @Override
    public ObservableList<String> getValuesRoulette() {
    
        
        ObservableList<String> Roulette = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT name  FROM roulette";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Roulette.add(rs.getString("name"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
        return Roulette;
        
    }
    
    @Override
    public Roulette load_data_modify(String id) { // charger données pour la modification

        Statement stm = null;
        Roulette r = new Roulette();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `roulette`  WHERE id='" + id + "' OR name='" + id +  "' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                r.setId(rst.getInt("id"));
                r.setName(rst.getString("name"));
              

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return r;

    }
    

    }

    
  

