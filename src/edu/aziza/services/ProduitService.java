 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import Alert.AlertDialog;
import edu.aziza.entities.Produit;
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
public class ProduitService implements IserviceProduit<Produit> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Produit p) {



        try {
            String requete = "INSERT INTO produit (nom,description,prix,quantite,img)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getDescription());
            pst.setFloat(3, p.getPrix());
            pst.setInt(4, p.getQuantite());
            pst.setString(5, p.getImg());

            pst.executeUpdate();

            System.out.println("Produit été ajouté ✔");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }

//         
    }

  
   public boolean Supprimer(int id) {
        try {
            String requete = "DELETE FROM produit where id=" + String.valueOf(id) + "";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);

            System.out.println("produit supprimée ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }


    @Override
    public List<Produit> recuperer() {
        //List<Produit> list = new ArrayList<>();
        ObservableList<Produit> Produit = FXCollections.observableArrayList();
        try {
            String req = "Select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Produit p = new Produit();
                // p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setPrix(rs.getFloat("prix"));
                p.setQuantite(rs.getInt("quantite"));
                p.setImg(rs.getString("img"));

                // p.setQuantite(rs.getInteger("prix"));
                Produit.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Produit;
    }

   
   public static boolean Modifier(Produit p) {
        try {
            String sql = "UPDATE produit SET nom= ?,description= ?, prix = ?, quantite= ?, img= ? WHERE id = ?";
            PreparedStatement preparedStatement = DataSource.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, p.getNom());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setFloat(3, p.getPrix());
            preparedStatement.setFloat(4, p.getQuantite());
            preparedStatement.setString(5, p.getImg());
            preparedStatement.setInt(6, p.getId());
            System.out.println("produit été modifié ✔");

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public ObservableList<Produit> trierProduitNom() {

        ObservableList<Produit> Produit = FXCollections.observableArrayList();
        ProduitService A = new ProduitService();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `produit` ORDER BY nom";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Produit Pr = new Produit();

                Pr.setId(rs.getInt(1));
                Pr.setNom(rs.getString(2));
                Pr.setDescription(rs.getString(3));
                // Pr.setPrix(rs.getString(3));
                Produit.add(Pr);

            }

        } catch (SQLException ex) {
            System.out.println("erreur trier Cadeau  de par Name");
            System.out.println(ex);
        }

        return Produit;

    }

    @Override
    public ObservableList<Produit> trierProduitPrix() {

        ObservableList<Produit> Produit = FXCollections.observableArrayList();
        ProduitService A = new ProduitService();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `produit` ORDER BY prix";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Produit Pr = new Produit();

                Pr.setId(rs.getInt(1));

                Pr.setNom(rs.getString(2));
                Pr.setDescription(rs.getString(3));
                //   Pr.setType(rs.getString(3));
                Produit.add(Pr);

            }

        } catch (SQLException ex) {
            System.out.println("erreur trier Produit par prix");
            System.out.println(ex);
        }
        return Produit;

    }

    @Override
    public ObservableList<Produit> trierProduitDesc() {

        ObservableList<Produit> Produit = FXCollections.observableArrayList();
        ProduitService A = new ProduitService();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `produit` ORDER BY description";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Produit Pr = new Produit();

                Pr.setId(rs.getInt(1));
                Pr.setNom(rs.getString(2));
                Pr.setDescription(rs.getString(3));
                //  Pr.setPrix(rs.getString(4));
                Produit.add(Pr);

            }

        } catch (SQLException ex) {
            System.out.println("erreur trier Produit par Description");
            System.out.println(ex);
        }
        return Produit;

    }
    @Override
    public void modifier(int id, Produit t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
