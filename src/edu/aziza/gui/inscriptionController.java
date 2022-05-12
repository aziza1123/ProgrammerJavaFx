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
import edu.aziza.utils.DefaultProfileImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import validate.RequieredFieldsValidators;
import validate.TextFieldMask;


/**
 * FXML Controller class
 *
 * @author yassin
 */
public class inscriptionController implements Initializable {

    @FXML
    private StackPane stck;
    @FXML
    private AnchorPane rootLogin;
    @FXML
    private Pane PageLogin;
    @FXML
    private JFXTextField txtEmailLogin;
    @FXML
    private JFXTextField txtPrenomLogin;
    @FXML
    private JFXTextField txtNomLogin;
    @FXML
    private JFXComboBox<String> comboSexLogin;
    @FXML
    private ImageView iconPrenom;
    @FXML
    private ImageView iconNom;
    @FXML
    private ImageView iconSexe;
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
    ////////////////////////////
    User rec = new User();
    CrudUser work = new CrudUser();
    mail mail = new mail();

    ////////////////////////////
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboSexLogin.getItems().addAll("Homme", "Femme");

        setValidations();
        showPassword();
        setMask();
    }

    private void setMask() {
        TextFieldMask.onlyLetters(txtNomLogin, 8);
        TextFieldMask.onlyLetters(txtPrenomLogin, 8);
    }

    private void setValidations() {
        RequieredFieldsValidators.toJFXTextField(txtNomLogin);
        RequieredFieldsValidators.toJFXTextField(txtPrenomLogin);
        RequieredFieldsValidators.toJFXTextField(txtEmailLogin);
        RequieredFieldsValidators.toJFXPasswordField(txtPasswordLogin);
        RequieredFieldsValidators.toJFXComboBox(comboSexLogin);
    }

    private void resetValidation() {
        txtNomLogin.resetValidation();
        txtPrenomLogin.resetValidation();
        txtEmailLogin.resetValidation();
        txtPasswordLogin.resetValidation();
        comboSexLogin.resetValidation();
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
    private void inscription(MouseEvent event) throws FileNotFoundException, IOException {

        String Email = txtEmailLogin.getText().trim();
        if (txtPrenomLogin.getText().isEmpty()) {
            txtPrenomLogin.requestFocus();
            shake(txtPrenomLogin);
            return;
        }
        if (txtNomLogin.getText().isEmpty()) {
            txtNomLogin.requestFocus();
            shake(txtNomLogin);
            return;
        }
        if (txtEmailLogin.getText().isEmpty()) {
            txtEmailLogin.requestFocus();
            shake(txtEmailLogin);
            return;
        }
        if (isValidEmailAddress(txtEmailLogin.getText()) == false) {
            txtEmailLogin.requestFocus();
            shake(txtEmailLogin);
            //AlertsBuilder.create(AlertType.SUCCES, stckLogin, rootLogin, PageLogin, "Email malformed !");
            return;
        }
        if (work.checkIfUserExists(Email) != 0) {
            // AlertsBuilder.create(AlertType.ERROR, stckLogin, rootLogin, PageLogin, Constants.MESSAGE_USER_ALREADY_EXISTS);
            txtEmailLogin.requestFocus();
            shake(txtEmailLogin);
            return;
        }
        if (txtPasswordLogin.getText().isEmpty()) {
            txtPasswordLogin.requestFocus();
            shake(txtPasswordLogin);
            return;
        }
        if (txtPasswordLogin.getText().length() < 4) {
            txtPasswordLogin.requestFocus();
            shake(txtPasswordLogin);
            return;
        }

        if (comboSexLogin.getSelectionModel().getSelectedItem() == null) {
            comboSexLogin.requestFocus();
            shake(comboSexLogin);
            return;
        }

        rec.setNom(txtNomLogin.getText());
        rec.setPrenom(txtPrenomLogin.getText());
        rec.setGenre(String.valueOf(comboSexLogin.getSelectionModel().getSelectedItem()));
        rec.setEmail(txtEmailLogin.getText());
        rec.setPassword(txtPasswordLogin.getText());
        rec.setRoles("[]");
        rec.setImage(DefaultProfileImage.getImage(txtNomLogin.getText()));

        //Controle saisie ky maykhterch sexe te3o
        String comb = comboSexLogin.getSelectionModel().getSelectedItem();
        if (comboSexLogin == null) {
            shake(comboSexLogin);
            return;
        }


        boolean result = work.Inscription(rec); // Fonction AjoutUser
        if (result) {
            //AlertsBuilder.create(AlertType.SUCCES, stckLogin, rootLogin, PageLogin, "Now Check your email to confirm your Account");
            /// nefarghou les champs be3ed majoutina
            txtNomLogin.clear();
            txtPrenomLogin.clear();
            comboSexLogin.getSelectionModel().clearSelection();
            txtEmailLogin.clear();
            txtPasswordLogin.clear();
            GoToLoginPage();

        } else {
            //AlertsBuilder.create(AlertType.ERROR, stckLogin, rootLogin, PageLogin, Constants.MESSAGE_Error);
        }

    }

    private void GoToLoginPage() throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }

    public static void shake(Node node) {
        new Shake(node).play();
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
}
