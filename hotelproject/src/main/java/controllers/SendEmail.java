package controllers;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;


public class SendEmail
{

    //SETUP MAIL SERVER PROPERTIES
    //DRAFT AN EMAIL
    //SEND EMAIL

    Session newSession = null;
    MimeMessage mimeMessage = null;
//    public static void main(String args[]) throws AddressException, MessagingException, IOException
//    {
//        SendEmail mail = new SendEmail();
//        mail.setupServerProperties();
//        mail.draftEmail();
//        mail.sendEmail();
//    }

    public boolean sendEmail(String Email) {
        String fromUser = "hermezabderrahim568@gmail.com";
        String fromUserPassword = "pwqo zsea tqgl epre";
        String emailHost = "smtp.gmail.com";
        try {
            Transport transport = newSession.getTransport("smtp");
            transport.connect(emailHost, fromUser, fromUserPassword);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            System.out.println("Email successfully sent!!!");
            return true; // Return true if the email was sent successfully
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email");
            return false; // Return false if there was an error sending the email
        }
    }

    public MimeMessage draftEmail(String Email , String message , String token) throws AddressException, MessagingException, IOException {
        String[] emailReceipients = {"hermezabderrahim568@gmail.com",Email};  //Enter list of email recepients
        String emailSubject = "L oasis hotel token";
        String emailBody = message + token + " " + emailReceipients[1];
        mimeMessage = new MimeMessage(newSession);

        for (int i =0 ;i<emailReceipients.length;i++)
        {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
        }
        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody,"text/html"); // Set the correct MIME type
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }

    public void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties,null);
    }

}
