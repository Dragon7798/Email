package com.example.email.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

@Service
public class MailService {


    private final JavaMailSenderImpl mailSender = mailSender();
    private final String from = "chetan.dabholkar@teknopoint.in";
    private final String to = "cdabholkar13@gmail.com";

    public void send() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("This is a plain text email");
        message.setText("Hello guys! This is a plain text email.");

        mailSender.send(message);
    }


    public void htmlEmail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("This is an HTML email");
        helper.setFrom(from);
        helper.setTo(to);

        boolean html = true;
        helper.setText("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "  <style>\n" + "    /* Internal CSS */\n" + "    body {\n" + "      font-family: 'Arial', sans-serif;\n" + "      background: #f2f2f2;\n" + "    }\n" + "    form {\n" + "      max-width: 400px;\n" + "      margin: 0 auto;\n" + "      padding: 20px;\n" + "      background: #ffffff;\n" + "      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n" + "    }\n" + "    h2 {\n" + "      color: #333;\n" + "    }\n" + "    label {\n" + "      display: block;\n" + "      margin-bottom: 8px;\n" + "      color: #007BFF;\n" + "    }\n" + "    input[type=\"text\"],\n" + "    input[type=\"email\"] {\n" + "      width: 100%;\n" + "      padding: 10px;\n" + "      margin-bottom: 10px;\n" + "      border: 1px solid #ccc;\n" + "      border-radius: 3px;\n" + "    }\n" + "    input[type=\"submit\"] {\n" + "      background: #007BFF;\n" + "      color: #fff;\n" + "      padding: 12px 20px;\n" + "      border: none;\n" + "      border-radius: 3px;\n" + "      cursor: pointer;\n" + "    }\n" + "    input[type=\"submit\"]:hover {\n" + "      background: #0056b3;\n" + "    }\n" + "  </style>\n" + "</head>\n" + "<body>\n" + "  <form>\n" + "    <h2>Contact Us</h2>\n" + "    <label for=\"name\">Name:</label>\n" + "    <input type=\"text\" id=\"name\" name=\"name\" required>\n" + "    <label for=\"email\">Email:</label>\n" + "    <input type=\"email\" id=\"email\" name=\"email\" required>\n" + "    <input type=\"submit\" value=\"Submit\">\n" + "  </form>\n" + "</body>\n" + "</html>\n", html);

        mailSender.send(message);
    }

    public void  mailWithAttachment() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Here's your e-book");
        helper.setFrom(from);
        helper.setTo(to);

        helper.setText("<b>Dear friend</b>,<br><i>Please find the book attached.</i>", true);

        FileSystemResource file = new FileSystemResource(new File("D:\\output.pdf"));
        helper.addAttachment("FreelanceSuccess.csv", file);

        mailSender.send(message);

    }


    public void mailWithImage() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Here's your pic");
        helper.setFrom(from);
        helper.setTo(to);

        String content = "<b>Dear guru</b>,<br><i>Please look at this nice picture:.</i>"
                + "<br><img src='cid:image001'/><br><b>Best Regards</b>";
        helper.setText(content, true);

        FileSystemResource resource = new FileSystemResource(new File("D:\\564835.jpg"));
        helper.addInline("image001", resource);

        mailSender.send(message);
    }

    private JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("chetan.dabholkar@teknopoint.in");
        mailSender.setPassword("hukzeiyeiodhzwqg");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }
}
