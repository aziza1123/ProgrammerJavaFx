/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.aziza.entities.User;
import static edu.aziza.gui.LoginController.shake;
import edu.aziza.services.CrudUser;
import edu.aziza.services.mail;
import edu.aziza.utils.Notification;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


/**
 * FXML Controller class
 *
 * @author yassin
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private StackPane stck;
    @FXML
    private AnchorPane root;
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
    private Button btnSendCode;
    @FXML
    private JFXTextField txtCodeDeRecuperation;
    @FXML
    private Button btnVerifieCode;
    ////////////////////////////
    User rec = new User();
    CrudUser work = new CrudUser();
    mail mail = new mail();
    Notification notif = new Notification();
    @FXML
    private ImageView iconforgetPassword;
    @FXML
    private Pane PaneNewPass;
    @FXML
    private JFXButton btnChangePassword;

    ////////////////////////////
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        showPassword();
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
    private void close_app(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void SendCodeClicked(MouseEvent event) {
        String Email = txtEmailLogin.getText().trim();

        if (txtEmailLogin.getText().isEmpty()) {
            txtEmailLogin.requestFocus();
            shake(txtEmailLogin);
            return;
        }
        if (isValidEmailAddress(txtEmailLogin.getText()) == false) {
            txtEmailLogin.requestFocus();
            shake(txtEmailLogin);
            return;
        }

        // btnChangePassword.setVisible(true);  
        txtCodeDeRecuperation.setVisible(true);
        btnVerifieCode.setVisible(true);
        iconforgetPassword.setVisible(true);

        /////Hedhy Generate Code Recuperation Compte 
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String codeRecuperation = String.valueOf(number);
        String email = txtEmailLogin.getText();
        String msg = "Code Recuperation = " + codeRecuperation;
        ///////////////////////////////////////////////// 
        rec.setEmail(email);
        rec.setResetoken(codeRecuperation);

        boolean result = work.sendCodeRecuperationCompte(rec);
        if (result) {
            mail.sendMail(msg, email);
            notif.notificationS("Cool", "Now Check your email !");
        } else {
            notif.notificationF("Soryy", "Oppss !");
        }
    }

    @FXML
    private void VerifierCodeClicked(MouseEvent event) {
        String Code = txtCodeDeRecuperation.getText();
        String Email = txtEmailLogin.getText();

        int verif = work.VerifCodeRecuperation(Email, Code);
        if (verif == 1) {
            notif.notificationS("Cool", "Now Type a New Password !");
            PaneNewPass.setVisible(true);
            btnChangePassword.setVisible(true);

        }
        if (verif == 0) {
            notif.notificationF("Soryy", "Oppss The Code is Wrong !");
        }
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    @FXML
    private void ChangePasswordClicked(MouseEvent event) throws IOException {
        String Code = txtCodeDeRecuperation.getText();
        String Email = txtEmailLogin.getText();

        int verif = work.VerifCodeRecuperation(Email, Code);
        if (verif == 1) {

            String password = txtPasswordLogin.getText();
            if (password.isEmpty()) {
                txtPasswordLogin.requestFocus();
                shake(txtPasswordLogin);
                return;
            }

            if (password.length() < 4) {
                txtPasswordLogin.requestFocus();
                shake(txtPasswordLogin);
                notif.notificationF("Password", " More then 4 char !");
                return;
            }

            rec.setEmail(Email);
            rec.setPassword(password);

            boolean result = work.ChangePasswordByEmail(rec);
            if (result) {
                notif.notificationS("Cool", "Password Changed !");
                GoToLoginPage();
            } else {
                notif.notificationF("Soryy", "Oppss !");
            }
        }

        if (verif == 0) {
            notif.notificationF("Soryy", "Oppss The Code is Wrong !");
        }
    }

    private void GoToLoginPage() throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }
}
