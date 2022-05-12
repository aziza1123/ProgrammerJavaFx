/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;


import edu.aziza.entities.Event;
import edu.aziza.services.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class EventController implements Initializable {

    @FXML
    private TableView<?> ListPubs2;
    @FXML
    private TableColumn<?, ?> libelle_Pubs;
    @FXML
    private TableColumn<?, ?> Nbr_Reaction;
    @FXML
    private TextField barre;
    @FXML
    private Button Button_Delete_Search;
    @FXML
    private Button Button_Search;
    @FXML
    private TableColumn<Event, String> cname;
    @FXML
    private TableColumn<Event, String> cdescription;
    @FXML
    private Button supprbtn;
    @FXML
    private Button pdf;
    @FXML
    private TextField tfname;
    ObservableList<Event> oblist = FXCollections.observableArrayList();

    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tflieux;
    @FXML
    private TextField tfimage;
    @FXML
    private TextField tfplace;
    @FXML
    private TableView<Event> tabevent;
    @FXML
    private TableColumn<Event, String> clieux;
    @FXML
    private TableColumn<Event, String> cimage;
    @FXML
    private TableColumn<Event, String> nplace_dispo;
    @FXML
    private Button modifier;
    @FXML
    private Button ajout;
      ObservableList<Event> ob1 = FXCollections.observableArrayList();
    private Button btevents;
    @FXML
    private Button ajoutTicket;
   public static Event EvenementStatic ;
Event ev;
int idd=0;
public static int id_evenement_ticket;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Afficher();
//             ajoutTicket.setOnAction(event -> {
//
//            try {
//                Parent page1 = FXMLLoader.load(getClass().getResource("TicketF.fxml"));
//                Scene scene = new Scene(page1);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//                //Logger.getLogger(AfficherListeTerrainsController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });

    }    
public Event rom;

 

    @FXML
    private void selectcom(MouseEvent event) {
           rom = tabevent.getSelectionModel().getSelectedItem();
        if (rom != null) {

            //iduu.setText(String.valueOf(cc.getIde_user()));
           
            tfname.setText(String.valueOf(rom.getNom()));
            tfdescription.setText(String.valueOf(rom.getDescription()));
            
            tflieux.setText(String.valueOf(rom.getLieux()));
            tfplace.setText(String.valueOf(rom.getPlace_dispo()));
            tfimage.setText(String.valueOf(rom.getImage()));

          
        }
    }

     void recherche(){
        ServiceEvent SR=new ServiceEvent();
       Event r = new Event();
        cname.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        

        ObservableList<Event> dataList;

        dataList = (ObservableList<Event>) SR.getAll();
       
        tabevent.setItems(dataList);
       
        FilteredList<Event> filteredData = new FilteredList<>(dataList, b -> true);
       
        barre.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Event ar) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (ar.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               
                } else if (String.valueOf(ar.getDescription()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
                
              
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Event> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabevent.comparatorProperty());
        tabevent.setItems(sortedData);
    }
    
    
    

    
    
    @FXML
    private void Afficher() {
            ServiceEvent rs=new ServiceEvent();

            ObservableList obeListe = FXCollections.observableList(rs.getAll());
        cname.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clieux.setCellValueFactory(new PropertyValueFactory<>("lieux"));
        cimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        nplace_dispo.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));
      

        tabevent.setItems(ob1);
        
        tabevent.setItems(obeListe);
        recherche();
    }
    ServiceEvent rs=new ServiceEvent();
   
    @FXML
    private void Modifier(ActionEvent event) {
        String name = tfname.getText();
        String description = tfdescription.getText();
         String lieux =tflieux.getText();
         String place =tfplace.getText();
        String image= tfimage.getText();
        
        Event rr = new Event();
        rr.setNom(tfname.getText());
        rr.setDescription(tfdescription.getText());
          rr.setLieux(tflieux.getText());
        rr.setPlace_dispo(tfplace.getText());
        rr.setImage(tfimage.getText());
        
        Event ro = tabevent.getSelectionModel().getSelectedItem();
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
        ObservableList<Event> selectedRows, allPeople;
        allPeople = tabevent.getItems();
        selectedRows = tabevent.getSelectionModel().getSelectedItems();
        Event ro = tabevent.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirnation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
             rs.supprimer(ro.getId());
             tabevent.getItems().clear();
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
        String lieux =tflieux.getText();
         String place =tfplace.getText();
        String image= tfimage.getText();
       
        Event r = new Event(name, description, lieux, image, place);
        ServiceEvent rs = new ServiceEvent();
        rs.ajouter(r);
        Afficher();
//        Email from = new Email("CulturaApplication@gmail.com");
//        String subject = "Mail de Nouveautés ";
//        Email to = new Email("aziza.chouchane@esprit.tn");
//        Content content = new Content("text/plain", "Checkez notre application une nouvelle roulette est arrivée allez , venez tenter votre chance !! " );
//        Mail mail = new Mail(from, subject, to, content);
//
//        SendGrid sg = new SendGrid("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");
//        Request request = new Request();
//
//        try {
//            request.setMethod(Method.POST);
//            request.setEndpoint("mail/send");
//            request.setBody(mail.build());
//            Response response = sg.api(request);
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//            System.out.println(response.getHeaders());
//        } catch (IOException ex) {
//            System.out.println("message non envoyé");
//        }
    }
    }
       private void clearfields() {  
        tfname.clear();
        tfdescription.clear();
        tflieux.clear();
        tfplace.clear();
        tfimage.clear();

    }

    @FXML
    private void ajouterTicket(ActionEvent event) {
        if(tabevent.hasProperties() )
        {  
            idd = tabevent.getSelectionModel().getSelectedItem().getId();
            System.out.println(idd);
            ev = tabevent.getSelectionModel().getSelectedItem();
            id_evenement_ticket = idd;

            EvenementStatic = ev;
            System.out.println(id_evenement_ticket);
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("TicketF.fxml"));
                Stage stage =new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Ticket");
                stage.show();
        } catch (IOException ex) {
            // Logger.getLogger(edu.workshopjdbc3a48.gui.PIdesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
           //  AlertDialog.showNotification("Attention","Veuillez selectionner une evenement", AlertDialog.image_cross);
        }
    }

    private void BachToHome(MouseEvent event) throws IOException {
        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void DeleteTextFromTextField(ActionEvent event) {
    }

   

    @FXML
    private void BackToHome(MouseEvent event) throws IOException {
       Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void FindByLibelle(ActionEvent event) {
    }

    @FXML
    private void ExportPDF(ActionEvent event) {
    }
    
}
