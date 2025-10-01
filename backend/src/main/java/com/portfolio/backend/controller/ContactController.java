package com.portfolio.backend.controller;

import com.portfolio.backend.model.ContactMessage;
import com.portfolio.backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
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
        ContactMessage savedMessage = contactService.saveMessageAndSendEmail(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }
    
    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody ContactMessage message) {
        try {
            ContactMessage savedMessage = contactService.saveMessageAndSendEmail(message);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Message sent successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Failed to send message. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
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
