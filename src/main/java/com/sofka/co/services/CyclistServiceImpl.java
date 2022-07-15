package com.sofka.co.services;

import com.sofka.co.dto.CyclistDTO;
import com.sofka.co.entities.CyclingTeam;
import com.sofka.co.entities.Cyclist;
import com.sofka.co.exceptions.NotEqualsException;
import com.sofka.co.exceptions.ResourceNotFoundException;
import com.sofka.co.repositories.CyclingTeamRepository;
import com.sofka.co.repositories.CyclistRepository;
import com.sofka.co.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CyclistServiceImpl implements CyclistService {


    @Autowired
    private CyclistRepository cyclistRepository;

    @Autowired
    private CyclingTeamRepository cyclingTeamRepository;

    @Autowired
    private Mapper mapper;


    @Override
    public List<CyclistDTO> getAllCyclistsByCyclingTeam(Long cyclingTeamId) {
        List<Cyclist> cyclists = cyclistRepository.findByCyclingTeamId(cyclingTeamId);
        return cyclists.stream().map(cyclist -> mapper.mapperCyclistToDTO(cyclist)).collect(Collectors.toList());

    }

    @Override
    public List<CyclistDTO> getAllCyclistsByCyclingTeamCode(String cyclingTeamCode) {
        List<Cyclist> cyclists = cyclistRepository.findByCyclingTeamCode(cyclingTeamCode);
        return cyclists.stream().map(cyclist -> mapper.mapperCyclistToDTO(cyclist)).collect(Collectors.toList());
    }

    @Override
    public CyclistDTO getCyclistById(Long cyclingTeamId, Long cyclistId) {
        CyclingTeam cyclingTeam = cyclingTeamRepository.findById(cyclingTeamId).orElseThrow(() -> new ResourceNotFoundException("Equipo", "id", cyclingTeamId));

        Cyclist cyclist = cyclistRepository.findById(cyclistId).orElseThrow(() -> new ResourceNotFoundException("Ciclista", "id", cyclistId));

        if(!cyclist.getCyclingTeam().getId().equals(cyclingTeam.getId())){
            throw new NotEqualsException(HttpStatus.BAD_REQUEST, "El ciclista no pertenece al equipo");
        }
        return mapper.mapperCyclistToDTO(cyclist);
    }

    @Override
    public List<CyclistDTO> findByCountry(String country) {
        List<Cyclist> cyclists = cyclistRepository.findByCountry(country);
        return cyclists.stream().map(cyclist -> mapper.mapperCyclistToDTO(cyclist)).collect(Collectors.toList());
    }

    @Override
    public CyclistDTO createCyclist(Long cyclingTeamId, CyclistDTO cyclistDTO) {
        Cyclist cyclist = mapper.mapperCyclistDTOToEntity(cyclistDTO);
        CyclingTeam cyclingTeam = cyclingTeamRepository.findById(cyclingTeamId).orElseThrow(() -> new ResourceNotFoundException("Equipo", "id", cyclingTeamId));

        cyclist.setCyclingTeam(cyclingTeam);
        Cyclist newCyclist = cyclistRepository.save(cyclist);
        return mapper.mapperCyclistToDTO(newCyclist);
    }

    @Override
    public CyclistDTO updateCyclist(Long cyclingTeamId, Long cyclistId, CyclistDTO cyclistDTO) {
        CyclingTeam cyclingTeam = cyclingTeamRepository.findById(cyclingTeamId).orElseThrow(() -> new ResourceNotFoundException("Equipo", "id", cyclingTeamId));

        Cyclist cyclist = cyclistRepository.findById(cyclistId).orElseThrow(() -> new ResourceNotFoundException("Ciclista", "id", cyclistId));

        if(!cyclist.getCyclingTeam().getId().equals(cyclingTeam.getId())){
            throw new NotEqualsException(HttpStatus.BAD_REQUEST, "El ciclista no pertenece al equipo");
        }

        cyclist.setFullName(cyclistDTO.getFullName());
        cyclist.setNumber(cyclistDTO.getNumber());
        cyclist.setCountry(cyclistDTO.getCountry());

        Cyclist newCyclist = cyclistRepository.save(cyclist);
        return mapper.mapperCyclistToDTO(newCyclist);
    }

    @Override
    public void deleteCyclist(Long cyclingTeamId, Long cyclistId) {
        CyclingTeam cyclingTeam = cyclingTeamRepository.findById(cyclingTeamId).orElseThrow(() -> new ResourceNotFoundException("Equipo", "id", cyclingTeamId));

        Cyclist cyclist = cyclistRepository.findById(cyclistId).orElseThrow(() -> new ResourceNotFoundException("Ciclista", "id", cyclistId));

        if(!cyclist.getCyclingTeam().getId().equals(cyclingTeam.getId())){
            throw new NotEqualsException(HttpStatus.BAD_REQUEST, "El ciclista no pertenece al equipo");
        }
        cyclistRepository.delete(cyclist);

    }
}

