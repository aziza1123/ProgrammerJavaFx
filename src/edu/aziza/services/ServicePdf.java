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
import edu.aziza.entities.Ticket;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author USER
 */
public class ServicePdf {
     public void liste_achatPDF() throws FileNotFoundException, DocumentException{
        String message = "                       Voici la liste des Rdv \n\n";
              ServiceTicket so = new ServiceTicket();

        
        String file_name = "src/PDF/liste_achat.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
         List<Ticket> achatt = so.getAll();
        PdfPTable table = new PdfPTable(5);

        PdfPCell cl = new PdfPCell(new Phrase("Name"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("Description"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Type"));
        table.addCell(cl2);
      PdfPCell cl3 = new PdfPCell(new Phrase("prix"));
        table.addCell(cl3);
         PdfPCell cl4 = new PdfPCell(new Phrase("Qte"));
        table.addCell(cl4);
     
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        for (int i = 0; i < achatt.size(); i++) {
            table.addCell(""+ achatt.get(i).getName());
            table.addCell("" + achatt.get(i).getDescription());
            table.addCell("" + achatt.get(i).getType());
            table.addCell("" + achatt.get(i).getQte());
            table.addCell("" + achatt.get(i).getPrix());


        }
        document.add(table);

        document.close();

    
        }      
}
