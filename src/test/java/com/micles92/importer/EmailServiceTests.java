package com.micles92.importer;

import com.micles92.importer.service.EmailService;
import com.micles92.importer.service.EmailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmailServiceTests {
    @Autowired
    EmailService emailService;

    @TestConfiguration
    static class EmailServiceTestContextConfiguration {

        @Autowired
        JavaMailSender javaMailSender;


        @Bean
        public EmailService emailService() {
            return new EmailServiceImpl();
        }

    }

    @Test
    public void testSendSimpleMessage() {
        emailService.sendSimpleMessage("micles92@gmail.com","JOJO","hehe");
    }
}
