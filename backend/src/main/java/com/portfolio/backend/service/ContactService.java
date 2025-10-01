package com.portfolio.backend.service;

import com.portfolio.backend.model.ContactMessage;
import com.portfolio.backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    
    @Autowired
    private ContactMessageRepository contactMessageRepository;
    
    @Autowired
    private JavaMailSender mailSender;
    
    private static final String ADMIN_EMAIL = "victoriatrotter95@gmail.com";
    
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAllByOrderByCreatedAtDesc();
    }
    
    public List<ContactMessage> getUnreadMessages() {
        return contactMessageRepository.findByIsReadFalseOrderByCreatedAtDesc();
    }
    
    public Long getUnreadMessageCount() {
        return contactMessageRepository.countByIsReadFalse();
    }
    
    public Optional<ContactMessage> getMessageById(Long id) {
        return contactMessageRepository.findById(id);
    }
    
    public ContactMessage saveMessage(ContactMessage message) {
        return contactMessageRepository.save(message);
    }
    
    public ContactMessage markAsRead(Long id) {
        Optional<ContactMessage> optionalMessage = contactMessageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            ContactMessage message = optionalMessage.get();
            message.setIsRead(true);
            return contactMessageRepository.save(message);
        }
        return null;
    }
    
    public boolean deleteMessage(Long id) {
        if (contactMessageRepository.existsById(id)) {
            contactMessageRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public ContactMessage saveMessageAndSendEmail(ContactMessage message) {
        ContactMessage savedMessage = contactMessageRepository.save(message);
        
        try {
            sendEmailNotification(savedMessage);
        } catch (Exception e) {
            System.err.println("Failed to send email notification: " + e.getMessage());
        }
        
        return savedMessage;
    }
    
    private void sendEmailNotification(ContactMessage message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(ADMIN_EMAIL);
        email.setSubject("New Contact Form Message from " + message.getName());
        email.setText(buildEmailContent(message));
        
        mailSender.send(email);
    }
    
    private String buildEmailContent(ContactMessage message) {
        StringBuilder content = new StringBuilder();
        content.append("New contact form submission:\n\n");
        content.append("Name: ").append(message.getName()).append("\n");
        content.append("Email: ").append(message.getEmail()).append("\n");
        content.append("Subject: ").append(message.getSubject()).append("\n");
        content.append("Message:\n").append(message.getMessage()).append("\n\n");
        content.append("Submitted at: ").append(message.getCreatedAt()).append("\n");
        return content.toString();
    }
}
