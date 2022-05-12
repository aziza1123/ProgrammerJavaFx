/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import edu.aziza.utils.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class HomeBackController implements Initializable {

    @FXML
    private Button Button_Search;
    @FXML
    private ImageView imageViewProfile;
    @FXML
    private Text textName;
    @FXML
    private Text textUserType;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private ImageView usericon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        loadData(LoginController.idUserConnected);

    }

    private void loadData(int idUser) {
        try {
            String sql = "SELECT Nom,roles,image FROM users where id=" + String.valueOf(idUser) + "";
            PreparedStatement preparedStatement = DataSource.getInstance().getCnx().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                // textName.setText(rs.getString(1) + " " + rs.getString(2));
                textName.setText(rs.getString(1));
                //textUserType.setText(rs.getString(2));
                if (rs.getString(2).contains("ROLE_ADMIN") && !rs.getString(2).contains("ROLE_USER")) {
                    textUserType.setText("Admin");
                }
                if (rs.getString(2).contains("ROLE_USER") || rs.getString(2).equals("[]")) {
                    textUserType.setText("User");
                }
                try {
                    Image image = new Image(new FileInputStream(("C:\\wamp64\\www\\Salma\\" + rs.getString("image"))));
                    imageViewProfile.setImage(image);
                } catch (FileNotFoundException ex) {
                    //Logger.getLogger(BackAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
    }

    @FXML
    private void FindByLibelle(ActionEvent event) {
    }

    @FXML
    private void LogoutClicked(MouseEvent event) throws IOException {
        AnchorPane.getChildren().clear();
        AnchorPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("Login.fxml")));
        LoginController.idUserConnected = 0;

    }

    @FXML
    private void GoToActivity(MouseEvent event) throws IOException {
         Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("Activite.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void GoToUsers(MouseEvent event) throws IOException {
        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("BackAdmin.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

//        Parent menu = FXMLLoader.load(getClass().getResource("BackAdmin.fxml")); // interface lel User
//        Stage window = new Stage();
//        Scene scene = new Scene(menu);
//        // window.initStyle(StageStyle.TRANSPARENT);
//        // scene.setFill(Color.TRANSPARENT);
//        window.setScene(scene);
//        window.show();
    }

    @FXML
    private void GoToSponsors(MouseEvent event) throws IOException {
//        Parent menu = FXMLLoader.load(getClass().getResource("SponsorBack.fxml")); // interface lel User
//        Stage window = new Stage();
//        Scene scene = new Scene(menu);
//        // window.initStyle(StageStyle.TRANSPARENT);
//        // scene.setFill(Color.TRANSPARENT);
//        window.setScene(scene);
//        window.show();

        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("SponsorBack.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void GoToRoulette(MouseEvent event) throws IOException {

//        Parent menu = FXMLLoader.load(getClass().getResource("test.fxml")); // interface lel User
//        Stage window = new Stage();
//        Scene scene = new Scene(menu);
//        // window.initStyle(StageStyle.TRANSPARENT);
//        // scene.setFill(Color.TRANSPARENT);
//        window.setScene(scene);
//        window.show();
        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void GoToPorduits(MouseEvent event) throws IOException {

        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutProduit.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void GoToEvent(MouseEvent event) throws IOException {
//        Parent menu = FXMLLoader.load(getClass().getResource("Event.fxml")); // interface lel User
//        Stage window = new Stage();
//        Scene scene = new Scene(menu);
//        // window.initStyle(StageStyle.TRANSPARENT);
//        // scene.setFill(Color.TRANSPARENT);
//        window.setScene(scene);
//        window.show();
//        
        
              Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("Event.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void GoToCadeau(MouseEvent event) throws IOException {
         Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("test2.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void GoToPublication(MouseEvent event) throws IOException {
        
               Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("naderpub.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void GoToCategory(MouseEvent event) throws IOException {

        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutCategorie.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void GoToReservation(MouseEvent event) throws IOException {
         Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("Reservtion.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void GoToTickets(MouseEvent event) throws IOException {
         Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("TicketF.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void BackToActivity(MouseEvent event) throws IOException {
        
    }

 

    @FXML
    private void GoToComments(MouseEvent event) throws IOException {
         Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("naderpub.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void GoToCategorie(MouseEvent event) {
//         Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        stageclose.close();
//        Parent root = FXMLLoader.load(getClass().getResource("AjoutCategorie.fxml"));
//        Stage stage = new Stage();
//
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//        stage.show();
    }

}
