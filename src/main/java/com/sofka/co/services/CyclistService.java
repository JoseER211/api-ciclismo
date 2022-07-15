package com.sofka.co.services;

import com.sofka.co.dto.CyclistDTO;

import java.util.List;

public interface CyclistService {


    List<CyclistDTO> getAllCyclistsByCyclingTeam(Long cyclingTeamId);

    List<CyclistDTO> getAllCyclistsByCyclingTeamCode(String cyclingTeamCode);

    CyclistDTO getCyclistById(Long cyclingTeamId, Long cyclistId);

    List<CyclistDTO> findByCountry(String country);

    CyclistDTO createCyclist(Long cyclingTeamId, CyclistDTO cyclistDTO);

    CyclistDTO updateCyclist(Long cyclingTeamId, Long cyclistId, CyclistDTO cyclistDTO);

    void deleteCyclist(Long cyclingTeamId, Long cyclistId);
}
