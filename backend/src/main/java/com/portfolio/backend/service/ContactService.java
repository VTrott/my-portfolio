package com.portfolio.backend.service;

import com.portfolio.backend.model.ContactMessage;
import com.portfolio.backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    
    @Autowired
    private ContactMessageRepository contactMessageRepository;
    
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
}
