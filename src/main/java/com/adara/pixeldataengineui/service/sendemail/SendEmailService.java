package com.adara.pixeldataengineui.service.sendemail;

import com.adara.pixeldataengineui.service.pixeldataenginerules.PixelDataEngineGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Address;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by yzhao on 10/20/16.
 */
public class SendEmailService extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        // Sender's email ID needs to be mentioned
        String from = "pde.alarm@gmail.com";

        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Username
        String user = "pde.alarm@gmail.com";
        String password = "pdealarm123";


        // Get system properties
        Properties properties = System.getProperties();


        properties.setProperty("mail.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        Authenticator auth = new SMTPAuthenticator(user, password);

        // Get the default Session object.
        Session session = Session.getInstance(properties, auth);

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        MimeMessage message = null;
        try {
            // Create a default MimeMessage object.
            message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            Address[]  recipients = {new InternetAddress("yi.zhao@adara.com")};
            message.addRecipients(Message.RecipientType.TO,
                    recipients);
            // Set Subject: header field
            message.setSubject("PDE Error Alert!");
            // Now set the actual message
            message.setText("PDE error found in udcuv2w101-lax1");
            // Send message
            Transport.send(message);
            /*String title = "Send Email";
            String res = "Sent message successfully....";
            String docType =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " +
                            "transitional//en\">\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<p align=\"center\">" + res + "</p>\n" +
                    "</body></html>");*/

        } catch (AddressException e) {

        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }

    private class SMTPAuthenticator extends Authenticator {
        private PasswordAuthentication authentication;

        public SMTPAuthenticator(String login, String password) {
            authentication = new PasswordAuthentication(login, password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}
