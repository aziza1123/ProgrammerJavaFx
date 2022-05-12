/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;


import edu.aziza.entities.Ticket;
import edu.aziza.gui.EventController;
import edu.aziza.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author USER
 */
public class ServiceTicket implements IServiceEvent<Ticket>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Ticket p) {
        System.out.println(EventController.EvenementStatic.getPlace_dispo());
     EventController a = new EventController();
    int nbr = Integer.parseInt(EventController.EvenementStatic.getPlace_dispo())-1;
    String snbr = String.valueOf(nbr);
        try {
            String req = "INSERT INTO `ticket`( `name`, `description`, `type`, `qte`, `prix`, `event_id`) VALUES (?,?,?,?,?,?)";
            String queryy = "UPDATE event SET `place_dispo` ="+snbr+" WHERE `event`.`id` ='"+EventController.id_evenement_ticket+"'";
                        PreparedStatement pss = cnx.prepareStatement(queryy);
                        pss.executeUpdate();

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getType()); 
            ps.setString(4, p.getQte());
            ps.setString(5, p.getPrix());
           ps.setInt(6, p.getEvent_id());
     
          
           
           
           ps.executeUpdate();
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
//       try {
//            String req = "DELETE FROM `ticket` WHERE id = " + id;
//            Statement st = cnx.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Ticket deleted !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    
        PreparedStatement ps;

        String req = "DELETE FROM `ticket` WHERE id = " + id;
  
        try {
            ps = cnx.prepareStatement(req);

//            ps.setInt(1,p.getEtat());
            //ps.setInt(2,r.getId());
            ps.execute();

            System.out.println("suppression de produit");
        } catch (Exception e) {
            System.out.println(e);
        } 
    
    
    }
    

    @Override
    public void modifier(int id,Ticket p) {
     try {
            String req = "UPDATE `ticket` SET `id`='" + p.getId() + "', `name`='" + p.getName()+ "', `description`='" + p.getDescription()+ "',`type`='" + p.getType()+ "',`qte`='" + p.getQte()+ "',`prix`='" + p.getPrix()+ "' WHERE `ticket`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ticket updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}

    @Override
    public List<Ticket> getAll() {
          ObservableList<Ticket> list = FXCollections.observableArrayList();
                  try {
            String req = "Select * from ticket WHERE ticket.`event_id`='"+EventController.id_evenement_ticket+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Ticket p = new Ticket( rs.getString("name"), rs.getString("description"), rs.getString("type"), rs.getString("qte"), rs.getString("prix"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        }
    
    public long Recherche1() throws SQLException {
        

        List<Ticket> p = getAll();
        return p.stream().filter(b -> b.getType().equals("vip")).count();
    }

    public long Recherche2() throws SQLException {
        

        List<Ticket> p = getAll();
        return p.stream().filter(b -> b.getType().equals("normal")).count();
    }

    public long Recherche3() throws SQLException {

        List<Ticket> p = getAll();
        return p.stream().filter(b -> b.getType().equals("double")).count();
    }
      public long Recherche4() throws SQLException {

        List<Ticket> p = getAll();
        return p.stream().filter(b -> b.getType().equals("single")).count();
    }

    @Override
    public List<Ticket> getAll1() {
        ArrayList<Ticket> myList = new ArrayList<>();
        try {
            String req = "Select * from ticket ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Ticket p = new Ticket( rs.getString("name"), rs.getString("description"), rs.getString("type"), rs.getString("qte"), rs.getString("prix"));
              
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }
    
    
}
