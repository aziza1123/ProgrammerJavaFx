/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.utils;


import edu.aziza.entities.Ticket;
import edu.aziza.services.ServiceTicket;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import static java.lang.System.out;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author khalil kouki
 */
public class QRCodeGenerator {
    
    public static void main() throws Exception{
//Reservation r = new Reservation();
//            reservation_service rs  = new reservation_service();          
Ticket a = new Ticket();
       ServiceTicket ws = new ServiceTicket();
       
            String details = String.valueOf("okkkkk");
             
    ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
    
    File f = new File ("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\aziza\\src\\ressources\\MyChannel.jpg");
    FileOutputStream fos = new FileOutputStream(f);
        
    fos.write(out.toByteArray());
    fos.flush();
    
    
}

    
}
