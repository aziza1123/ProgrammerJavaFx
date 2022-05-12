/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.aziza.entities.User;
import edu.aziza.services.CrudUser;
import edu.aziza.services.mail;
import edu.aziza.utils.Notification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import validate.RequieredFieldsValidators;


/**
 * FXML Controller class
 *
 * @author yassin
 */
public class LoginController implements Initializable {

    @FXML
    private Pane PageLogin;
    @FXML
    private JFXTextField txtEmailLogin;
    @FXML
    private JFXPasswordField txtPasswordLogin;
    @FXML
    private JFXTextField txtShowPassword;
    @FXML
    private Pane paneIcon;
    @FXML
    private FontAwesomeIconView icon;
    @FXML
    private Label passOublier;
    @FXML
    private StackPane stck;
    @FXML
    private AnchorPane root;
    public static int idUserConnected;
    public static String roleUserConnected ="";
    double xOffset, yOffset;
    ////////////////////////////
    User rec = new User();
    CrudUser work = new CrudUser();
    mail mail = new mail();
    Notification notif = new Notification();
    @FXML
    private JFXButton btnLogin;

    ////////////////////////////
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        setValidations();
        showPassword();
    }

    private void setValidations() {
        RequieredFieldsValidators.toJFXTextField(txtEmailLogin);
        RequieredFieldsValidators.toJFXPasswordField(txtPasswordLogin);
    }

    private void resetValidation() {
        txtEmailLogin.resetValidation();
        txtPasswordLogin.resetValidation();

    }

    private void showPassword() {
        txtShowPassword.managedProperty().bind(icon.pressedProperty());
        txtShowPassword.visibleProperty().bind(icon.pressedProperty());
        txtShowPassword.textProperty().bindBidirectional(txtPasswordLogin.textProperty());

        txtPasswordLogin.managedProperty().bind(icon.pressedProperty().not());
        txtPasswordLogin.visibleProperty().bind(icon.pressedProperty().not());

        icon.pressedProperty().addListener((o, oldVal, newVal) -> {
            if (newVal) {
                icon.setIcon(FontAwesomeIcon.EYE);
            } else {
                icon.setIcon(FontAwesomeIcon.EYE_SLASH);
            }
        });
    }

    private void NavigationToinscription(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }

    @FXML
    private void PassOublierClicked(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("ForgetPassword.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }

    @FXML
    private void close_app(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void GoToSignUp(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("inscription.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }

    @FXML
    private void GoToSign(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }

    @FXML
    private void connexion(MouseEvent event) throws IOException {

        if (txtEmailLogin.getText().isEmpty()) {
            txtEmailLogin.requestFocus();
            shake(txtEmailLogin);
            return;
        }
        if (txtPasswordLogin.getText().isEmpty()) {
            txtPasswordLogin.requestFocus();
            shake(txtPasswordLogin);
            return;
        }

        roleUserConnected = work.Login(txtEmailLogin.getText(), txtPasswordLogin.getText());
        
        //System.out.println("" + roleConnectedUser);

        if (roleUserConnected.equals("[]")) { // User added from inscri
            idUserConnected = work.finIdUser(txtEmailLogin.getText(), txtPasswordLogin.getText());
            System.out.println("empty" + roleUserConnected);
                            Parent menu = FXMLLoader.load(getClass().getResource("FrontSponsor.fxml")); // interface lel User
                Stage window = new Stage();
                Scene scene = new Scene(menu);
               // window.initStyle(StageStyle.TRANSPARENT);
               // scene.setFill(Color.TRANSPARENT);
                window.setScene(scene);
                window.show();

                menu.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });
                menu.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        window.setX(event.getScreenX() - xOffset);
                        window.setY(event.getScreenY() - yOffset);
                    }
                });
                ((Stage) btnLogin.getScene().getWindow()).close();
        }
        if (roleUserConnected.contains("ROLE_USER")) { // user added from back
            idUserConnected = work.finIdUser(txtEmailLogin.getText(), txtPasswordLogin.getText());
            System.out.println("user " + roleUserConnected);
                            Parent menu = FXMLLoader.load(getClass().getResource("FrontSponsor.fxml")); // interface lel User
                Stage window = new Stage();
                Scene scene = new Scene(menu);
              //  window.initStyle(StageStyle.TRANSPARENT);
              //  scene.setFill(Color.TRANSPARENT);
                window.setScene(scene);
                window.show();

                menu.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });
                menu.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        window.setX(event.getScreenX() - xOffset);
                        window.setY(event.getScreenY() - yOffset);
                    }
                });
                ((Stage) btnLogin.getScene().getWindow()).close();
        }
        if (roleUserConnected.contains("ROLE_ADMIN") && !roleUserConnected.contains("ROLE_USER")) { // user added from back
            idUserConnected = work.finIdUser(txtEmailLogin.getText(), txtPasswordLogin.getText());
            System.out.println("admin " + roleUserConnected);
            
            

                Parent menu = FXMLLoader.load(getClass().getResource("HomeBack.fxml")); // interface lel User
                Stage window = new Stage();
                Scene scene = new Scene(menu);
               // window.initStyle(StageStyle.TRANSPARENT);
               // scene.setFill(Color.TRANSPARENT);
                window.setScene(scene);
                window.show();

                menu.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });
                menu.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        window.setX(event.getScreenX() - xOffset);
                        window.setY(event.getScreenY() - yOffset);
                    }
                });
                ((Stage) btnLogin.getScene().getWindow()).close();

        }
      
    }

    public static void shake(Node node) {
        new Shake(node).play();
    }
}
