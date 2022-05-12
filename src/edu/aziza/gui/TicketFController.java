/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import com.itextpdf.text.DocumentException;
import edu.aziza.entities.Ticket;
import edu.aziza.services.ServicePdf;
import edu.aziza.services.ServiceTicket;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class TicketFController implements Initializable {
    private boolean verificationUsernom;
    private boolean verificationUserdescrip;
    private boolean verificationUserPhone;
    private boolean verificationUserQte;
    ObservableList<Ticket> ticket=FXCollections.observableArrayList();
    
    ServiceTicket sr= new ServiceTicket();
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
    private TextField tfdescription;
    @FXML
    private TableColumn<Ticket,String> cname;
    @FXML
    private TableColumn<Ticket,String> cdescription;
    @FXML
    private Button modifier;
    @FXML
    private Button supprbtn;
    @FXML
    private Button pdf;
    @FXML
    private TextField tfname;
     ObservableList<Ticket> oblist = FXCollections.observableArrayList();
    @FXML
    private Button ajout;
      ObservableList<Ticket> ob1 = FXCollections.observableArrayList();


    @FXML
    private TextField tftype;
    @FXML
    private TextField tfqte;
    @FXML
    private TextField tfprix;
    @FXML
    private TableView<Ticket> tabticket;
    @FXML
    private TableColumn<Ticket,String> ctype;
    @FXML
    private TableColumn<Ticket,String> cqte;
    @FXML
    private TableColumn<Ticket,String> cprix;
    @FXML
    private Label erreur_description;
    @FXML
    private Label erreur_qte;
    @FXML
    private Label erreur_nom;
    @FXML
    private Label erreur_prix;
    @FXML
    private ImageView nomCM;
    @FXML
    private ImageView qteCM;
    @FXML
    private ImageView descCM;
    @FXML
    private ImageView prixCM;
    @FXML
    private Button stat;
    @FXML
    private TextField idEvent;
    @FXML
    private ImageView qr;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        try {
            Afficher();
        } catch (IOException ex) {
            Logger.getLogger(TicketFController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         EventController a= new EventController();
       // System.out.println(EventController.id_evenement_participant);
        
        idEvent.setText(String.valueOf( EventController.id_evenement_ticket));
    }    
public Ticket rom;


    @FXML
    private void DeleteTextFromTextField(ActionEvent event) {
    }

    @FXML
    private void FindByLibelle(ActionEvent event) {
    }

    @FXML
    private void selectcom(MouseEvent event) {
        rom = tabticket.getSelectionModel().getSelectedItem();
        if (rom != null) {

            //iduu.setText(String.valueOf(cc.getIde_user()));
           
            tfname.setText(String.valueOf(rom.getName()));
            tfdescription.setText(String.valueOf(rom.getDescription()));
            
            tftype.setText(String.valueOf(rom.getType()));
            tfqte.setText(String.valueOf(rom.getQte()));
            tfprix.setText(String.valueOf(rom.getPrix()));

          
        }
    }

     void recherche(){
        ServiceTicket SR=new ServiceTicket();
       Ticket r = new Ticket();
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        

        ObservableList<Ticket> dataList;

        dataList = (ObservableList<Ticket>) SR.getAll();
       
        tabticket.setItems(dataList);
       
        FilteredList<Ticket> filteredData = new FilteredList<>(dataList, b -> true);
       
        barre.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Ticket ar) -> {
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
        SortedList<Ticket> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabticket.comparatorProperty());
        tabticket.setItems(sortedData);
    }
    
    @FXML
    private void Afficher() throws FileNotFoundException, IOException {
        ServiceTicket rs = new ServiceTicket();

        ObservableList obeListe = FXCollections.observableList(rs.getAll());
        cname.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ctype.setCellValueFactory(new PropertyValueFactory<>("type"));
        cqte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tabticket.setItems(ob1);

        tabticket.setItems(obeListe);
        recherche();

        ticket = FXCollections.observableArrayList(sr.getAll());
        tabticket.setItems(ticket);
        //ByteArrayOutputStream out = QRCode.from(ticket.toString()).to(ImageType.JPG).stream();
        
         String details ="https://www.google.com/maps/place/"+EventController.EvenementStatic.getLieux();
         ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
        File file = new File("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\aziza\\src\\ressources\\MyChannel.jpg");
          FileOutputStream fos;
  
       fos = new FileOutputStream(file);
            fos.write(out.toByteArray());
        fos.flush();
  
// --> file:/C:/MyImages/myphoto.jpg
        String localUrl = file.toURI().toURL().toString();

        Image image = new Image(localUrl);
       
        qr.setImage(image);
        
        
        
        
    }
  ServiceTicket rs=new ServiceTicket();
    @FXML
    private void Modifier(ActionEvent event) throws IOException {
         String name = tfname.getText();
        String description = tfdescription.getText();
         String qte =tfqte.getText();
         String type =tftype.getText();
        String prix= tfprix.getText();
        
        Ticket rr = new Ticket();
        rr.setName(tfname.getText());
        rr.setDescription(tfdescription.getText());
          rr.setType(tftype.getText());
        rr.setQte(tfqte.getText());
        rr.setPrix(tfprix.getText());
        
        Ticket ro = tabticket.getSelectionModel().getSelectedItem();
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
    
    }

    @FXML
    private void ExportPDF(ActionEvent event) throws FileNotFoundException, DocumentException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos livraisons?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             ServicePdf sp= new ServicePdf();
        sp.liste_achatPDF();
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
          Boolean ok=false;
          
//        Smsapi.sendSMS("+216"+tel, "Bonjour "+u.getNom()+",\n" +"Merci d’avoir fait confiance a Campi." +"\n" +"Nous aimerions vous confirmer que votre inscription a l'evenement d'ID  "+u.getEvenements_id()+" a été validé." +"\n" +"Cordialement,\n" +"L’équipe de Campi.");
 /// *******   Smsapi.sendSMS("+216"+58105027, "Bonjour Nous aimerions vous confirmer que votre inscription a l'evenement d'ID L’équipe de Campi.");
     if ((tfname.getText().toString().compareTo("") == 0)  ||  (tfdescription.getText().toString().compareTo("") == 0 )) {
         ok=true;
            JOptionPane.showMessageDialog(null, "champs vide");

} else {
         
         if(Integer.parseInt(EventController.EvenementStatic.getPlace_dispo())==0)
         {System.out.println("ereeeeeur");}
         else {
         
        int id = Integer.parseInt(idEvent.getText());
        String name =tfname.getText();
        String description= tfdescription.getText();
        String type =tftype.getText();
         String qte =tfqte.getText();
        String prix= tfprix.getText();
       
        Ticket r = new Ticket(name, description, type, qte, prix,id);
                
        ServiceTicket rs = new ServiceTicket();
        rs.ajouter(r);
        Afficher(); }}
    }
    
    private void clearfields() {  
        tfname.clear();
        tfdescription.clear();
        tftype.clear();
        tfqte.clear();
        tfprix.clear();

    }
    
