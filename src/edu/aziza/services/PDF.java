/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.aziza.entities.Roulette;

/**
 *
 * @author 21650
 */
public class PDF {
    public void liste_ArticlePDF() throws FileNotFoundException, DocumentException {

        RouletteService SA = new RouletteService();
        String message = "Voici la liste des roulettes \n\n";

        String file_name = "src/pdf/liste_Article.pdf";
        Document document = new Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
         List<Roulette> Roulette = SA.recuperer();
        PdfPTable table = new PdfPTable(4);

        
        PdfPCell cl1 = new PdfPCell(new Phrase("name"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("description"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("debut"));
        table.addCell(cl3);
         PdfPCell cl4 = new PdfPCell(new Phrase("fin"));
        table.addCell(cl4);
         
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < Roulette.size(); i++) {
           
            table.addCell("" + Roulette.get(i).getName());
            table.addCell("" + Roulette.get(i).getDescription());
            
            table.addCell("" + Roulette.get(i).getDebut());
            table.addCell("" + Roulette.get(i).getFin());

        }
        document.add(table);

        document.close();

    }
}
