/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import com.itextpdf.text.DocumentException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import edu.aziza.entities.Roulette;
import edu.aziza.entities.Cadeau;

import edu.aziza.services.PDF;
import edu.aziza.services.RouletteService;
import edu.aziza.utils.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class TestController implements Initializable {
        Connection cnx = DataSource.getInstance().getCnx();

   
  
    @FXML
    private TextField barre;
    @FXML
    private TableView<Roulette> tabroulette;
    @FXML
    private TableColumn<Roulette, String> cname;
    @FXML
    private TableColumn<Roulette, String> cdescription;
    @FXML
    private TableColumn<Roulette, String> cdebut;
    @FXML
    private TableColumn<Roulette, String> cfin;
    @FXML
    private Button modifbtn;
    @FXML
    private Button supprbtn;
    @FXML
    private TextField tfname;
         ObservableList<Roulette> oblist = FXCollections.observableArrayList();

    @FXML
    private TextField tfdescription;
    @FXML
    private Button ajoutbtn;
         ObservableList<Roulette> ob1 = FXCollections.observableArrayList();

    @FXML
    private DatePicker tfdebut;
    @FXML
    private DatePicker tffin;
    @FXML
    private Button pdf;
    @FXML
    private TableView<?> ListPubs2;
    @FXML
    private TableColumn<?, ?> libelle_Pubs;
    @FXML
    private TableColumn<?, ?> Nbr_Reaction;
    @FXML
    private Button Button_Delete_Search;
    @FXML
    private Button Button_Search;
    @FXML
    private AnchorPane AnchorPane;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Afficher();
    }    

   
public Roulette rom;
    @FXML
    private void selectcom(MouseEvent event) {
        rom = tabroulette.getSelectionModel().getSelectedItem();
        if (rom != null) {

            //iduu.setText(String.valueOf(cc.getIde_user()));
           
            tfname.setText(String.valueOf(rom.getName()));
            tfdescription.setText(String.valueOf(rom.getDescription()));
            
            //tfdebut.setText(String.valueOf(rom.getDebut()));
           // tffin.setText(String.valueOf(rom.getFin()));

          
        }
    }

    void recherche(){
        RouletteService SR=new RouletteService();
       Roulette r = new Roulette();
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        

        ObservableList<Roulette> dataList;

        dataList = (ObservableList<Roulette>) SR.recuperer();
       
        tabroulette.setItems(dataList);
       
        FilteredList<Roulette> filteredData = new FilteredList<>(dataList, b -> true);
       
        barre.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Roulette ar) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (ar.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               
                } else if (String.valueOf(ar.getDescription()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
                
              
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Roulette> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabroulette.comparatorProperty());
        tabroulette.setItems(sortedData);
    }
    
    
    

  
    @FXML
    private void ExportPDF(ActionEvent event) throws FileNotFoundException, DocumentException {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos Articles ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             PDF sp= new PDF();
        sp.liste_ArticlePDF();
        }
    }


    @FXML
    private void DeleteTextFromTextField(ActionEvent event) {
    }

    @FXML
    private void FindByLibelle(ActionEvent event) {
    }

    @FXML
    private void Afficher() {
     RouletteService rs=new RouletteService();

            ObservableList obeListe = FXCollections.observableList(rs.recuperer());
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        cdebut.setCellValueFactory(new PropertyValueFactory<>("debut"));
        cfin.setCellValueFactory(new PropertyValueFactory<>("fin"));
      

        tabroulette.setItems(ob1);
        
        tabroulette.setItems(obeListe);
        recherche();
        
   
    }
        RouletteService rs=new RouletteService();
    @FXML
    private void Modifier(ActionEvent event) {
    String name = tfname.getText();
        String description = tfdescription.getText();
        String debut = (tfdebut.getValue() != null ? tfdebut.getValue().toString() : "");
        String fin = (tffin.getValue() != null ? tffin.getValue().toString() : "");
        
        Roulette rr = new Roulette();
        rr.setName(tfname.getText());
        rr.setDescription(tfdescription.getText());
        rr.setDebut(debut);
        rr.setFin(fin);

        Roulette ro = tabroulette.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            rs.modifier(ro.getId(),rr);
            Afficher();
            clearfields();
            
    }
} 

    @FXML
    private void supprimer(ActionEvent event) {
     ObservableList<Roulette> selectedRows, allPeople;
        allPeople = tabroulette.getItems();
        selectedRows = tabroulette.getSelectionModel().getSelectedItems();
       Roulette ro = tabroulette.getSelectionModel().getSelectedItem();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirnation");
          alert.setHeaderText(null);
          alert.setContentText("Êtes-vous sûr de supprimer ?");
          Optional<ButtonType> action = alert.showAndWait();
          if (action.get() == ButtonType.OK) {
         rs.supprimer(ro.getId());
        tabroulette.getItems().clear();
            Afficher(); 
            clearfields();
    }
    }  

    @FXML
    private void ajouter(ActionEvent event) {
      Boolean ok=false;
     if ((tfname.getText().toString().compareTo("") == 0)  ||  (tfdescription.getText().toString().compareTo("") == 0 )) {
         ok=true;
            JOptionPane.showMessageDialog(null, "champs vide");

} else {
        String name =tfname.getText();
        String description= tfdescription.getText();
       
        String debut = (tfdebut.getValue() != null ? tfdebut.getValue().toString() : "");
        String fin = (tffin.getValue() != null ? tffin.getValue().toString() : "");
        Roulette r = new Roulette(name,description,debut,fin);
        RouletteService rs = new RouletteService();
        rs.ajouter(r);
        Afficher();
        Email from = new Email("CulturaApplication@gmail.com");
        String subject = "Mail de Nouveautés ";
        Email to = new Email("aziza.chouchane@esprit.tn");
        Content content = new Content("text/plain", "Checkez notre application une nouvelle roulette est arrivée allez , venez tenter votre chance !! " );
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("message non envoyé");
        }
    }

     }
    private void clearfields() {  
        tfname.clear();
        tfdescription.clear();
        //tfdebut.clear();
        //tffin.clear();

    }

    
    private void setInterface(String location) throws IOException {
        AnchorPane.getChildren().clear();
        AnchorPane.getChildren().add(FXMLLoader.load(this.getClass().
                getResource(location + ".fxml")));
    }
 

   
    
    
    public List<Roulette>Dsiplay(){
        List<Roulette> list = new ArrayList<>();
        try {
            String req = "Select * from roulette";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Roulette r = new Roulette();
                r.setId(rs.getInt(1));
                r.setName(rs.getString(2));
                r.setDescription(rs.getString(3));
                r.setDebut(rs.getString(4));
                r.setFin(rs.getString(5));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    private void goToCadeau(ActionEvent event) throws IOException{
        setInterface("test2");
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