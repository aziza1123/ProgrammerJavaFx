/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import edu.aziza.entities.Sponsor;
import edu.aziza.utils.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author yassin
 */
public class CrudSponsor {

    public boolean Ajouter(Sponsor p) {
        try {
            String requete = "INSERT INTO sponsor (nom_sponsor,date_fin_contrat,image_sponsor)"
                    + "VALUES (?,?,?)";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setDate(2, p.getDateFin());
            pst.setString(3, p.getImage());

            pst.executeUpdate();

            System.out.println("sponsor été ajouté ✔");
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    public List<Sponsor> Afficher(Sponsor t) {
        List<Sponsor> List = new ArrayList<>();
        try {
            String requete = "SELECT nom_sponsor,date_fin_contrat,image_sponsor,id_sponsor FROM sponsor ORDER BY id_sponsor DESC";
            Statement pst = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Sponsor r = new Sponsor();
                                ImageView img = new ImageView();
                Image image;
                try {
                    if (rs.getString("image_sponsor") == null) {

                    } else if (rs.getString("image_sponsor") != null) {

                        image = new Image(new FileInputStream(("C:\\wamp64\\www\\Salma\\" + rs.getString("image_sponsor"))));
                        img.setImage(image);
                        img.setPreserveRatio(false);
                        img.setFitWidth(50);
                        img.setFitHeight(50);
                    }
                } catch (FileNotFoundException ex) {
                    try {
                        System.out.println(ex.getMessage());
                        image = new Image(new FileInputStream(("C:\\wamp64\\www\\Salma\\" + "nophoto.jpg")));
                        img.setImage(image);
                        img.setPreserveRatio(true);
                        img.setFitWidth(50);
                        img.setFitHeight(50);
                    } catch (FileNotFoundException ex1) {
                        // Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                //
                r.setImageview(img);
                r.setNom(rs.getString(1));
                r.setDateFin(rs.getDate(2));
                r.setImage(rs.getString(3));
                r.setId(rs.getInt(4));

                List.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return List;
    }

    public static boolean Modifier(Sponsor t) {
        try {
            String sql = "UPDATE sponsor SET nom_sponsor= ?,date_fin_contrat = ?, image_sponsor = ? WHERE id_sponsor = ?";
            PreparedStatement preparedStatement = DataSource.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setDate(2, t.getDateFin());
            preparedStatement.setString(3, t.getImage());
            preparedStatement.setInt(4, t.getId());

            System.out.println("sponsor été modifié ✔");

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }
    
        public boolean Supprimer(int id) {
        try {
            String requete = "DELETE FROM sponsor where id_sponsor=" + String.valueOf(id) + "";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);

            System.out.println("sponsor supprimée ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }
}
