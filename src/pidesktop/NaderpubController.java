/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidesktop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class NaderpubController implements Initializable {

    @FXML
    private TableView<?> ListPubs2;
    @FXML
    private TableColumn<?, ?> libelle_Pubs;
    @FXML
    private TableColumn<?, ?> Nbr_Reaction;
    @FXML
    private TableView<?> id_tab1;
    @FXML
    private TableColumn<?, ?> col_Num;
    @FXML
    private TableColumn<?, ?> col_Contenu;
    @FXML
    private TableView<?> id_tab;
    @FXML
    private TableColumn<?, ?> col_Media;
    @FXML
    private TableColumn<?, ?> col_Titre;
    @FXML
    private TableColumn<?, ?> col_Description;
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
    private TextField barre1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fill(MouseEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void goajout(ActionEvent event) {
    }
    
}
