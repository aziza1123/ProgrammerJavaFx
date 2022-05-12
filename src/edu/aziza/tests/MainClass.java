  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.tests;

import edu.aziza.entities.Cadeau;
import edu.aziza.entities.Roulette;
import edu.aziza.services.CadeauService;
import edu.aziza.services.RouletteService;
import edu.aziza.utils.DataSource;

/**
 *
 * @author 21650
 */
public class MainClass {
    public static void main(String[] args) {
        DataSource ds=new DataSource();
               CadeauService sc=new CadeauService();
                RouletteService rs=new RouletteService();

              // Cadeau rs= new Cadeau("chika","123","123","dd");
               Roulette r=new Roulette("chika","lalalal","123","123");
               
              //sc.ajouter(c2);
               rs.modifier(67,r);
               //r.ajouter(new Roulette("aziza", "desc", 2021/12/15, 2021/12/15));
             System.out.println(rs.recuperer());
              //sc.supprimer(50);
              //rs.modifer(42,"hahah","hahaah","aziza","baba");
    }
    
    
}
