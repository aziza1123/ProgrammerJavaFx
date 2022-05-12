/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import Alert.AlertDialog;
import edu.aziza.entities.Categorie;
import edu.aziza.services.CategorieService;
import edu.aziza.services.ProduitService;
import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AjoutCategorieController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private TextField tnom;
    @FXML
    private TextField tdescription;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupp;
      ObservableList<Categorie> ob1 = FXCollections.observableArrayList();
    ObservableList<Categorie> oblist1 = FXCollections.observableArrayList();
    @FXML
    private TableView<?> tabcategorie;
    @FXML
    private TableColumn<?, ?> pnom;
    @FXML
    private TableColumn<?, ?> pdescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     Afficher();
            CategorieService  cs = new CategorieService();
        
        
         List<Categorie> Categorie = cs.recuperer();
         
 
    }    
            
  
   
    private void clearfields() {  
        tnom.clear();
        tdescription.clear();
       
    }
     

    
    public Categorie com;
CategorieService  cs = new CategorieService();
    private void selectcom(MouseEvent event) {
     //    com = tabcategorie.getSelectionModel().getSelectedItem();
        if (com != null) {

            //iduu.setText(String.valueOf(cc.getIde_user()));
           
            tnom.setText(String.valueOf(com.getNom()));
        
            tdescription.setText(String.valueOf(com.getDescription()));
           


          

            //java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dattee.getValue());
            //nom_u.setText(tab.getNom());
        }

    }
   



    private void Afficher() {
         ProduitService ps=new ProduitService();

            ObservableList obeListe = FXCollections.observableList(ps.recuperer());
          pnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        
      

    //  tabcategorie.setItems(ob1);
        
        tabcategorie.setItems(obeListe);
        
    }
    

    private void Modifier(ActionEvent event) {
        
        String nom = tnom.getText();
        String description = tdescription.getText();
     
        
        Categorie pr = new Categorie();
        
        pr.setNom(tnom.getText());
        pr.setDescription(tdescription.getText());
       

       // Categorie po = tabcategorie.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            cs.modifier(pr.getId(),pr);
            Afficher();
            clearfields();
            
    }

    }

    private void supprimer(ActionEvent event) {
         CategorieService cs=new CategorieService();
         ObservableList<Categorie> selectedRows, allPeople;
          Categorie p = new Categorie();
        // allPeople = tabcategorie.getItems();
        // selectedRows = tabcategorie.getSelectionModel().getSelectedItems();
         
      // Categorie  = tabcategorie.getSelectionModel().getSelectedItem();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation");
          alert.setHeaderText(null);
          alert.setContentText("Êtes-vous sûr de supprimer ?");
          Optional<ButtonType> action = alert.showAndWait();
          if (action.get() == ButtonType.OK) {
             
              
         cs.supprimer(p.getId());
        tabcategorie.getItems().clear();
            clearfields();
                        Afficher(); 

    
    }
}
ProduitService  ps = new ProduitService();

    private void ajouter(ActionEvent event) {

            String nom = tnom.getText();
            String description  = tdescription.getText();
           
            
            
            Categorie p = new Categorie (nom,description);
            CategorieService  cs = new CategorieService();
               
            cs.ajouter(p);
             AlertDialog.showNotification("ajout","avec succee", AlertDialog.image_checked);
             Afficher();
            
           
        
    
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
   
}