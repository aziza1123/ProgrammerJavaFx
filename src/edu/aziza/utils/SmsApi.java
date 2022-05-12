/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.utils;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author DELL
 */
public class SmsApi {
    
    public static final String ACCOUNT_SID = "AC88c62a55dbd6a2dc6774c6c217fe49c3";
    public static final String AUTH_TOKEN = "117acc27cee6ed0c3c8c42c49eb4bfe4";

    public static void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(num),new PhoneNumber("+19206858912"), msg).create();
        System.out.println(message.getSid());

    }
    
    
}
