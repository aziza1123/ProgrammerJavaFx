/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import edu.aziza.entities.Sponsor;
import edu.aziza.services.CrudSponsor;
import edu.aziza.utils.DataSource;
import edu.aziza.utils.Notification;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.io.FileUtils;


/**
 * FXML Controller class
 *
 * @author yassin
 */
public class SponsorBackController implements Initializable {

    @FXML
    private StackPane stck;
    @FXML
    private AnchorPane rootUsers;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private TabPane PaneTableau;
    @FXML
    private TableColumn<Sponsor, String> col_NomUser;
    @FXML
    private TableColumn<Sponsor, ImageView> col_image;
    @FXML
    private TableColumn<Sponsor, Date> col_Date;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField txtSearch;
    @FXML
    private JFXTextField txtNomLogin1;
    @FXML
    private ImageView iconNom1;
    @FXML
    private JFXDatePicker txtDate;
    @FXML
    private ImageView iconDateNaissance;
    @FXML
    private ImageView PreviewImage;
    @FXML
    private ImageView imageViewProfile;
    @FXML
    private Text textName;
    @FXML
    private Text textUserType;
    @FXML
    private TableView<Sponsor> TableView;
    private ObservableList<Sponsor> ListUsers;
    ////////////////////////////
    Sponsor rec = new Sponsor();
    CrudSponsor work = new CrudSponsor();
    Notification notif = new Notification();
    File selectedFile;
    private FileChooser Fc = new FileChooser();
    private File file;
    private static String pathImage = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTable();
        loadData(LoginController.idUserConnected);
        getselected();
    }

    private void getselected() {

        TableView.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 1) {

                txtNomLogin1.setText(TableView.getSelectionModel().getSelectedItem().getNom());
                txtDate.setValue(TableView.getSelectionModel().getSelectedItem().getDateFin().toLocalDate());
                int id = 0;
                if (TableView.getSelectionModel().getSelectedItem() != null) {
                    id = Integer.valueOf((TableView.getSelectionModel().getSelectedItem().getId()));
                }
                ////////////
                try {
                    String Destination = "C:\\wamp64\\www\\Salma\\";
                    File dest = new File("C:\\wamp64\\www\\Salma\\");

                    String requeteee = "SELECT image_sponsor FROM sponsor WHERE id_sponsor = '" + id + "'";
                    Statement psttt = DataSource.getInstance().getCnx().createStatement();
                    ResultSet rsss = psttt.executeQuery(requeteee);
                    while (rsss.next()) {
                        String exist = "";
                        exist = rsss.getString(1);
                        if (exist != null && !exist.isEmpty()) {
                            String ImageProduct = Destination + rsss.getString(1);
                            String NomImage = rsss.getString(1);
                            pathImage = rsss.getString(1);
                            File f = new File(dest, NomImage);
                            if (f.exists()) {
                                System.out.println("File  Exist  in Uploads");
                                Image imagee = new Image(new FileInputStream(ImageProduct), 200, 200, true, true);
                                PreviewImage.setImage(imagee);
                            } else {
                                System.out.println("File Not Exist  in Uploads");
                                File file = new File(Destination + "uploadimageicon.png");
                                Image imagee = new Image(file.toURI().toString());
                                PreviewImage.setImage(imagee);
                            }

                        } else if (exist == null || exist.isEmpty()) {
                            System.out.println("Base de donnée champ image null or empty !");
                            File file = new File(Destination + "uploadimageicon.png");
                            Image imagee = new Image(file.toURI().toString());
                            PreviewImage.setImage(imagee);
                        }

                    }
                } catch (SQLException ex) {
                    ex.getStackTrace();
                } catch (FileNotFoundException ex) {
                    //Logger.getLogger(BackProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ///////////               
            }
        });

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

    public void LoadTable() {

        List<Sponsor> listee = new ArrayList<>();
        listee = work.Afficher(rec);
        ObservableList<Sponsor> Liste = FXCollections.observableArrayList(listee);

        col_NomUser.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        col_image.setCellValueFactory(new ImageCellValueFactory());
        ListUsers = FXCollections.observableArrayList(listee);
        TableView.setItems(ListUsers);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////// Search
        //  Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Sponsor> filteredData = new FilteredList<>(ListUsers, b -> true);

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
                } else if (String.valueOf(data.getDateFin()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match

                }
            });
        });

        //  Wrap the FilteredList in a SortedList.
        SortedList<Sponsor> sortedData = new SortedList<>(filteredData);
        //  Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TableView.comparatorProperty());
        //  Add sorted (and filtered) data to the table.
        TableView.setItems(sortedData);
        //////////////////////////////////////////////////////

    }

    @FXML
    private void GoToHomeBack(MouseEvent event) throws IOException {
        
                     Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private class ImageCellValueFactory implements Callback<TableColumn.CellDataFeatures<Sponsor, ImageView>, ObservableValue<ImageView>> {

        @Override
        public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Sponsor, ImageView> param) {
            Sponsor item = param.getValue();
            ImageView img = null;

            img = item.getImageview();

            return new SimpleObjectProperty<>(img);
        }
    }

    @FXML
    private void iconAddUserClicked(MouseEvent event) {
        if (txtNomLogin1.getText().isEmpty()) {
            txtNomLogin1.requestFocus();
            shake(txtNomLogin1);
            return;
        }
        if (txtDate.getEditor().getText().isEmpty()) {
            txtDate.requestFocus();
            shake(txtDate);
            return;
        }
        if (pathImage.isEmpty()) {
            PreviewImage.requestFocus();
            shake(PreviewImage);
            return;
        }
        rec.setNom(txtNomLogin1.getText());
        rec.setDateFin(java.sql.Date.valueOf(txtDate.getValue()));
        rec.setImage(pathImage);
        //  ComboFournisseur.getSelectionModel().getSelectedItem();
        // rec.setVote();

        boolean result = work.Ajouter(rec); // Fonction AjoutUser
        if (result) {
            LoadTable();
            txtNomLogin1.clear();
            txtDate.setValue(null);

            pathImage = "";

        }
    }

    public static void shake(Node node) {
        new Shake(node).play();
    }

    @FXML
    private void SupprimerClicked(MouseEvent event) {
        if (TableView.getSelectionModel().getSelectedItem() != null) {//supprimer mel tableau
            rec = TableView.getSelectionModel().getSelectedItem();
            Boolean result = work.Supprimer(rec.getId());
            if (result) {
                notif.notificationS("Delete", "User: " + rec.getNom() + " " + rec.getNom() + " " + "a été supprimé");
                LoadTable();
            }
        }
    }

    @FXML
    private void ModifierClicked(MouseEvent event) {

        if (TableView.getSelectionModel().getSelectedItem() != null) {
            int id = TableView.getSelectionModel().getSelectedItem().getId();
            if (txtNomLogin1.getText().isEmpty()) {
                txtNomLogin1.requestFocus();
                shake(txtNomLogin1);
                return;
            }
            if (txtDate.getEditor().getText().isEmpty()) {
                txtDate.requestFocus();
                shake(txtDate);
                return;
            }
            if (pathImage.isEmpty()) {
                PreviewImage.requestFocus();
                shake(PreviewImage);
                return;
            }
            rec.setId(id);
            rec.setNom(txtNomLogin1.getText());
            rec.setDateFin(java.sql.Date.valueOf(txtDate.getValue()));
            rec.setImage(pathImage);
            //  ComboFournisseur.getSelectionModel().getSelectedItem();
            // rec.setVote();

            boolean result = work.Modifier(rec); // Fonction AjoutUser
            if (result) {
                LoadTable();
                txtNomLogin1.clear();
                txtDate.setValue(null);

                pathImage = "";
                File file = new File("C:\\wamp64\\www\\Salma\\" + "uploadimageicon.png");
                Image imagee = new Image(file.toURI().toString());
                PreviewImage.setImage(imagee);

            }
        }
    }

    @FXML
    private void UploadImageClicked(MouseEvent event) {
        File dest = new File("C:\\wamp64\\www\\Salma");
        Fc.setTitle("Open Resource File");
        Fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        selectedFile = Fc.showOpenDialog(null);

        if (selectedFile != null) {
            try {

                String Destination = "C:\\wamp64\\www\\Salma";
                File f = new File(dest, selectedFile.getName());

                FileUtils.copyFileToDirectory(selectedFile, dest);
                pathImage = selectedFile.getName();

                Image image = new Image(new FileInputStream(selectedFile), 200, 200, true, true);
                PreviewImage.setImage(image);
            } catch (IOException ex) {
                ex.getStackTrace();
            }
        }
    }

    private Stage getStage() {
        return (Stage) PreviewImage.getScene().getWindow();
    }

    private File getFileSelected() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        fileChooser.setTitle("Select an image");

        File selectedImage = fileChooser.showOpenDialog(getStage());
        return selectedImage;
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

}
