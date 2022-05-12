/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import Alert.AlertDialog;
import edu.aziza.entities.Commentaire;
import edu.aziza.entities.Publication;
import edu.aziza.services.ServiceCommentaire;
import edu.aziza.services.ServicePublication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class NaderpubController implements Initializable {

     ObservableList<Publication> Publication=FXCollections.observableArrayList();
    ObservableList<Commentaire> Commentaire=FXCollections.observableArrayList();
          ObservableList<Publication> list2= FXCollections.observableArrayList();

  
    private TextField barre;
    @FXML
    private TableView<Publication> id_tab;
    @FXML
    private TableColumn<Publication, String> col_Media;
    @FXML
    private TableColumn<Publication, String> col_Titre;
    @FXML
    private TableColumn<Publication, String> col_Description;
    
    

    @FXML
    private TableView<Commentaire> id_tab1;
    @FXML
    private TableColumn<Commentaire, Integer> col_Num;
    @FXML
    private TableColumn<Commentaire, String> col_Contenu;
    @FXML
    private TextField id_titre;
    @FXML
    private TextField id_media;
    @FXML
    private TextField id_description;
    @FXML
    private Button id_mod;
    @FXML
    private Button id_supprimer;
    @FXML
    private Button Ajout;
    @FXML
    private TableView<?> ListPubs2;
    @FXML
    private TableColumn<?, ?> libelle_Pubs;
    @FXML
    private TableColumn<?, ?> Nbr_Reaction;
    @FXML
    private TextField barre1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // TODO
        try {
            affichertab();
            
            affichertab1();
            //afficherID();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(NaderpubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    @FXML
    private void supprimer(ActionEvent event) throws SQLException{
      ServicePublication sr = new ServicePublication();
        Publication p = new Publication();
        sr.Supprimer(p,id_tab.getSelectionModel().getSelectedItem().getId());
        AlertDialog.showNotification("supprimer","avec succee", AlertDialog.image_checked);
        affichertab();
    }

    @FXML
    private void fill(MouseEvent event) {
    Publication p= id_tab.getSelectionModel().getSelectedItem();
        id_media.setText(p.getMedia());
        id_titre.setText(p.getTitre());
        id_description.setText(p.getDescription());
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException{
       Publication p = new Publication();
         ServicePublication sr = new ServicePublication();
                p.setMedia(id_media.getText());
               
                p.setTitre(id_titre.getText());
                p.setDescription(id_description.getText());
                sr.Modifier(p,id_tab.getSelectionModel().getSelectedItem().getId());
                AlertDialog.showNotification("modifier","avec succee", AlertDialog.image_checked);
                affichertab();
    }
    
    
    void recherche() {
        ServicePublication SR=new ServicePublication();
       Publication r = new Publication();
        col_Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        

        ObservableList<Publication> dataList;

        dataList = (ObservableList<Publication>) SR.Affichertout();
       
        id_tab.setItems(dataList);
       
        FilteredList<Publication> filteredData = new FilteredList<>(dataList, b -> true);
       
        barre1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Publication ar) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (ar.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               
                } else if (String.valueOf(ar.getDescription()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
                
              
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Publication> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(id_tab.comparatorProperty());
        id_tab.setItems(sortedData);
    }
    
    

    @FXML
    private void goajout(ActionEvent event) {
    
             try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("AjoutPublication.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(FirstWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public void affichertab() throws SQLException{
       ServicePublication sr = new ServicePublication();
        
        ObservableList<Publication> list= FXCollections.observableArrayList(sr.Affichertout());
        
       
        col_Media.setCellValueFactory(new PropertyValueFactory<Publication, String>("media"));
        col_Titre.setCellValueFactory(new PropertyValueFactory<Publication, String>("titre"));
        col_Description.setCellValueFactory(new PropertyValueFactory<Publication, String>("description"));
        id_tab.setItems(list);
        id_tab.setItems(list2);
        recherche();
    }
      
         public void affichertab1() throws SQLException{
       ServiceCommentaire sr = new ServiceCommentaire();
        //Evenement=FXCollections.observableArrayList(sr.Affichertout());
        ObservableList<Commentaire> list= FXCollections.observableArrayList(sr.Affichertout());
        
       
        col_Contenu.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("contenu"));
        col_Num.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("num"));
//        col_Date.setCellValueFactory(new PropertyValueFactory<Commentaire, Date>("date"));
        id_tab1.setItems(list);
    }

    @FXML
    private void GoToHome(MouseEvent event) throws IOException {
   Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}

    

