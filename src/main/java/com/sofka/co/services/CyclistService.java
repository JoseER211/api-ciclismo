package com.sofka.co.services;

import com.sofka.co.dto.CyclistDTO;

import java.util.List;

public interface CyclistService {


    public List<CyclistDTO> getAllCyclistsByCyclingTeam(Long cyclingTeamId);

    public List<CyclistDTO> getAllCyclistsByCyclingTeamCode(String cyclingTeamCode);

    public CyclistDTO getCyclistById(Long cyclingTeamId, Long cyclistId);

    public List<CyclistDTO> findByCountry(String country);
    public CyclistDTO createCyclist(Long cyclingTeamId, CyclistDTO cyclistDTO);

    public CyclistDTO updateCyclist(Long cyclingTeamId, Long cyclistId, CyclistDTO cyclistDTO);

    public void deleteCyclist(Long cyclingTeamId, Long cyclistId);
}
