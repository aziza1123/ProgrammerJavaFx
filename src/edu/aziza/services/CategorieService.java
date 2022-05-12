/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;


import edu.aziza.entities.Categorie;
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
 * @author lenovo
 */
public class CategorieService implements Cat <Categorie> {
    Connection cnx = DataSource.getInstance().getCnx();



    @Override
    public void ajouter(Categorie c) {
        
        try {
            Statement st=cnx.createStatement();
             String query = "INSERT INTO `categorie`( `nom`,`description`) "
                    + "VALUES ('"
                    + c.getNom() + "','"
                    + c.getDescription() + "','";
                   
             st.executeUpdate(query);
             System.out.println("added");
                     
        } catch (SQLException ex) {
            System.out.println( ex);
        }
       

    }

    @Override
    public void supprimer(int id) {
          try {
            String req = "DELETE FROM categorie WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    


    

    @Override
    public List<Categorie> recuperer() {
     List<Categorie> list = new ArrayList<>();
     ObservableList<Categorie>Categorie = FXCollections.observableArrayList();
        try {
            String req = "Select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Categorie c = new Categorie();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
             
             
               
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    
   
      



    @Override
    public void modifier(int id,Categorie c) {
 try {
            PreparedStatement ps = cnx.prepareStatement("UPDATE categorie SET nom`=? , description`= ?   WHERE id=  '"+id+"'");
            ps.setString(1, c.getNom());
            ps.setString(2, c.getDescription());
  
           

             if(ps.executeUpdate() > 0)
            {
               
                JOptionPane.showMessageDialog(null, "Categorie Updated", "Edit Categorie", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("Product Updated");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Categorie Not Updated", "Edit Categorie", JOptionPane.ERROR_MESSAGE);
              //System.out.println("Some Error Message Here");  
            }
            System.out.println("Categorie Modifieé avec succées ");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}