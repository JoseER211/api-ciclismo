package com.sofka.co.services;

import com.sofka.co.dto.CyclingTeamDTO;

import java.util.List;

public interface CyclingTeamService {

    public List<CyclingTeamDTO> getAllCyclingTeams();

    public CyclingTeamDTO findCyclingTeamById(Long id);

    public List<CyclingTeamDTO> findByCountry(String country);

    public CyclingTeamDTO createCyclingTeam(CyclingTeamDTO cyclingTeamDTO);

    public CyclingTeamDTO updateCyclingTeam(CyclingTeamDTO cyclingTeamDTO, Long id);

    public void deleteCyclingTeam(Long id);




}
