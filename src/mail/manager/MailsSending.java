/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail.manager;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Haier
 */
public class MailsSending {

    public static void sendMail(ArrayList<String> recepients, String mailSubject, String senderMessage, String myAcountMail) throws MessagingException {

        System.out.println(recepients);
        boolean check = false;
        JOptionPane.showMessageDialog(null, myAcountMail);
        JOptionPane.showMessageDialog(null, mailSubject);
        JOptionPane.showMessageDialog(null, senderMessage);
        JOptionPane.showMessageDialog(null, recepients);
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

//        String myAcountMail = "technicaldemonstrations@gmail.com";
        String myPasswordMail = "wordpress12";

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAcountMail, myPasswordMail);
            }
        });

        for (String recepient : recepients) {
            Message message = prepareMessage(session, myAcountMail, recepient, mailSubject, senderMessage);
            try {
                Transport.send(message);
                check = true;
            } catch (MessagingException ex) {
                Logger.getLogger(MailsSending.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (check) {
            JOptionPane.showMessageDialog(null, "Message Sent Succesfully");
        } else {
            JOptionPane.showMessageDialog(null, "Connection Error Try again");
        }

    }

    private static Message prepareMessage(Session session, String myAcountMail, String recepient, String mailSubject, String senderMessage) throws MessagingException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAcountMail));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(mailSubject);
            message.setText(senderMessage);

            return message;
        } catch (Exception ex) {
            Logger.getLogger(MailsSending.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
