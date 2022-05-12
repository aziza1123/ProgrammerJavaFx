/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import edu.aziza.entities.Activite;
import edu.aziza.entities.Reservation;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceActivite implements IService1<Activite> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Activite a) {
        try {
            String req = "INSERT INTO `activite` (`nom`, `description`,`prix`) VALUES ('" + a.getNom() + "', '" + a.getDescription() + "','" + a.getPrix() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Activite created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
//    public void ajouter2(Activite a) {
//        try {
//            String req = "INSERT INTO `personne` (`nom`, `description`, `photo`, `prix`) VALUES (?,?,?,?)";
//            PreparedStatement ps = cnx.prepareStatement(req);
//            ps.setString(1, a.getNom());
//            ps.setString(2, a.getDescription());
//            ps.setString(3, a.getPhoto());
//            ps.setFloat(4, a.getPrix());
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `activite` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("activite deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//    @Override
//     public void Supprimer(Activite a,int id) throws SQLException {
//        PreparedStatement ps;
//
//        //String query = " DELETE publication WHERE activite.`id`='"+id+"'";
//         String query = "DELETE FROM activite WHERE id = " + id;
//        
//  
//        try {
//            ps = c.prepareStatement(query);
//
////           
//            ps.execute();
//
//            System.out.println("suppression ");
//        } catch (Exception e) {
//            System.out.println(e);
//        } 
//    }

    @Override
    public void modifier(Activite a) {
        try {
            String req = "UPDATE `activite` SET `nom` = '" + a.getNom() + "', `description` = '" + a.getDescription() + "', `prix` = '" + a.getPrix() + "' WHERE `activite`.`id` = " + a.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
                System.out.println("Activite updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Activite> getAll() {
        List<Activite> list = new ArrayList<>();
        try {
            String req = "Select * from activite";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Activite a = new Activite(rs.getString("nom"),rs.getString("description"), rs.getFloat("prix"));
                list.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    public ObservableList<Activite> Afficher() {
        ObservableList<Activite> myList1 = FXCollections.observableArrayList();
        try {

            String requete3 = " SELECT * FROM activite";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Activite e = new Activite(rs.getString("nom"),rs.getString("description"), rs.getFloat("prix"));
                myList1.add(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList1;
    }


//    @Override
//    public void modifier2(Activite p, int id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    
    
    
    
//      @Override
//    public void modifier2(Activite p, int id) {
// try {
//            PreparedStatement ps= cnx.prepareStatement("UPDATE activite SET nom`=? , num`= ? ,`telephonenum`=?, `mail`=? WHERE id= '"+id+"'");
//           ps.setString(2, p.getDescription());
//            ps.setString(1, p.getNom());
//            ps.setString(3, p.getPrix());
//           
//
//
//            ps.execute();
//        } catch (SQLException ex) {
//        }    }

    @Override
    public void modifier2(Activite p, int id) {
    }

}
