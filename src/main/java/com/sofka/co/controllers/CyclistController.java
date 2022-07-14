package com.sofka.co.controllers;

import com.sofka.co.dto.CyclistDTO;
import com.sofka.co.services.CyclistService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cyclingteams")
public class CyclistController {

    @Autowired
    private CyclistService cyclistService;

    @GetMapping("/{cyclingTeamId}/cyclists")
    public ResponseEntity<List<CyclistDTO>> getAllCyclistsByCyclingTeam(@PathVariable Long cyclingTeamId) {
        return new ResponseEntity<>(cyclistService.getAllCyclistsByCyclingTeam(cyclingTeamId), HttpStatus.OK);
    }

    @GetMapping("/{cyclingTeamId}/cyclists/{cyclistId}")
    public ResponseEntity<CyclistDTO> getCyclistById(@PathVariable Long cyclingTeamId, @PathVariable Long cyclistId) {
        return new ResponseEntity<>(cyclistService.getCyclistById(cyclingTeamId, cyclistId), HttpStatus.OK);
    }

    @PostMapping("/{cyclingTeamId}/cyclists")
    public ResponseEntity<CyclistDTO> createCyclist(@PathVariable Long cyclingTeamId, @RequestBody CyclistDTO cyclistDTO) {
        return new ResponseEntity<>(cyclistService.createCyclist(cyclingTeamId, cyclistDTO), HttpStatus.CREATED);
    }
}
