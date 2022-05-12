/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import javafx.scene.control.TextField;
import edu.aziza.entities.Activite;
import edu.aziza.services.ServiceActivite;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author fatma
 */
public class FrontActiviteController implements Initializable {
    
    ObservableList<Activite> ob1 = FXCollections.observableArrayList();
    ObservableList<Activite> oblist1 = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Activite, String> cnomaf;
    @FXML
    private TableColumn<Activite, Float> cprixaf;
    @FXML
    private TableColumn<Activite, String> cdescaf;
    @FXML
    private TextField tfrechercheaf;
    @FXML
    private TableView<Activite> tableactiviteaf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherr();
    }   
     private void afficherr() {

        ServiceActivite sa =new  ServiceActivite();

        ObservableList obeListe = FXCollections.observableList(sa.getAll());

        //idc.setCellValueFactory(new PropertyValueFactory<>("id_conge"));
        cnomaf.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        cprixaf.setCellValueFactory(new PropertyValueFactory<>("prix"));
        cdescaf.setCellValueFactory(new PropertyValueFactory<>("description"));
                

//        tableactivite.setItems(ob1);
        
        tableactiviteaf.setItems(ob1);
        tableactiviteaf.setItems(obeListe);

                   
FilteredList<Activite> filteredData = new FilteredList<>(obeListe, e -> true);
        tfrechercheaf.setOnKeyReleased(e ->{
            tfrechercheaf.textProperty().addListener((observableValue, oldValue, newValue) ->{
                filteredData.setPredicate((Predicate<? super Activite>) r->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(r.getNom().contains(newValue)){
                        return true;
                    }else if(r.getDescription().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
//                    else if(r.getPrix().toLowerCase().contains(lowerCaseFilter)){
//                        return true;
//                    }
                    return false;
                });
            });
            
           
        });
        SortedList<Activite> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableactiviteaf.comparatorProperty());
            tableactiviteaf.setItems(sortedData);
    }
    
}
