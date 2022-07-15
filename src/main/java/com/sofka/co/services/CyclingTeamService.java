package com.sofka.co.services;

import com.sofka.co.dto.CyclingTeamDTO;

import java.util.List;

public interface CyclingTeamService {

    List<CyclingTeamDTO> getAllCyclingTeams();

    CyclingTeamDTO findCyclingTeamById(Long id);

    List<CyclingTeamDTO> findByCountry(String country);

    CyclingTeamDTO createCyclingTeam(CyclingTeamDTO cyclingTeamDTO);

    CyclingTeamDTO updateCyclingTeam(CyclingTeamDTO cyclingTeamDTO, Long id);

    void deleteCyclingTeam(Long id);


}
