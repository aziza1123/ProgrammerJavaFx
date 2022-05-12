/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.aziza.entities.Produit;
import edu.aziza.entities.Roulette;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author 21650
 */
public class PDFProd {
     public void liste_ProduitPDF() throws FileNotFoundException, DocumentException {

        ProduitService SA = new ProduitService();
        String message = "Voici la liste des produits \n\n";

        String file_name = "src/pdf/liste_ProduitPDF.pdf";
        Document document = new Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
         List<Produit> Produit = SA.recuperer();
        PdfPTable table = new PdfPTable(4);

        
        PdfPCell cl1 = new PdfPCell(new Phrase("nom"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("description"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("prix"));
        table.addCell(cl3);
         PdfPCell cl4 = new PdfPCell(new Phrase("quantite"));
        table.addCell(cl4);
         PdfPCell cl5 = new PdfPCell(new Phrase("image"));
        table.addCell(cl5);
         
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < Produit.size(); i++) {
           
            table.addCell("" + Produit.get(i).getNom());
            table.addCell("" + Produit.get(i).getDescription());
            
            table.addCell("" + Produit.get(i).getPrix());
            table.addCell("" + Produit.get(i).getQuantite());
            table.addCell("" + Produit.get(i).getImg());

        }
        document.add(table);

        document.close();

    }
}


