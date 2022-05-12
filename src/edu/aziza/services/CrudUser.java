/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import edu.aziza.entities.User;
import edu.aziza.utils.BCrypt;
import edu.aziza.utils.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author yassin
 */
public class CrudUser {

    public boolean Inscription(User t) {
        try {
            String requete = "INSERT INTO users (Nom,Prenom,genre,Email,Password,roles,image)"
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getGenre());
            pst.setString(4, t.getEmail());
            pst.setString(5, BCrypt.hashpw(t.getPassword(), BCrypt.gensalt()));
            pst.setString(6, t.getRoles());
            pst.setString(7, t.getImage());

            pst.executeUpdate();

            System.out.println("User inscrit ✔");
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    public String Login(String Email, String Password) {
        try {
            String req = "select * from users ";
            Statement ste = DataSource.getInstance().getCnx().createStatement();
            ResultSet res = ste.executeQuery(req);
            System.out.println("" + Email + "" + Password);
            while (res.next()) {
                if (res.getString("Email").equals(Email) && (BCrypt.checkpw(Password, res.getString("password")) == true)) {

                    return res.getString("roles");
                }

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return "false";
    }

    public static int checkIfUserExists(String Email) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM users WHERE Email = BINARY ?";
            PreparedStatement preparedStatement = DataSource.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, Email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return count;
    }

    public int finIdUser(String Email, String Password) {
        try {
            String req = "select * from users ";

            Statement ste = DataSource.getInstance().getCnx().createStatement();
            ResultSet res = ste.executeQuery(req);
            while (res.next()) {
                if (res.getString("Email").equals(Email) && (BCrypt.checkpw(Password, res.getString("password")) == true)) {
                    return res.getInt("id");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return 0;
    }

    public boolean sendCodeRecuperationCompte(User t) {
        try {
            String sql = "UPDATE users SET reset_token = ? WHERE Email = ?";
            PreparedStatement preparedStatement = DataSource.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, t.getResetoken());
            preparedStatement.setString(2, t.getEmail());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;

    }

    public int VerifCodeRecuperation(String Email, String code) {
        Integer verif = 0;
        try {
            String req = "select * from users ";
            Statement ste = DataSource.getInstance().getCnx().createStatement();
            ResultSet res = ste.executeQuery(req);
            while (res.next()) {
                if (res.getString("Email").equals(Email) && res.getString("reset_token").equals(code)) {
                    return verif = 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException+: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return verif = 0;
    }

    public static boolean ChangePasswordByEmail(User t) { //hedhy bech nest79oulha fel Recupation Compte (Pass oublié)
        try {
            String sql = "UPDATE users SET password = ? WHERE Email = ?";
            PreparedStatement preparedStatement = DataSource.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, BCrypt.hashpw(t.getPassword(), BCrypt.gensalt()));
            preparedStatement.setString(2, t.getEmail());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    public List<User> AfficherAllUsers(User t) {
        List<User> UsersList = new ArrayList<>();
        try {
            String requete = "SELECT id,Nom,Prenom,genre,Email,roles,image FROM users  ORDER BY id DESC";
            Statement pst = DataSource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                User r = new User();

                ImageView img = new ImageView();
                Image image;
                try {
                    if (rs.getString("image") == null) {

                    } else if (rs.getString("image") != null) {

                        image = new Image(new FileInputStream(("C:\\xampp\\htdocs\\Projet\\Uploads\\" + rs.getString("image"))));
                        img.setImage(image);
                        img.setPreserveRatio(false);
                        img.setFitWidth(50);
                        img.setFitHeight(50);
                    }
                } catch (FileNotFoundException ex) {
                    try {
                        //System.out.println(ex.getMessage());
                        image = new Image(new FileInputStream(("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "nophoto.jpg")));
                        img.setImage(image);
                        img.setPreserveRatio(true);
                        img.setFitWidth(50);
                        img.setFitHeight(50);
                    } catch (FileNotFoundException ex1) {
                        // Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                //
                r.setImageView(img);

                r.setId(rs.getInt(1));
                r.setNom(rs.getString(2));
                r.setPrenom(rs.getString(3));
                r.setGenre(rs.getString(4));
                r.setEmail(rs.getString(5));
                r.setRoles(rs.getString(6));
                r.setImage(rs.getString(7));

                UsersList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return UsersList;
    }

    public boolean supprimerUser(int idUser) {
        try {
            String requete = "DELETE FROM users where id=" + String.valueOf(idUser) + "";
            PreparedStatement pst = DataSource.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.execute(requete);

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    public static boolean updateUser(User t) {
        try {
            String sql = "UPDATE Users SET nom = ?, prenom = ?, genre = ?, email = ?, password = ?, image = ? , roles = ? WHERE id = ?";
            PreparedStatement preparedStatement = DataSource.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setString(2, t.getPrenom());
            preparedStatement.setString(3, t.getGenre());
            preparedStatement.setString(4, t.getEmail());
            preparedStatement.setString(5, BCrypt.hashpw(t.getPassword(), BCrypt.gensalt()));
            preparedStatement.setString(6, t.getImage());
            preparedStatement.setString(7, t.getRoles());
            preparedStatement.setInt(8, t.getId());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }
}
