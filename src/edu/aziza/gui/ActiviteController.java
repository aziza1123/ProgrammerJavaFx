/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import com.itextpdf.text.DocumentException;
//import com.jfoenix.controls.JFXTextField;
import edu.aziza.entities.Activite;
import edu.aziza.entities.Reservation;
import edu.aziza.services.PDF;
import edu.aziza.services.ServiceActivite;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fatma
 */
public class ActiviteController implements Initializable {
    
    
    ObservableList<Activite> ob1 = FXCollections.observableArrayList();
    ObservableList<Activite> oblist1 = FXCollections.observableArrayList();

    @FXML
    private TextField tfnoma;
    @FXML
    private TextField tfprixa;
    @FXML
    private TextArea tadesca;
    @FXML
    private TableView<Activite> tableactivite;
    @FXML
    private TableColumn<Activite, String> cnoma;
    @FXML
    private TableColumn<Activite, Float> cprixa;
    @FXML
    private TableColumn<Activite, String> cdesca;
    @FXML
    private TextField tfrecherche;
 
 
    @FXML
    private Button btnadd;
    @FXML
    private Button btnedit;
    @FXML
    private Button btndelete;
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
        afficherr();
        // TODO
    }  
    private void afficherr() {
        

        ServiceActivite sa =new  ServiceActivite();

        ObservableList obeListe = FXCollections.observableList(sa.getAll());

        //idc.setCellValueFactory(new PropertyValueFactory<>("id_conge"));
        cnoma.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        cprixa.setCellValueFactory(new PropertyValueFactory<>("prix"));
        cdesca.setCellValueFactory(new PropertyValueFactory<>("description"));
                

//        tableactivite.setItems(ob1);
        
        tableactivite.setItems(ob1);
        tableactivite.setItems(obeListe);

                   
FilteredList<Activite> filteredData = new FilteredList<>(obeListe, e -> true);
        tfrecherche.setOnKeyReleased(e ->{
            tfrecherche.textProperty().addListener((observableValue, oldValue, newValue) ->{
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
            sortedData.comparatorProperty().bind(tableactivite.comparatorProperty());
            tableactivite.setItems(sortedData);
    }
    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {

        ServiceActivite sr = new ServiceActivite();
//        SoundClick sound = new SoundClick();

        
        String nom = tfnoma.getText();
        String description = tadesca.getText();
        String prix = tfprixa.getText();
       
//tfdate.getValue().isBefore(LocalDate.now())
        if (nom.isEmpty() || description.isEmpty()||prix.isEmpty() ) {
//            sound.playClick();
            //np.notifpushno("Erreur","Verifier les champs (non vide - tél 8 chiffres - Service : Installation ou Reparation) ");
          //  Alerterr();
          
        } 
//        else if ( !service.equals("Installation") && !service.equals("Reparation")  ) { 
//         AlerteService();
//        }
        
            
        else {

            Activite r = new Activite(nom, description, 0);
            sr.ajouter(r);
//            sound.playClick();
            //np.notifpush("Rendez-vous Ajouter", "Rendez-vous ajouter avec succes");
            //Alertsucc();
            afficherr();

        }

    }
 public Activite cc;
   // int IDRDV;
    @FXML
    private void editr(ActionEvent event) throws SQLException {
//
     
        String nom = tfnoma.getText();
        String description = tadesca.getText();
        float prix = parseFloat(tfprixa.getText());
        
        Activite st = new Activite();
//            st.setId(IDRDV);
        st.setNom(tfnoma.getText());
        st.setDescription(tadesca.getText());
        st.setPrix((Float)Float.parseFloat(tfprixa.getText()));
        
//
//
//          //st.setId_fournisseur(1);
//  
//          
//          try{
//    sr.modifier2(IDRDV,st);
//    afficherr();
//    JOptionPane.showMessageDialog(null,"le produit a été modifier avec succes");
////    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Stock.fxml"));
////     Parent root = loader.load();
////            nom_stock.getScene().setRoot(root);
//    }catch(Exception e)
//    {JOptionPane.showMessageDialog(null, e);}

        Activite da = (Activite) tableactivite.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
                                            afficherr();

            if (nom.isEmpty() || description.isEmpty()){
//            sound.playClick();
            //np.notifpushno("Erreur","Verifier les champs (non vide - tél 8 chiffres - Service : Installation ou Reparation) ");
           //   Alerterr();
          
        } 
//        else if ( !service.equals("Installation") && !service.equals("Reparation")  ) { 
//             AlerteService();
//
//        }
//          else if (  num.length() < 8 || num.length() > 8  ) { 
//             ///Alertetel();
//        }
          else{
//            sr.modifier(da.getId(),nom,prenom,num,mail);
              sr.modifier(da);
            afficherr();

            //clearfields();
//            sound.playClick();
//            np.notifpush("rendezvous Modifier", "Conge modifier avec succes");
                        afficherr();

          }
                                            afficherr();

        }
                                afficherr();

    }

    private void clearfields() {
        tfnoma.clear();
        tadesca.clear();
        tfprixa.clear();
        
        

    }
    
    private void selectcong(MouseEvent event) {

        cc = tableactivite.getSelectionModel().getSelectedItem();
        if (cc != null) {

            //iduu.setText(String.valueOf(cc.getIde_user()));
            tfnoma.setText(String.valueOf(cc.getNom()));
            tadesca.setText(String.valueOf(cc.getDescription()));
            tfprixa.setText(String.valueOf(cc.getPrix()));
            


            //java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dattee.getValue());
            //nom_u.setText(tab.getNom());
        }

    }
    @FXML
    private void supprimer(ActionEvent event) {

        ObservableList<Activite> selectedRows, allPeople;
        allPeople = tableactivite.getItems();
        selectedRows = tableactivite.getSelectionModel().getSelectedItems();
        Activite da = tableactivite.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            sr.supprimer(da.getId());
            tableactivite.getItems().clear();
            afficherr();
            clearfields();
//            sound.playClick();
//            np.notifpush("Reseravtion Deleted", "Reservation Deleted avec sucees");
        }

    }

        ServiceActivite sr = new ServiceActivite();
        
        
//    @FXML
//        private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {
//
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation de création du PDF");
//        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos réservation?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//            PDF sp = new PDF();
//            sp.liste_achatPDF();
//        }
//    }
//        
//    private void GeneratePdf(javafx.scene.input.MouseEvent event) throws IOException {
//
//        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
//
//        try {
//
//            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf("Reservation" + ".pdf")));//yyyy-MM-dd
//            document.open();
//            Paragraph ph1 = new Paragraph("Reservation");
//            Paragraph ph2 = new Paragraph(".");
//            PdfPTable table = new PdfPTable(3);
//
//            //On créer l'objet cellule.
//            PdfPCell cell;
//
//            //contenu du tableau.
//            table.addCell("nom");
//            table.addCell("prenom");
//            table.addCell("num");
//            table.addCell("mail");
//
//            //     table.addCell("Image : ");
//            sr.Afficher().forEach(e
//                    -> {
//                table.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//                table.addCell(String.valueOf(e.getNom()));
//                table.addCell(String.valueOf(e.getPrenom()));
//                table.addCell(String.valueOf(e.getNum()));
//                table.addCell(String.valueOf(e.getMail()));
//
//            }
//            );
//            document.add(ph1);
//            document.add(ph2);
//            document.add(table);
//            //  document.addAuthor("Bike");
//            // AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        document.close();
//
//        ///Open FilePdf
//        File file = new File("Reservation" + ".pdf");
//        if (file.exists()) //checks file exists or not  
//        {
//            desktop.open(file); //opens the specified file   
//        }
//
//    }

    @FXML
    private void BackToHome(javafx.scene.input.MouseEvent event) throws IOException {
        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
      


  
    
   

}

    

