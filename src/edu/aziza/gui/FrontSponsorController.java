/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import com.jfoenix.controls.JFXMasonryPane;
import edu.aziza.entities.Sponsor;
import edu.aziza.services.CrudSponsor;
import edu.aziza.utils.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class FrontSponsorController implements Initializable {

    @FXML
    private StackPane stck;
    @FXML
    private AnchorPane rootUsers;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private Circle imgOnline;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ImageView imageViewProfile;
    @FXML
    private Text textName;
    @FXML
    private Text textUserType;
    private final JFXMasonryPane mansoryPane = new JFXMasonryPane();
    ////
    Sponsor rec = new Sponsor();
    CrudSponsor work = new CrudSponsor();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              scrollPane.setStyle("-fx-background: rgb(255,255,255);\n -fx-background-color: rgb(255,255,255)");
        initMansoryCard();
        LoadCardProduits();  
         loadData(LoginController.idUserConnected);
    }    
    private void initMansoryCard() {
        mansoryPane.setPadding(new Insets(15, 15, 15, 15));
        mansoryPane.setVSpacing(5);
        mansoryPane.setHSpacing(5);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mansoryPane);

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
                if(rs.getString(2).contains("ROLE_ADMIN") && !rs.getString(2).contains("ROLE_USER"))
                {
                     textUserType.setText("Admin");
                }
                if (rs.getString(2).contains("ROLE_USER") || rs.getString(2).equals("[]"))
                {
                 textUserType.setText("User");
                }
                try {
                    Image image = new Image(new FileInputStream(("C:\\xampp\\htdocs\\Projet\\Uploads\\" + rs.getString("image"))));
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
 private void LoadCardProduits() {

        mansoryPane.getChildren().clear();
        List<Sponsor> listeProduits = new ArrayList<>();
        listeProduits = work.Afficher(rec);

        if (!listeProduits.isEmpty()) {
            for (int i = 0; i < listeProduits.size(); i++) {
                VBox root = new VBox();
                ImageView PreviewImageProduit = new ImageView();
                PreviewImageProduit.setFitWidth(120);
                PreviewImageProduit.setFitHeight(120);
                PreviewImageProduit.setPreserveRatio(false);
                PreviewImageProduit.setSmooth(true);
                PreviewImageProduit.setCache(true);

                String nom = listeProduits.get(i).getNom();

                //
                File dest = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\");
                File f = new File(dest, listeProduits.get(i).getImage());
                //
                if (listeProduits.get(i).getImage() != null) {
                    if (!f.exists()) {
                        try {
                            Image img = new Image(new FileInputStream("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "nophoto.jpg"));
                            PreviewImageProduit.setImage(img);
                        } catch (FileNotFoundException ex) {
                            //Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            // Image img = new Image(new FileInputStream(listeUser.get(i).getCarteidentity()));
                            Image img = new Image(new FileInputStream("C:\\xampp\\htdocs\\Projet\\Uploads\\" + listeProduits.get(i).getImage()));
                            PreviewImageProduit.setImage(img);
                        } catch (FileNotFoundException ex) {
                            //   Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else if (listeProduits.get(i).getImage() == null) {

                    //identityView.setImage(new Image(getClass().getResource("/resources/image/empty-image.jpg").toString()));
                    File file = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "nophoto.jpg");
                    Image imagee = new Image(file.toURI().toString());
                    PreviewImageProduit.setImage(imagee);
                }
                root.setPadding(new Insets(12, 17, 17, 17));
                root.setSpacing(13);

                ///
                root.setStyle("-fx-background-color: #fff; -fx-background-radius: 15px;-fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                //labels[i].setTextFill(Color.color(1, 0, 0));

                Label LabelNom = new Label("" + nom);              
                LabelNom.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setStyle("-fx-font-weight: bold");

     

      
                root.getChildren().addAll(LabelNom, PreviewImageProduit );
                root.setAlignment(Pos.CENTER);
                mansoryPane.getChildren().add(root);
              


            }

        }

    }
    @FXML
    private void close_app(ActionEvent event) {
       System.exit(0);
    }

    @FXML
    private void LogoutClicked(MouseEvent event) throws IOException {
      Parent menu = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
        LoginController.idUserConnected = 0;
    }

      @FXML
    private void GotoSponsor(MouseEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("FrontSponsor.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);

    }
    
}
