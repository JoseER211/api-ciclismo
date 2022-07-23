package com.sofka.co.controllers;

import com.sofka.co.dto.CyclistDTO;
import com.sofka.co.services.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CyclistController {

    @Autowired
    private CyclistService cyclistService;


    @GetMapping("/cyclingteams/{cyclingTeamId}/cyclists")
    public ResponseEntity<List<CyclistDTO>> getAllCyclistsByCyclingTeam(@PathVariable Long cyclingTeamId) {
        return new ResponseEntity<>(cyclistService.getAllCyclistsByCyclingTeam(cyclingTeamId), HttpStatus.OK);
    }

    @GetMapping("/cyclingteams/{cyclingTeamId}/cyclists/{cyclistId}")
    public ResponseEntity<CyclistDTO> getCyclistById(@PathVariable Long cyclingTeamId, @PathVariable Long cyclistId) {
        return new ResponseEntity<>(cyclistService.getCyclistById(cyclingTeamId, cyclistId), HttpStatus.OK);
    }

    @GetMapping("/cyclists/query")
    public ResponseEntity<List<CyclistDTO>> findByCountry(@RequestParam String country) {
        return new ResponseEntity<>(cyclistService.findByCountry(country), HttpStatus.OK);
    }

    @GetMapping("/cyclingteams/querycyclists")
    public ResponseEntity<List<CyclistDTO>> findByCyclingTeamCode(@RequestParam String cyclingTeamCode) {
        return new ResponseEntity<>(cyclistService.getAllCyclistsByCyclingTeamCode(cyclingTeamCode), HttpStatus.OK);
    }


    @PostMapping("/cyclingteams/{cyclingTeamId}/cyclists")
    public ResponseEntity<CyclistDTO> createCyclist(@PathVariable Long cyclingTeamId, @Valid @RequestBody CyclistDTO cyclistDTO) {
        return new ResponseEntity<>(cyclistService.createCyclist(cyclingTeamId, cyclistDTO), HttpStatus.CREATED);
    }

    @PutMapping("/cyclingteams/{cyclingTeamId}/cyclists/{cyclistId}")
    public ResponseEntity<CyclistDTO> updateCyclist(@PathVariable Long cyclingTeamId, @PathVariable Long cyclistId, @Valid @RequestBody CyclistDTO cyclistDTO) {
        return new ResponseEntity<>(cyclistService.updateCyclist(cyclingTeamId, cyclistId, cyclistDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/cyclingteams/{cyclingTeamId}/cyclists/{cyclistId}")
    public ResponseEntity<String> deleteCyclist(@PathVariable Long cyclingTeamId, @PathVariable Long cyclistId) {
        cyclistService.deleteCyclist(cyclingTeamId, cyclistId);
        return new ResponseEntity<>("Ciclista eliminado", HttpStatus.OK);
    }
}
