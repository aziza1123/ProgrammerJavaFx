
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import Alert.AlertDialog;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import edu.aziza.entities.Produit;
import edu.aziza.services.PDF;
import edu.aziza.services.PDFProd;
import edu.aziza.services.ProduitService;
import edu.aziza.utils.DataSource;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Hyperlink;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AjoutProduitController implements Initializable {

    Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfquantite;

    @FXML
    private TableView<Produit> tabproduit;
    @FXML
    private TableColumn<Produit, String> pnom;
    @FXML
    private TableColumn<Produit, String> pdescription;
    @FXML
    private TableColumn<Produit, Float> pprix;
    @FXML
    private TableColumn<Produit, Integer> pquantite;

    private String tfImg;

    private File Current_File;

    ObservableList<Produit> ob1 = FXCollections.observableArrayList();
    ObservableList<Produit> oblist1 = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cbtriObjPred;

    @FXML
    private TextField barre;
    @FXML
    private Button modifbtn;
    @FXML
    private Button supprbtn;
    @FXML
    private Button ajoutbtn;
    @FXML
    private TableView<?> ListPubs2;
    @FXML
    private TableColumn<?, ?> libelle_Pubs;
    @FXML
    private TableColumn<?, ?> Nbr_Reaction;
    @FXML
    private ImageView tfimg;
    @FXML
    private TableColumn<?, ?> pimg;
    @FXML
    private Button pdf;
    @FXML
    private Hyperlink givewayhyperlink;
    @FXML
    private Hyperlink givewaytitle;
    @FXML
    private ImageView giveawaypic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Afficher();
        ProduitService ps = new ProduitService();

        List<Produit> Produit = ps.recuperer();
        ObservableList<String> listTriObjPred = FXCollections.observableArrayList("Par Nom", "Par Description", "Par Prix");
        cbtriObjPred.setItems(listTriObjPred);

    }

    private void clearfields() {
        tfNom.clear();
        tfDescription.clear();
        tfprix.clear();
        tfquantite.clear();
        tfimg.setImage(null);

    }

    public Produit com;

    private void selectcom(MouseEvent event) {
        com = tabproduit.getSelectionModel().getSelectedItem();
        if (com != null) {

            //iduu.setText(String.valueOf(cc.getIde_user()));
            tfNom.setText(String.valueOf(com.getNom()));

            tfDescription.setText(String.valueOf(com.getDescription()));
            tfprix.setText(String.valueOf(com.getPrix()));
            tfquantite.setText(String.valueOf(com.getQuantite()));
            // tfimg.setText(String.valueOf(com.getImg()));

            //java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dattee.getValue());
            //nom_u.setText(tab.getNom());
        }

    }

    @FXML
    private void selectTriObjPred(ActionEvent event) {
        ProduitService Sar = new ProduitService();
        ObservableList<Produit> Ar = FXCollections.observableArrayList();
        if (cbtriObjPred.getValue().equals("Par Nom")) {
            Ar = Sar.trierProduitNom();
        } else if (cbtriObjPred.getValue().equals("Par Description")) {
            Ar = Sar.trierProduitDesc();

        } else if (cbtriObjPred.getValue().equals("Par Prix")) {
            Ar = Sar.trierProduitPrix();

            tabproduit.setItems(Ar);
        }
    }
