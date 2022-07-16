package com.sofka.co.services;

import com.sofka.co.dto.CyclingTeamDTO;
import com.sofka.co.entities.CyclingTeam;
import com.sofka.co.exceptions.ResourceNotFoundException;
import com.sofka.co.repositories.CyclingTeamRepository;
import com.sofka.co.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CyclingTeamServiceImpl implements CyclingTeamService {

    @Autowired
    private final CyclingTeamRepository cyclingTeamRepository;

    @Autowired
    private final Mapper mapper;

    public CyclingTeamServiceImpl(CyclingTeamRepository cyclingTeamRepository, Mapper mapper) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CyclingTeamDTO> getAllCyclingTeams() {
        List<CyclingTeam> cyclingTeams = cyclingTeamRepository.findAll();
        return cyclingTeams.stream().map(mapper::mapperCyclingTeamToDTO).collect(Collectors.toList());
    }

    @Override
    public CyclingTeamDTO findCyclingTeamById(Long id) {
        CyclingTeam cyclingTeam = cyclingTeamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipo", "id", id));
        return mapper.mapperCyclingTeamToDTO(cyclingTeam);
    }

    @Override
    public List<CyclingTeamDTO> findByCountry(String country) {
        List<CyclingTeam> cyclingTeams = cyclingTeamRepository.findByCountry(country);
        return cyclingTeams.stream().map(mapper::mapperCyclingTeamToDTO).collect(Collectors.toList());
    }

    @Override
    public CyclingTeamDTO createCyclingTeam(CyclingTeamDTO cyclingTeamDTO) {
        CyclingTeam cyclingTeam = mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO);

        CyclingTeam newCyclingTeam = cyclingTeamRepository.save(cyclingTeam);

        return mapper.mapperCyclingTeamToDTO(newCyclingTeam);


    }

    @Override
    public CyclingTeamDTO updateCyclingTeam(CyclingTeamDTO cyclingTeamDTO, Long id) {
        CyclingTeam cyclingTeam = cyclingTeamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipo", "id", id));

        cyclingTeam.setName(cyclingTeamDTO.getName());
        cyclingTeam.setCode(cyclingTeamDTO.getCode());
        cyclingTeam.setCountry(cyclingTeamDTO.getCountry());

        CyclingTeam updatedCyclingTeam = cyclingTeamRepository.save(cyclingTeam);

        return mapper.mapperCyclingTeamToDTO(updatedCyclingTeam);
    }

    @Override
    public void deleteCyclingTeam(Long id) {
        CyclingTeam cyclingTeam = cyclingTeamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipo", "id", id));
        cyclingTeamRepository.delete(cyclingTeam);
    }


}

