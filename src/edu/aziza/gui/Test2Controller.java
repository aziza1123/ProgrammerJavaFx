/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import edu.aziza.entities.Cadeau;
import edu.aziza.entities.Roulette;
import edu.aziza.services.CadeauService;
import edu.aziza.services.RouletteService;
import edu.aziza.utils.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class Test2Controller implements Initializable {
   Connection cnx = DataSource.getInstance().getCnx();

    @FXML
    private Button btnmodif;
    @FXML
    private Button supprimerbtn;
    @FXML
    private Button ajouterbtn;
    @FXML
    private TableView<Cadeau> tabcadeau;
    @FXML
    private TableColumn<Cadeau, String> cname;
    @FXML
    private TableColumn<Cadeau, String> ctype;
    @FXML
    private TableColumn<Cadeau, String> cdescription;
    @FXML
    private TableColumn<Cadeau, String> cimage;
    ObservableList<Cadeau> ob1 = FXCollections.observableArrayList();
    ObservableList<Cadeau> oblist1 = FXCollections.observableArrayList();
    @FXML
    private TextField tfname;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfdescription;
    @FXML
    private ComboBox<String> checkbox;
    @FXML
    private TextField tfimage;
   
    @FXML
    private ComboBox<String> cbtriObjPred;
    
    @FXML
    private AnchorPane AnchorPaneCadeau;
    @FXML
    private AnchorPane AnchorPaneCadeau1;
    @FXML
    private TableView<?> ListPubs2;
    @FXML
    private TableColumn<?, ?> libelle_Pubs;
    @FXML
    private TableColumn<?, ?> Nbr_Reaction;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      CadeauService Ar=new CadeauService();
        RouletteService rs =new RouletteService();
//          ObservableList<String> Cadeau =(ObservableList<Cadeau>) Ar.recuperer();

            List<Cadeau> Cadeau = Ar.recuperer();
      
            ObservableList<String> listTriObjPred = FXCollections.observableArrayList("Par Name", "Par Type", "Par Description");
        cbtriObjPred.setItems(listTriObjPred);
        getRoulette();
//        checkbox.setItems(;
        afficher();
    }
     @FXML
    private void AjouterCadeau(ActionEvent event) {
     Boolean ok=false;
         Roulette r=new Roulette();
         RouletteService rs=new RouletteService();
     if ((tfname.getText().toString().compareTo("") == 0) || (tftype.getText().toString().compareTo("") == 0 ) ||  (tfdescription.getText().toString().compareTo("") == 0 )|| (tfimage.getText().toString().compareTo("") == 0 )) {
         ok=true;
            JOptionPane.showMessageDialog(null, "champs vide");

} else {
         
        String name =tfname.getText();
        String type=tftype.getText();
        String description= tfdescription.getText();
        String image=tfimage.getText();
        int id=rs.load_data(checkbox.getValue()).getId();
         //C.setRoulette_id(rs.load_data (checkbox.getValue()).getId());
        
        Cadeau c = new Cadeau(name,type,description,id,image);
        CadeauService sc = new CadeauService();
        sc.ajouter(c);
        afficher();
     }
    }


    @FXML
    private void editc(ActionEvent event) {
      String name = tfname.getText();
        String type = tftype.getText();
        String description = tfdescription.getText();
        String image= tfimage.getText();
        Cadeau cc = new Cadeau();
        cc.setName(tfname.getText());
        cc.setType(tftype.getText());
        cc.setDescription(tfdescription.getText());
        cc.setImage(tfimage.getText());

        Cadeau co = tabcadeau.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            sc.modifier(co.getId(),cc);
            afficher();
            clearfields();
            // sound.playClick();
            // np.notifpush("rendezvous Modifier", "Conge modifier avec succes");
        }
}


    @FXML
    private void SupprimerCadeau(ActionEvent event) {
       ObservableList<Cadeau> selectedRows, allPeople;
        allPeople = tabcadeau.getItems();
        selectedRows = tabcadeau.getSelectionModel().getSelectedItems();
       Cadeau co = tabcadeau.getSelectionModel().getSelectedItem();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Comfirmation");
          alert.setHeaderText(null);
          alert.setContentText("Êtes-vous sûr de supprimer ?");
          Optional<ButtonType> action = alert.showAndWait();
          if (action.get() == ButtonType.OK) {
         sc.supprimer(co.getId());
        tabcadeau.getItems().clear();
            afficher(); 
            clearfields();
    }

    }
       CadeauService sc = new CadeauService();

   
            public Cadeau com;
    @FXML
    private void selectcom(javafx.scene.input.MouseEvent event) {
     com = tabcadeau.getSelectionModel().getSelectedItem();
        if (com != null) {

            //iduu.setText(String.valueOf(cc.getIde_user()));
           
            tfname.setText(String.valueOf(com.getName()));
            tftype.setText(String.valueOf(com.getType()));
            tfdescription.setText(String.valueOf(com.getDescription()));
            tfimage.setText(String.valueOf(com.getImage()));


          

            //java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dattee.getValue());
            //nom_u.setText(tab.getNom());
        }

    
    }

    @FXML
    private void afficher() {
     
        CadeauService sc=new CadeauService();

            ObservableList obeListe = FXCollections.observableList(sc.recuperer());
          cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        ctype.setCellValueFactory(new PropertyValueFactory<>("type"));
        cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        cimage.setPrefWidth(40);
        cimage.setCellValueFactory(new PropertyValueFactory<>("image"));
      

      tabcadeau.setItems(ob1);
        
        tabcadeau.setItems(obeListe);
        
   
    }
      private void clearfields() {  
        tfname.clear();
        tftype.clear();
        tfdescription.clear();
        tfimage.clear();

    }

    @FXML
    private void Upload(ActionEvent event) {
     JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String ImageName =f.getAbsolutePath();
        tfimage.setText(ImageName);
        Image photo = new Image(ImageName);
       
        
    }


    @FXML
    private void selectTriObjPred(ActionEvent event) {
      CadeauService Sar = new CadeauService();
        ObservableList<Cadeau> Ar = FXCollections.observableArrayList();
        if (cbtriObjPred.getValue().equals("Par Name")) {
           Ar = Sar.trierCadeauName();
            } else if (cbtriObjPred.getValue().equals("Par Type")) {
          Ar = Sar.trierCadeauType();
       
        } else if (cbtriObjPred.getValue().equals("Par Description")) {
           Ar = Sar.trierCadeauDesc();
       
        tabcadeau.setItems(Ar);
    }
    }
     public void getRoulette() {
    
        
        ObservableList<String> Roulette = FXCollections.observableArrayList();
        try {
    
            Statement st = cnx.createStatement();
            String query = "SELECT name  FROM roulette";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) { 
                Roulette.add(rs.getString("name"));
            }
            checkbox.setItems(Roulette);

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
    }
     
     

    
     private void setInterface(String location) throws IOException {
        AnchorPaneCadeau1.getChildren().clear();
        AnchorPaneCadeau1.getChildren().add(FXMLLoader.load(this.getClass().
                getResource(location + ".fxml")));
    }

   

    @FXML
    private void GoToHome(MouseEvent event) {
    Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

  
}
