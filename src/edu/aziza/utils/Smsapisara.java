/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class Smsapisara {
    public static final String ACCOUNT_SID = "AC7634a932b1459f6c47e8267d5278f18f";
    public static final String AUTH_TOKEN = "466e87e740c89ef194639587e1482348";

    public static void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(num),new PhoneNumber("+18645279772"), msg).create();

        System.out.println(message.getSid());

    }
}