public boolean isNumeric(String str){
        if(str==null){
            return false;
        }
        try
        {
            int x=Integer.parseInt(str);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    @FXML
    private void testqte(KeyEvent event) {
           if (isNumeric(tfqte.getText())) {
                erreur_qte.setText("prix valide");
                 qteCM.setImage(new Image("Images/checkMark.png"));
                verificationUserQte = true;
            } else {             
                qteCM.setImage(new Image("Images/erreurcheckMark.png"));
                erreur_qte.setText("prix non valide");
                verificationUserQte= false;

            }
    }

    @FXML
    private void testdescrip(KeyEvent event) {
         int nbNonChar = 0;
            for (int i = 1; i < tfdescription.getText().trim().length(); i++) {
                char ch = tfdescription.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfdescription.getText().trim().length() >=5) {
            descCM.setImage(new Image("Images/checkMark.png"));
            erreur_description.setText("Description valide");
            
            verificationUserdescrip= true;
            } else {
              
                descCM.setImage(new Image("Images/erreurcheckMark.png"));
              erreur_description.setText("veuillez saisir une description valide ");
              verificationUserdescrip = false;

            }
    }

    @FXML
    private void testprix(KeyEvent event) {
         if (isNumeric(tfprix.getText())&& Integer.parseInt(tfprix.getText())>0) {
                erreur_prix.setText("prix valide");
                 prixCM.setImage(new Image("Images/checkMark.png"));
                verificationUserPhone = true;
            } else {             
                prixCM.setImage(new Image("Images/erreurcheckMark.png"));
                erreur_prix.setText("prix non valide");
                verificationUserPhone = false;

            }
    }

    @FXML
    private void testnom(KeyEvent event) {
         int nbNonChar = 0;
            for (int i = 1; i < tfname.getText().trim().length(); i++) {
                char ch = tfname.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfname.getText().trim().length() >=3) {
            nomCM.setImage(new Image("Images/checkMark.png"));
            erreur_nom.setText("Nom valide");
            
            verificationUsernom = true;
            } else {
              
                nomCM.setImage(new Image("Images/erreurcheckMark.png"));
              erreur_nom.setText("veuillez saisir un nom valide ");
              verificationUsernom = false;

            }
    }

    @FXML
    private void stat(ActionEvent event) {
              try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("statistic.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            // Logger.getLogger(edu.workshopjdbc3a48.gui.PIdesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("Event.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
         //    Logger.getLogger(edu.workshopjdbc3a48.gui.PIdesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
