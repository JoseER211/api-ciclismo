package com.sofka.co.controllers;

import com.sofka.co.dto.CyclingTeamDTO;
import com.sofka.co.services.CyclingTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cyclingteams")
public class CyclingTeamController {

    @Autowired
    private CyclingTeamService cyclingTeamService;

    @GetMapping
    public ResponseEntity<List<CyclingTeamDTO>> getAllCyclingTeams() {
        return new ResponseEntity<>(cyclingTeamService.getAllCyclingTeams(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CyclingTeamDTO> findCyclingTeamById(@PathVariable Long id) {
        return new ResponseEntity<>(cyclingTeamService.findCyclingTeamById(id), HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<List<CyclingTeamDTO>> findByCountry(@RequestParam String country) {
        return new ResponseEntity<>(cyclingTeamService.findByCountry(country), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CyclingTeamDTO> createCyclingTeam(@Valid @RequestBody CyclingTeamDTO cyclingTeamDTO) {
        return new ResponseEntity<>(cyclingTeamService.createCyclingTeam(cyclingTeamDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CyclingTeamDTO> updateCyclingTeam(@Valid @RequestBody CyclingTeamDTO cyclingTeamDTO, @PathVariable Long id) {
        return new ResponseEntity<>(cyclingTeamService.updateCyclingTeam(cyclingTeamDTO, id), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCyclingTeam(@PathVariable Long id) {
        cyclingTeamService.deleteCyclingTeam(id);
        return new ResponseEntity<>("Equipo eliminado", HttpStatus.OK);
    }
}
