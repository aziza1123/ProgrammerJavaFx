/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import edu.aziza.entities.Cadeau;
import edu.aziza.entities.Reservation;
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
 * @author fatma
 */
public class ServiceReservation implements IService1<Reservation> {
     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reservation r) {
        try {
            String req = "INSERT INTO `reservation` (`nom`, `prenom`, `mail`,`num`) VALUES ('" + r.getNom() + "', '" + r.getPrenom() + "', '" + r.getMail() + "', '" + r.getNum() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
//    public void ajouter2(Reservation r) {
//        try {
//            String req = "INSERT INTO `personne` (`nom`, `prenom`, `mail`, `num`) VALUES (?,?,?,?)";
//            PreparedStatement ps = cnx.prepareStatement(req);
//            ps.setString(1, r.getNom());
//            ps.setString(2, r.getPrenom());
//            ps.setString(3, r.getMail());
//            ps.setString(4, r.getNum());
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reservation` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  
//     public void modifier2 (int id,Reservation p)
//    {
//        try {
//            PreparedStatement ps= cnx.prepareStatement("UPDATE reservation SET nom`=? , prenom`= ? , num`= ? ,`telephonenum`=?, `mail`=? WHERE id= '"+id+"'");
//           ps.setString(2, p.getPrenom());
//            ps.setString(1, p.getNom());
//            ps.setString(3, p.getNum());
//            ps.setString(4, p.getMail());
//
//
//            ps.execute();
//        } catch (SQLException ex) {
//        }
//    }

    @Override
    public void modifier(Reservation r) {
        try {
            String req = "UPDATE `reservation` SET `nom` = '" + r.getNom() + "', `prenom` = '" + r.getPrenom() + "', `mail` = '" + r.getMail() + "', `num` = '" + r.getNum() + "' WHERE `reservation`.`id` = " + r.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
                System.out.println("Resrvation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> getAll() {
       List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from reservation";
            Statement st = cnx.createStatement();// execution taa les requetes fel BD 
            ResultSet rs = st.executeQuery(req);// trajaa resultset
            while(rs.next()){
                Reservation a = new Reservation(rs.getInt("id"), rs.getString("nom") , rs.getString("prenom"), rs.getString("num"), rs.getString("mail") );
                list.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;  
    }
     
    public ObservableList<Reservation> Afficher() {
        ObservableList<Reservation> myList1 = FXCollections.observableArrayList();
        try {

            String requete3 = " SELECT * FROM reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Reservation e = new Reservation();
                myList1.add(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList1;
    }

    @Override
    public void modifier2(Reservation p, int id) {
 try {
            PreparedStatement ps= cnx.prepareStatement("UPDATE reservation SET nom`=? , prenom`= ? , num`= ? ,`telephonenum`=?, `mail`=? WHERE id= '"+id+"'");
           ps.setString(2, p.getPrenom());
            ps.setString(1, p.getNom());
            ps.setString(3, p.getNum());
            ps.setString(4, p.getMail());


            ps.execute();
        } catch (SQLException ex) {
        }    }

   
    
}
