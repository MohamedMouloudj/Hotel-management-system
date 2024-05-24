package  controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QrCode {
    public  void SendQrCode(String username , String email , String phoneNumber )throws MessagingException, IOException {
            // User information
//        String username = "Hermez rahim";
//        String email = "hermezabderrahim568@gmail.com";
//        String phoneNumber = "+1234567890";

        // Encode user information into a string
        String userInfo = "Username: " + username + "\nEmail: " + email + "\nPhone: " + phoneNumber;

        // Path to save the QR code image
        String filePath = "user_qr_code.png";

        // Generate QR code that has these informations
        generateQRCode(username, email, phoneNumber, filePath);
        try{
            System.out.println("The QR code has been saved to: " + filePath);
            SendEmail emailService = new SendEmail();
            emailService.setupServerProperties();
            emailService.draftEmail(email , "Here is your QR code: Please present this QR code when checking in at the hotel." + "\n" + "Thank you!" + "\n" + "Best regards," + "\n" + "Hotel Management" + "\n" , "111");
            emailService.sendEmail(email);
        }catch (MessagingException e){
            e.printStackTrace();

        }

    }

    private void generateQRCode(String username, String email, String phoneNumber, String filePath) {
        int width = 350;
        int height = 350;
        String format = "png";

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        // Encode user information into a vCard
        String vCard = "BEGIN:VCARD\n"
                + "VERSION:3.0\n"
                + "N:" + username + "\n"
                + "EMAIL:" + email + "\n"
                + "TEL:" + phoneNumber + "\n"
                + "END:VCARD";

        try {

            QRCodeWriter writer = new QRCodeWriter();


            BitMatrix matrix = writer.encode(vCard, BarcodeFormat.QR_CODE, width, height, hints);// Encode the vCard into a bitMatrix

            // Create the QR code image
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(matrix, format, path);

            System.out.println("QR code generated successfully.");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}

