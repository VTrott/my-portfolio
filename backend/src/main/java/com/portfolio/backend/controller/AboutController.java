package com.portfolio.backend.controller;

import com.portfolio.backend.model.About;
import com.portfolio.backend.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/about")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AboutController {
    
    @Autowired
    private AboutService aboutService;
    
    @GetMapping
    public ResponseEntity<About> getAbout() {
        Optional<About> about = aboutService.getAbout();
        return about.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<About> createAbout(@RequestBody About about) {
        About savedAbout = aboutService.saveAbout(about);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAbout);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<About> updateAbout(@PathVariable Long id, @RequestBody About aboutDetails) {
        About updatedAbout = aboutService.updateAbout(id, aboutDetails);
        if (updatedAbout != null) {
            return ResponseEntity.ok(updatedAbout);
        }
        return ResponseEntity.notFound().build();
    }
}
