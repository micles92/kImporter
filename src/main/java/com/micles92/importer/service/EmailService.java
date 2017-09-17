package com.micles92.importer.service;


import com.micles92.importer.model.context.FileContext;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import java.io.IOException;

public interface EmailService {
     void sendSimpleMessage(String to, String subject, String text);
     void  sendMessageWithAttachment(FileContext fileContext) throws MessagingException, javax.mail.MessagingException, IOException;
}
