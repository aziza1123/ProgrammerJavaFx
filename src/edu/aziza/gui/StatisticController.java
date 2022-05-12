/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.gui;

import edu.aziza.services.ServiceTicket;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatisticController implements Initializable {

    @FXML
    private PieChart chart;
    @FXML
    private Button btok;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ServiceTicket p =new ServiceTicket();
          chart.setTitle("statisfaction"); 
        try {
            chart.getData().setAll(new PieChart.Data("vip", p.Recherche1()),new PieChart.Data("normal", p.Recherche2()), new PieChart.Data("double", p.Recherche3()),new PieChart.Data("single", p.Recherche4()));
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(StatisticController.class.getName()).log(Level.SEVERE, null, ex);
        }
         btok.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("fticket.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                //Logger.getLogger(AfficherListeTerrainsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    
        // TODO
    }    
    



    
}