/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import Alert.AlertDialog;
import edu.aziza.entities.Publication;
import edu.aziza.services.ServicePublication;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import edu.aziza.gui.FirstWindow;




/**
 * FXML Controller class
 *
 * @author nader
 */
public class AjoutPublicationController implements Initializable {

   
   
 
    @FXML
    private Button insert;
    
     private File Current_file;
 
    private String file_image;
    
    @FXML
    private ImageView id_media;
    @FXML
    private TextField id_titre;
     @FXML
    private TextField id_description;
     
     
    
    @FXML
    private ImageView titreCM;
    @FXML
    private Label erreur_titre;
    @FXML
    private ImageView descriptionCM;
    @FXML
    private Label erreur_description;
    @FXML
    private Button retour;
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

//    @FXML
//    private void dragover(DragEvent event) {
//        Dragboard board = event.getDragboard();
//        
//        if (board.hasFiles()) {
//            event.acceptTransferModes(TransferMode.ANY);
//            
//        }
//    }
//
//    @FXML
//    private void dropimg(DragEvent event) throws FileNotFoundException {
//        Dragboard board = event.getDragboard();
//        List<File> phil = board.getFiles();
//        FileInputStream fis;
//        fis = new FileInputStream(phil.get(0));
//        Image image = new Image(fis);
//        File selectedFile = phil.get(0);
//        if (selectedFile != null) {
//
//            String test = selectedFile.getAbsolutePath();
//            System.out.println(test);
//
//            Current_file = selectedFile.getAbsoluteFile();
//            file_image = Current_file.getName();
//            Publication e = new Publication();
//            e.setMedia(selectedFile.getName());
//           id_media.setImage(image);
//        }
//    }
    
    
    
    @FXML
    private void dragover(DragEvent event) {
          Dragboard board = event.getDragboard();
        
        if (board.hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
            
       }
        
    }

    @FXML
    private void dropimg(DragEvent event) throws FileNotFoundException {
         Dragboard board = event.getDragboard();
        List<File> phil = board.getFiles();
        FileInputStream fis;
        fis = new FileInputStream(phil.get(0));
        Image image = new Image(fis);
        File selectedFile = phil.get(0);
        if (selectedFile != null) {

            String test = selectedFile.getAbsolutePath();
            System.out.println(test);

            Current_file = selectedFile.getAbsoluteFile();
            file_image = Current_file.getName();
            Publication e = new Publication();
            e.setMedia(selectedFile.getName());
           id_media.setImage(image);
        }
        
    }
    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, ParseException {
        

Boolean ok=false;
     if (  (id_titre.getText().toString().length() > 10 ) ||  (id_description.getText().toString().length() < 10 )) {
         ok=true;
            JOptionPane.showMessageDialog(null, "champs invalid");

} else {
        file_image = "src/Images/" + file_image;
//        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");  
        ServicePublication sp=new ServicePublication(); 
//        System.out.println(id_date.toString());
        Publication u = new Publication();
        u.setMedia(file_image);
        u.setTitre(id_titre.getText());
        u.setDescription(id_description.getText());
      
        
            sp.Ajouter(u);
            
            try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("naderpub.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

               
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FirstWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            AlertDialog.showNotification("ajout","avec succee", AlertDialog.image_checked);
    }}

    
    
    
    
    @FXML
    private void testtitre(KeyEvent event) {
         int nbNonChar = 0;
            for (int i = 1; i < id_titre.getText().trim().length(); i++) {
                char ch = id_titre.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && id_titre.getText().trim().length() <=10) {
            titreCM.setImage(new Image("Images/checked.png"));
            erreur_titre.setText("Titre valide");
            
           
            } else {
              titreCM.setImage(new Image("Images/cross.png"));
              erreur_titre.setText("Il faut au max 10 caracteres");
              

            }
    }

    @FXML
    private void testdescription(KeyEvent event) {
        int nbNonChar = 0;
            for (int i = 1; i < id_description.getText().trim().length(); i++) {
                char ch = id_description.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && id_description.getText().trim().length() >=10) {
            descriptionCM.setImage(new Image("Images/checked.png"));
            erreur_description.setText("Description valide");
           
            } else {
                descriptionCM.setImage(new Image("Images/cross.png"));
                erreur_description.setText("Il faut au moins 10 caracteres");
                

            }
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
//    @FXML
//    private void testnum(KeyEvent event) {
//        if (id_num.getText().trim().length() == 😎 {
//            int nbChar = 0;
//            for (int i = 1; i < id_num.getText().trim().length(); i++) {
//                char ch = id_num.getText().charAt(i);
//
//                if (Character.isLetter(ch)) {
//
//                    nbChar++;
//
//                }
//                //System.out.println(nbChar);
//            }
//
//            if (isNumeric(id_num.getText())) {
//                erreur_num.setText("Tel valide");
//                 numCM.setImage(new Image("Images/checkMark.png"));
//                verificationUserPhone = true;
//            } else {             numCM.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur_num.setText("Tel non valide");
//                verificationUserPhone = false;
//
//            }
//
//        } else {numCM.setImage(new Image("Images/erreurcheckMark.png"));
//            erreur_num.setText("Il faut 8 chiffres");
//            verificationUserPhone = false;
//        }
//    }

    @FXML
    private void retour(ActionEvent event) {
        
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("naderpub.fxml"));
            Stage stage =new Stage();
            
                Scene scene = new Scene(root);
            
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(FirstWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}






