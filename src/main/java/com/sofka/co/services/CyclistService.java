package com.sofka.co.services;

import com.sofka.co.dto.CyclistDTO;

import java.util.List;

public interface CyclistService {

    public List<CyclistDTO> getAllCyclistsByCyclingTeam(Long cyclingTeamId);

    public CyclistDTO getCyclistById(Long cyclingTeamId, Long cyclistId);
    public CyclistDTO createCyclist(Long cyclingTeamId, CyclistDTO cyclistDTO);


}
