/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import edu.aziza.entities.Commentaire;
import edu.aziza.services.Iservices;
import edu.aziza.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ServiceCommentaire implements Iservices<Commentaire> {
    
     Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void Ajouter(Commentaire u) throws SQLException {
       PreparedStatement ps;
        
   
        String query = "INSERT INTO `Commentaire`( `contenu`,`num`) VALUES ('"+u.getContenu()+"','"+u.getNum()+"')";
        try {
            ps = cnx.prepareStatement(query);

           
            ps.execute();    
            System.out.println(u);
         
        } catch (Exception e) { 
            System.out.println(e);
        }  



    }
    
    @Override
    public List<Commentaire> Affichertout() throws SQLException {
    List<Commentaire> list = new ArrayList();
    
        String requete = "SELECT * FROM `Commentaire`";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Commentaire r = new Commentaire();
               list.add(new Commentaire(rs.getInt("id"),rs.getString("contenu"),rs.getString("num")));
//            r.setId(rs.getInt(1));
//            r.setMedia(rs.getString(2));
//             r.setTitre(rs.getString(3));
//            r.setDescription(rs.getString(4));
           
            
            list.add(r);
            //Commentaire.add(r);
           
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }

    @Override
    public void Supprimer(Commentaire t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modifier(Commentaire t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
