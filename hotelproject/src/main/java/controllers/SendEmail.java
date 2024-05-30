package controllers;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;


public class SendEmail
{

    Session newSession = null;
    MimeMessage mimeMessage = null;

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
//NOTE: if you want to send emails replace Email@gmail.com with the email you want to send to 
    public MimeMessage draftEmail(String Email , String message , String token) throws AddressException, MessagingException, IOException {
        String[] emailReceipients = {"Email@gmail.com",Email};  //Enter list of email recepients
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
    public boolean sendEmailWithAttachment(String email, String subject, String filePath) {
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(newSession);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("your email"));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject(subject);

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("This is message body");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            javax.activation.DataSource source = new FileDataSource(filePath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filePath);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully with attachment...");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }

    public void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties,null);
    }

}
