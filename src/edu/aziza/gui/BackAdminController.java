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
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import edu.aziza.entities.User;
import edu.aziza.services.CrudUser;
import edu.aziza.services.mail;
import edu.aziza.utils.DataSource;
import edu.aziza.utils.DefaultProfileImage;
import edu.aziza.utils.Notification;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.controlsfx.control.ToggleSwitch;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class BackAdminController implements Initializable {

    @FXML
    private ImageView imageViewProfile;
    @FXML
    private Text textName;
    @FXML
    private Text textUserType;
    @FXML
    private TabPane PaneTableau;
    @FXML
    private TableView<User> TableViewUsers;
    @FXML
    private TableColumn<User, String> col_NomUser;
    @FXML
    private TableColumn<User, String> col_PrenomUser;
    @FXML
    private TableColumn<User, ImageView> col_SexeUser;
    @FXML
    private TableColumn<User, String> col_EmailUser;
    @FXML
    private TableColumn<User, JFXButton> col_RoleUser;
    @FXML
    private TableColumn<User, ImageView> col_image;
    @FXML
    private JFXPasswordField txtPasswordLogin;
    @FXML
    private JFXComboBox<String> comboRole;
    @FXML
    private ImageView iconRole;
    @FXML
    private JFXTextField txtEmailLogin1;
    @FXML
    private JFXTextField txtPrenomLogin1;
    @FXML
    private JFXTextField txtNomLogin1;
    @FXML
    private ImageView iconPrenom1;
    @FXML
    private ImageView iconNom1;
    @FXML
    private ImageView iconSexe1;
    @FXML
    private JFXTextField txtShowPassword1;
    @FXML
    private Pane paneIcon;
    @FXML
    private FontAwesomeIconView icon1;
    private ObservableList<User> ListUsers;
    @FXML
    private JFXComboBox<String> comboGenre;
    ////////////////////////////
    User rec = new User();
    CrudUser work = new CrudUser();
    mail mail = new mail();
    Notification notif = new Notification();
    @FXML
    private TextField txtSearch;
    @FXML
    private AnchorPane rootUsers;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private Circle imgOnline;
    @FXML
    private Label dateTime;
    @FXML
    private StackPane stck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPassword();

        comboGenre.getItems().addAll("Homme", "Femme");
        comboRole.getItems().addAll("User", "Admin");
        LoadTable();
        getselected();
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

    private void getselected() {

        TableViewUsers.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 1) {
                String GetRole = TableViewUsers.getSelectionModel().getSelectedItem().getRoles();
                txtNomLogin1.setText(TableViewUsers.getSelectionModel().getSelectedItem().getNom());
                txtPrenomLogin1.setText(TableViewUsers.getSelectionModel().getSelectedItem().getPrenom());
                txtEmailLogin1.setText(String.valueOf(TableViewUsers.getSelectionModel().getSelectedItem().getEmail()));
                comboGenre.setValue(String.valueOf(TableViewUsers.getSelectionModel().getSelectedItem().getGenre()));
                if (GetRole.contains("ROLE_ADMIN") && !GetRole.contains("ROLE_USER")) {
                    comboRole.setValue("Admin");
                }
                if (GetRole.contains("ROLE_USER")) {
                    comboRole.setValue("User");
                }
                if (GetRole.contains("[]")) {
                    comboRole.setValue("User");
                }
            }
        });

    }

    public void LoadTable() {

        List<User> listee = new ArrayList<>();
        listee = work.AfficherAllUsers(rec);
        ObservableList<User> Liste = FXCollections.observableArrayList(listee);

        col_NomUser.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_PrenomUser.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_EmailUser.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_image.setCellValueFactory(new ImageCellValueFactory());
        col_SexeUser.setCellValueFactory(new SexeUserCellValueFactory());
        col_RoleUser.setCellValueFactory(new TypeUserCellValueFactory());
        ListUsers = FXCollections.observableArrayList(listee);
        TableViewUsers.setItems(ListUsers);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////// Search
        //  Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(ListUsers, b -> true);

        //  Set the filter Predicate whenever the filter changes.
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(data -> {
                // If filter text is empty, display all data.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare every table columns fields with lowercase filter text
                String lowerCaseFilter = newValue.toLowerCase();

                // Filter with all table columns
                if (data.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getPrenom()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getEmail()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getRoles()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getGenre()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match

                }
            });
        });

        //  Wrap the FilteredList in a SortedList.
        SortedList<User> sortedData = new SortedList<>(filteredData);
        //  Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TableViewUsers.comparatorProperty());
        //  Add sorted (and filtered) data to the table.
        TableViewUsers.setItems(sortedData);
        //////////////////////////////////////////////////////

    }

    @FXML
    private void SupprimerClicked(MouseEvent event) {
        if (TableViewUsers.getSelectionModel().getSelectedItem() != null) {//supprimer mel tableau
            rec = TableViewUsers.getSelectionModel().getSelectedItem();
            Boolean result = work.supprimerUser(rec.getId());
            if (result) {
                notif.notificationS("Delete", "User: " + rec.getNom() + " " + rec.getPrenom() + " " + "a été supprimé");
                LoadTable();
            }
        }
    }

    @FXML
    private void ModifierClicked(MouseEvent event) throws FileNotFoundException {
        if (TableViewUsers.getSelectionModel().getSelectedItem() != null) {
            int id = TableViewUsers.getSelectionModel().getSelectedItem().getId();
            String Email = txtNomLogin1.getText().trim();
            if (txtNomLogin1.getText().isEmpty()) {
                txtNomLogin1.requestFocus();
                shake(txtNomLogin1);
                return;
            }
            if (txtPrenomLogin1.getText().isEmpty()) {
                txtPrenomLogin1.requestFocus();
                shake(txtPrenomLogin1);
                return;
            }
            if (txtEmailLogin1.getText().isEmpty()) {
                txtEmailLogin1.requestFocus();
                shake(txtEmailLogin1);
                return;
            }
            if (isValidEmailAddress(txtEmailLogin1.getText()) == false) {
                txtEmailLogin1.requestFocus();
                shake(txtEmailLogin1);
                notif.notificationF("Opps ", "Email malformed !");
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
                notif.notificationF("Opps ", "Password Mut be more then 4 char");
                return;
            }

            if (comboGenre.getSelectionModel().getSelectedItem() == null) {
                comboGenre.requestFocus();
                shake(comboGenre);
                return;
            }
            if (comboRole.getSelectionModel().getSelectedItem() == null) {
                comboRole.requestFocus();
                shake(comboRole);
                return;
            }
            rec.setId(id);
            rec.setNom(txtNomLogin1.getText());
            rec.setPrenom(txtPrenomLogin1.getText());
            rec.setGenre(comboGenre.getSelectionModel().getSelectedItem());
            rec.setEmail(txtEmailLogin1.getText());
            rec.setPassword(txtPasswordLogin.getText());
            rec.setRoles(comboRole.getSelectionModel().getSelectedItem());
            System.out.println("combor " + comboRole.getSelectionModel().getSelectedItem());
            if (comboRole.getSelectionModel().getSelectedItem() == "Admin") {
                rec.setRoles("[\"ROLE_ADMIN\"]");
                System.out.println("ROLE_ADMIN");
            } else if (comboRole.getSelectionModel().getSelectedItem() == "User") {
                rec.setRoles("[]");
                System.out.println("ROLE_User");

            }
            rec.setImage(DefaultProfileImage.getImage(txtNomLogin1.getText()));

            boolean result = work.updateUser(rec); // Fonction updateUser
            if (result) {
                notif.notificationS("Cool ", "User updated With Sucees !");

                //mail.notificationS("Envoyer votre Email", "Email envoyé avec succée");
                /// nefarghou les champs be3ed majoutina
                txtNomLogin1.clear();
                txtPrenomLogin1.clear();
                comboGenre.getSelectionModel().clearSelection();
                comboRole.getSelectionModel().clearSelection();
                txtEmailLogin1.clear();
                txtPasswordLogin.clear();
                LoadTable();
                mail.sendMail("Your email :" + Email + "\n password : " + txtPasswordLogin.getText(), Email);

            } else {
                notif.notificationF("Opps ", "Something goes wrong !");
            }
        }
    }

    @FXML
    private void GotoBackAdmin(MouseEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("BackAdmin.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }

    @FXML
    private void GotoSponsor(MouseEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("SponsorBack.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);

    }

    @FXML
    private void ClearClicked(MouseEvent event) {
        txtNomLogin1.clear();
        txtPrenomLogin1.clear();
        comboGenre.getSelectionModel().clearSelection();
        comboRole.getSelectionModel().clearSelection();
        txtEmailLogin1.clear();
        txtPasswordLogin.clear();
    }

    @FXML
    private void GoToHomeBack(MouseEvent event) throws IOException {
//
//     Parent root = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
//        
//        
        
                        Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();

    }

    private class ImageCellValueFactory implements Callback<TableColumn.CellDataFeatures<User, ImageView>, ObservableValue<ImageView>> {

        @Override
        public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<User, ImageView> param) {
            User item = param.getValue();
            ImageView img = null;

            img = item.getImageView();

            return new SimpleObjectProperty<>(img);
        }
    }

    private class TypeUserCellValueFactory implements Callback<TableColumn.CellDataFeatures<User, JFXButton>, ObservableValue<JFXButton>> {

        @Override
        public ObservableValue<JFXButton> call(TableColumn.CellDataFeatures<User, JFXButton> param) {
            User item = param.getValue();

            JFXButton button = new JFXButton();
            button.setPrefWidth(col_RoleUser.getWidth() / 2);
            button.setText(item.getRoles());

            if (item.getRoles().contains("ROLE_ADMIN") && !item.getRoles().contains("ROLE_USER")) {
                button.getStyleClass().addAll("button-administrador", "table-row-cell");
                button.setText("Admin");

            } else if (item.getRoles().contains("ROLE_USER")) {
                button.getStyleClass().addAll("button-user", "table-row-cell");
                button.setText("User");
            } else if (item.getRoles().contains("[]")) {
                button.getStyleClass().addAll("button-user", "table-row-cell");
                button.setText("User");

            }
            return new SimpleObjectProperty<>(button);
        }
    }

    private class SexeUserCellValueFactory implements Callback<TableColumn.CellDataFeatures<User, ImageView>, ObservableValue<ImageView>> {

        @Override
        public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<User, ImageView> param) {
            User item = param.getValue();

            ImageView Verified;

            if (item.getGenre().equals("Homme")) {
                Verified = new ImageView(new Image("/ressources/man.png"));
            } else {
                Verified = new ImageView(new Image("/ressources/women.png"));
            }
            return new SimpleObjectProperty<>(Verified);
        }
    }

    @FXML
    private void LogoutClicked(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
        LoginController.idUserConnected = 0;
    }

    @FXML
    private void iconAddUserClicked(MouseEvent event) throws FileNotFoundException {

        String Email = txtNomLogin1.getText().trim();
        if (txtNomLogin1.getText().isEmpty()) {
            txtNomLogin1.requestFocus();
            shake(txtNomLogin1);
            return;
        }
        if (txtPrenomLogin1.getText().isEmpty()) {
            txtPrenomLogin1.requestFocus();
            shake(txtPrenomLogin1);
            return;
        }
        if (txtEmailLogin1.getText().isEmpty()) {
            txtEmailLogin1.requestFocus();
            shake(txtEmailLogin1);
            return;
        }
        if (isValidEmailAddress(txtEmailLogin1.getText()) == false) {
            txtEmailLogin1.requestFocus();
            shake(txtEmailLogin1);
            notif.notificationF("Opps ", "Email malformed !");
            return;

        }
        if (work.checkIfUserExists(Email) != 0) {
            notif.notificationF("Opps ", "Email ALREADY EXISTS !");
            txtEmailLogin1.requestFocus();
            shake(txtEmailLogin1);
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
            notif.notificationF("Opps ", "Password Mut be more then 4 char");
            return;
        }

        if (comboGenre.getSelectionModel().getSelectedItem() == null) {
            comboGenre.requestFocus();
            shake(comboGenre);
            return;
        }
        if (comboRole.getSelectionModel().getSelectedItem() == null) {
            comboRole.requestFocus();
            shake(comboRole);
            return;
        }

        rec.setNom(txtNomLogin1.getText());
        rec.setPrenom(txtPrenomLogin1.getText());
        rec.setGenre(comboGenre.getSelectionModel().getSelectedItem());
        rec.setEmail(txtEmailLogin1.getText());
        rec.setPassword(txtPasswordLogin.getText());
        rec.setRoles(comboRole.getSelectionModel().getSelectedItem());
        if (comboRole.getSelectionModel().getSelectedItem() == "Admin") {
            rec.setRoles("[\"ROLE_ADMIN\"]");
        } else if (comboRole.getSelectionModel().getSelectedItem() == "User") {
            rec.setRoles("[]");
        }
        rec.setImage(DefaultProfileImage.getImage(txtNomLogin1.getText()));

        boolean result = work.Inscription(rec); // Fonction AjoutUser
        if (result) {
            notif.notificationS("Cool ", "User Added With Sucees !");

            //mail.notificationS("Envoyer votre Email", "Email envoyé avec succée");
            /// nefarghou les champs be3ed majoutina
            txtNomLogin1.clear();
            txtPrenomLogin1.clear();
            comboGenre.getSelectionModel().clearSelection();
            comboRole.getSelectionModel().clearSelection();
            txtEmailLogin1.clear();
            txtPasswordLogin.clear();
            LoadTable();
            mail.sendMail("Now you can access to our application your email :" + Email + "\n password : " + txtPasswordLogin.getText(), Email);

        } else {
            notif.notificationF("Opps ", "Something goes wrong !");
        }
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

    @FXML
    private void close_app(ActionEvent event) {
        System.exit(0);
    }

    private void showPassword() {
        txtShowPassword1.managedProperty().bind(icon1.pressedProperty());
        txtShowPassword1.visibleProperty().bind(icon1.pressedProperty());
        txtShowPassword1.textProperty().bindBidirectional(txtPasswordLogin.textProperty());

        txtPasswordLogin.managedProperty().bind(icon1.pressedProperty().not());
        txtPasswordLogin.visibleProperty().bind(icon1.pressedProperty().not());

        icon1.pressedProperty().addListener((o, oldVal, newVal) -> {
            if (newVal) {
                icon1.setIcon(FontAwesomeIcon.EYE);
            } else {
                icon1.setIcon(FontAwesomeIcon.EYE_SLASH);
            }
        });
    }
}
