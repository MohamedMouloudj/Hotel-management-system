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

    public void sendEmail(String Email) throws MessagingException {
        String fromUser = "hermezabderrahim568@gmail.com";  //Enter sender email id
        String fromUserPassword = "pwqo zsea tqgl epre";  //Enter sender gmail password , this will be authenticated by gmail smtp server
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent!!!");
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
