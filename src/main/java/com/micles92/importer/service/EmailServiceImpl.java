package com.micles92.importer.service;

import com.micles92.importer.model.context.FileContext;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    public static final String ADMINISTRATOR_MAIL = "testImporterJava@gmail.com";
    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(FileContext fileContext) throws MessagingException, javax.mail.MessagingException {
        List<String> failedFiles = fileContext.getFailedReplaced();


        try (PrintWriter writer = new PrintWriter(new FileWriter(new File("import_error.txt"), true))) {
            for (String file : failedFiles) {
                writer.write(file);
            }

            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(ADMINISTRATOR_MAIL);
            helper.setSubject("import error");
            helper.setText("Import error. file:" + fileContext.getInput().toString() + " target:" + fileContext.getDestination().toString());

            FileSystemResource file
                    = new FileSystemResource("import_error.txt");
            helper.addAttachment("import_error.txt", file);

            emailSender.send(message);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
