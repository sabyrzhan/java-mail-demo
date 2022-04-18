package kz.sabyrzhan.javamaildemo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "false");
        prop.put("mail.smtp.host", "localhost");
        prop.put("mail.smtp.port", "1025");
        prop.put("mail.smtp.ssl.trust", "localhost");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("noreply@sabyrzhan.kz"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("customer001@gmail.com"));
        message.setSubject("Mail Subject");

        String msg = "<b>This is my first email using JavaMailer</b>";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(new File(Main.class.getClassLoader().getResource("dogecoin.png").toURI()));


        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        multipart.addBodyPart(attachment);

        message.setContent(multipart);

        Transport.send(message);
    }
}
