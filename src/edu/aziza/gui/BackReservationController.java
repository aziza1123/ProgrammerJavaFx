/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;


import edu.aziza.entities.Reservation;
import edu.aziza.services.ServiceReservation;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author fatma
 */
public class BackReservationController implements Initializable {
    ObservableList<Reservation> ob1 = FXCollections.observableArrayList();
    ObservableList<Reservation> oblist1 = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Reservation, String> cnomrb;
    @FXML
    private TableColumn<Reservation, String> cprenomrb;
    @FXML
    private TableColumn<Reservation, String> cnumrb;
    @FXML
    private TableColumn<Reservation, String> cmailrb;
    @FXML
    private TextField tfrechercherb;
    @FXML
    private TableView<Reservation> tablereservationb;
    @FXML
    private AnchorPane anchorpaneres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        afficherr();
    } 
    
    private void afficherr() {

        ServiceReservation sa =new  ServiceReservation();

        ObservableList obeListe = FXCollections.observableList(sa.getAll());

        //idc.setCellValueFactory(new PropertyValueFactory<>("id_conge"));
        cnomrb.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cprenomrb.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cnumrb.setCellValueFactory(new PropertyValueFactory<>("num"));
                cmailrb.setCellValueFactory(new PropertyValueFactory<>("mail"));

        tablereservationb.setItems(ob1);

        tablereservationb.setItems(obeListe);

                   
FilteredList<Reservation> filteredData = new FilteredList<>(obeListe, e -> true);
        tfrechercherb.setOnKeyReleased(e ->{
            tfrechercherb.textProperty().addListener((observableValue, oldValue, newValue) ->{
                filteredData.setPredicate((Predicate<? super Reservation>) r->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(r.getNom().contains(newValue)){
                        return true;
                    }else if(r.getPrenom().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(r.getMail().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Reservation> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tablereservationb.comparatorProperty());
            tablereservationb.setItems(sortedData);
           
        });
    }
    
}
