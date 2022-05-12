/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.TextField;
import edu.aziza.entities.Reservation;
import edu.aziza.services.PDF;
import edu.aziza.services.ServiceReservation;
import static java.awt.SystemColor.desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fatma
 */
public class ReservtionController implements Initializable {
   ObservableList<Reservation> ob1 = FXCollections.observableArrayList();
    ObservableList<Reservation> oblist1 = FXCollections.observableArrayList();

    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private Button btnvalidrdv;
    @FXML
    private TableView<Reservation> tablereservation;
    @FXML
    private TableColumn<Reservation, String> cnom;
    @FXML
    private TableColumn<Reservation, String> cprenom;
    @FXML
    private TableColumn<Reservation, String> cnum;
    @FXML
    private TableColumn<Reservation, String> cmail;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsup;
    @FXML
    private Button btnPDF;
 
  

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
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cnum.setCellValueFactory(new PropertyValueFactory<>("num"));
                cmail.setCellValueFactory(new PropertyValueFactory<>("mail"));

        tablereservation.setItems(ob1);

        tablereservation.setItems(obeListe);

                   
FilteredList<Reservation> filteredData = new FilteredList<>(obeListe, e -> true);
        tfrecherche.setOnKeyReleased(e ->{
            tfrecherche.textProperty().addListener((observableValue, oldValue, newValue) ->{
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
            sortedData.comparatorProperty().bind(tablereservation.comparatorProperty());
            tablereservation.setItems(sortedData);
           
        });
    }
    
    
    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {

        ServiceReservation sr = new ServiceReservation();
//        SoundClick sound = new SoundClick();

        
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String num = tfnum.getText();
        String mail = tfmail.getText();
//tfdate.getValue().isBefore(LocalDate.now())
        if (nom.isEmpty() || prenom.isEmpty()||num.isEmpty() || mail.isEmpty() ) {
//            sound.playClick();
            //np.notifpushno("Erreur","Verifier les champs (non vide - tél 8 chiffres - Service : Installation ou Reparation) ");
          //  Alerterr();
          
        } 
//        else if ( !service.equals("Installation") && !service.equals("Reparation")  ) { 
//         AlerteService();
//        }
        
            
        else {

            Reservation r = new Reservation(nom, prenom, num, mail);
            sr.ajouter(r);
//            sound.playClick();
            //np.notifpush("Rendez-vous Ajouter", "Rendez-vous ajouter avec succes");
            //Alertsucc();
            afficherr();

        }

    }
 public Reservation cc;
    int IDRDV;

    @FXML
    private void editr(ActionEvent event) throws SQLException {
//
     
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String num = tfnum.getText();
        String mail = tfmail.getText();
        Reservation st = new Reservation();
//            st.setId(IDRDV);
        st.setNom(tfnom.getText());
        st.setPrenom(tfprenom.getText());
        st.setNum(tfnum.getText());
        st.setMail(tfmail.getText());
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

        Reservation da = tablereservation.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
                                            afficherr();

            if (nom.isEmpty() || prenom.isEmpty()||num.isEmpty() || mail.isEmpty()) {
//            sound.playClick();
            //np.notifpushno("Erreur","Verifier les champs (non vide - tél 8 chiffres - Service : Installation ou Reparation) ");
           //   Alerterr();
          
        } 
//        else if ( !service.equals("Installation") && !service.equals("Reparation")  ) { 
//             AlerteService();
//
//        }
          else if (  num.length() < 8 || num.length() > 8  ) { 
             ///Alertetel();
        }
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
        tfnom.clear();
        tfprenom.clear();
        tfnum.clear();
        tfmail.clear();
        

    }
    
    private void selectcong(MouseEvent event) {

        cc = tablereservation.getSelectionModel().getSelectedItem();
        if (cc != null) {

            //iduu.setText(String.valueOf(cc.getIde_user()));
            tfnom.setText(String.valueOf(cc.getNom()));
            tfprenom.setText(String.valueOf(cc.getPrenom()));
            tfnum.setText(String.valueOf(cc.getNum()));
            tfmail.setText(String.valueOf(cc.getMail()));


            //java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dattee.getValue());
            //nom_u.setText(tab.getNom());
        }

    }
@FXML
    private void supprimer(ActionEvent event) {

        ObservableList<Reservation> selectedRows, allPeople;
        allPeople = tablereservation.getItems();
        selectedRows = tablereservation.getSelectionModel().getSelectedItems();
        Reservation da = tablereservation.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            sr.supprimer(da.getId());
            tablereservation.getItems().clear();
            afficherr();
            clearfields();
//            sound.playClick();
//            np.notifpush("Reseravtion Deleted", "Reservation Deleted avec sucees");
        }

    }

        ServiceReservation sr = new ServiceReservation();
        
        
    @FXML
        private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos réservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            PDF sp = new PDF();
            sp.liste_ArticlePDF();
        }
    }
        
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




    
//    private void PDF(ActionEvent event) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
////        pdf.CreatePdf();
//    
//       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation de création du PDF");
//        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos reservations?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//             PDF sp= new PDF();
//        sp.liste_reservationPDF();
//        }
//    }

    @FXML
    private void selectcong(javafx.scene.input.MouseEvent event) {
    }

    @FXML
    private void GoToHome(javafx.scene.input.MouseEvent event) throws IOException {
         Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }


    
   

}
