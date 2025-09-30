package com.portfolio.backend.controller;

import com.portfolio.backend.model.ContactMessage;
import com.portfolio.backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        List<ContactMessage> messages = contactService.getAllMessages();
        return ResponseEntity.ok(messages);
    }
    
    @GetMapping("/unread")
    public ResponseEntity<List<ContactMessage>> getUnreadMessages() {
        List<ContactMessage> messages = contactService.getUnreadMessages();
        return ResponseEntity.ok(messages);
    }
    
    @GetMapping("/unread-count")
    public ResponseEntity<Long> getUnreadMessageCount() {
        Long count = contactService.getUnreadMessageCount();
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ContactMessage> getMessageById(@PathVariable Long id) {
        Optional<ContactMessage> message = contactService.getMessageById(id);
        return message.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ContactMessage> createMessage(@RequestBody ContactMessage message) {
        ContactMessage savedMessage = contactService.saveMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }
    
    @PutMapping("/{id}/mark-read")
    public ResponseEntity<ContactMessage> markAsRead(@PathVariable Long id) {
        ContactMessage message = contactService.markAsRead(id);
        if (message != null) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        boolean deleted = contactService.deleteMessage(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