//    @FXML
//    private void showcoupons(ActionEvent event) throws IOException, URISyntaxException {
//        OkHttpClient client = new OkHttpClient();
//
//       Request request = new Request.Builder()
//	.url("https://gamerpower.p.rapidapi.com/api/filter?platform=epic-games-store.steam.android&type=game.loot")
//	.get()
//	.addHeader("x-rapidapi-host", "gamerpower.p.rapidapi.com")
//	.addHeader("x-rapidapi-key", "8e98077110msh3fba1864b104aa6p1f98fajsna13a5e5ab73e")
//	.build();
//    Response response = client.newCall(request).execute();
//     System.out.println(response.isSuccessful());
//     ObjectMapper obj = new ObjectMapper();
//     JsonNode jn = obj.readTree(response.body().string());
//     System.out.println(jn.get(7).get("open_giveaway_url").asText());
//      
//     givewayhyperlink.setText(jn.get(7).get("open_giveaway_url").asText());
//     givewaytitle.setText(jn.get(1).get("title").asText());
//     givewayhyperlink.setOnAction(ee ->{
//            try {
//                Desktop.getDesktop().browse(new URI(jn.get(7).get("open_giveaway_url").asText()));
//            } catch (URISyntaxException ex) {
//                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//     });
//     org.jsoup.Connection.Response d=Jsoup.connect(jn.get(4).get("image").asText())
//             .ignoreContentType(true)
//             .execute();
//     
//        
//     giveawaypic.setImage(new Image(d.bodyStream()));
//    }

    @FXML
    private void Afficher() {
        ProduitService ps = new ProduitService();

        ObservableList obeListe = FXCollections.observableList(ps.recuperer());
        pnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        pprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        pquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        pimg.setCellValueFactory(new PropertyValueFactory<>("img"));

        tabproduit.setItems(ob1);

        tabproduit.setItems(obeListe);
        recherche();

    }

    @FXML
    private void Modifier(ActionEvent event) {

        String nom = tfNom.getText();
        String description = tfDescription.getText();
        float prix = Float.parseFloat(tfprix.getText());
        int quantite = Integer.parseInt(tfquantite.getText());
        // tfImg = "src/edu/salma/Images" + tfImg;           
        Produit pr = new Produit();

        pr.setNom(tfNom.getText());
        pr.setDescription(tfDescription.getText());
        pr.setPrix((Float) Float.parseFloat(tfprix.getText()));
        pr.setQuantite((Integer) Integer.parseInt(tfprix.getText()));
        //  pr.setImg(tfImg);

        Produit po = tabproduit.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            ps.modifier(po.getId(), pr);
            Afficher();
            clearfields();

        }

    }

    @FXML
    private void supprimer(ActionEvent event) {
        ProduitService ps = new ProduitService();
        ObservableList<Produit> selectedRows, allPeople;
        allPeople = tabproduit.getItems();
        selectedRows = tabproduit.getSelectionModel().getSelectedItems();

        Produit p = tabproduit.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            ps.supprimer(p.getId());
            tabproduit.getItems().clear();
            clearfields();
            Afficher();

        }
    }
    ProduitService ps = new ProduitService();

    @FXML
    private void ajouter(ActionEvent event) {
        Boolean ok = false;
        if ((tfNom.getText().toString().length() > 10) || (tfDescription.getText().toString().length() < 10)) {
            ok = true;
            JOptionPane.showMessageDialog(null, "champs invalid");
        } else {

            String nom = tfNom.getText();
            String description = tfDescription.getText();
            float prix = Float.parseFloat(tfprix.getText());
            int quantite = Integer.parseInt(tfquantite.getText());

            tfImg = "src/edu/salma/Images" + tfImg;

            Produit p = new Produit(nom, description, prix, quantite, tfImg);
            ProduitService ps = new ProduitService();

            ps.ajouter(p);

            AlertDialog.showNotification("ajout", "avec succee", AlertDialog.image_checked);
            Afficher();

        }

    }

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

            Current_File = selectedFile.getAbsoluteFile();
            tfImg = Current_File.getName();
            Produit p = new Produit();
            p.setImg(selectedFile.getName());
            tfimg.setImage(image);
        }
    }

    void recherche() {
        ProduitService SR = new ProduitService();
        Produit r = new Produit();
        pnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pdescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        ObservableList<Produit> dataList;

        dataList = (ObservableList<Produit>) SR.recuperer();

        tabproduit.setItems(dataList);

        FilteredList<Produit> filteredData = new FilteredList<>(dataList, b -> true);

        barre.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Produit ar) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (ar.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username

                } else if (String.valueOf(ar.getDescription()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabproduit.comparatorProperty());
        tabproduit.setItems(sortedData);
    }

    @FXML
    private void GoToHomeBack(MouseEvent event) throws IOException {

        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageclose.close();
        Parent root = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ExportPDF(ActionEvent event)  throws FileNotFoundException, DocumentException{
        
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos produit ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             PDFProd sp= new PDFProd();
        sp.liste_ProduitPDF();
        }
    }
    @FXML
    private void showcoupons(ActionEvent event) throws IOException, URISyntaxException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://gamerpower.p.rapidapi.com/api/filter?platform=epic-games-store.steam.android&type=game.loot")
                .get()
                .addHeader("x-rapidapi-host", "gamerpower.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "8e98077110msh3fba1864b104aa6p1f98fajsna13a5e5ab73e")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.isSuccessful());
        ObjectMapper obj = new ObjectMapper();
        JsonNode jn = obj.readTree(response.body().string());
        System.out.println(jn.get(7).get("open_giveaway_url").asText());

        givewayhyperlink.setText(jn.get(7).get("open_giveaway_url").asText());
        givewaytitle.setText(jn.get(1).get("title").asText());
        givewayhyperlink.setOnAction(ee -> {
            try {
                Desktop.getDesktop().browse(new URI(jn.get(7).get("open_giveaway_url").asText()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        org.jsoup.Connection.Response d = Jsoup.connect(jn.get(4).get("image").asText())
                .ignoreContentType(true)
                .execute();

        giveawaypic.setImage(new Image(d.bodyStream()));
    }
}
