package com.esgitech.fsapp.controllers;

import com.esgitech.fsapp.exceptions.SportNotFoundException;
import com.esgitech.fsapp.model.Sport;
import com.esgitech.fsapp.services.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/sports")
public class SportController {

    @Autowired
    private SportService sportService;

    @GetMapping("/{name}")
    public Sport getSportByName(@PathVariable String name) {

        try {
            return sportService.getSport(name);
        } catch (Exception e) {
            throw new SportNotFoundException("SPORT NOT FOUND" + e.getMessage());
        }
    }

    @GetMapping
    public List<Sport> getSports() {
        try {
            return sportService.getSports();
        } catch (Exception e) {
            throw new SportNotFoundException("NO DATA IN SPORTS TABLE" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Sport> addSport(Sport sport) {
        return ResponseEntity.ok(sportService.addSport(sport));
    }

    @PutMapping()
    public ResponseEntity<Sport> updateSport(@RequestBody Sport sport) {
        return ResponseEntity.ok(sportService.updateSport(sport));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity deleteSport(@PathVariable String name) {
        try {
            sportService.deleteSport(name);
        } catch (Exception e) {
            throw new SportNotFoundException("SPORT NOT FOUND" + e.getMessage());
        }
        return (ResponseEntity) ResponseEntity.ok();
    }
}
