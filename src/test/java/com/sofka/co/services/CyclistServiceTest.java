package com.sofka.co.services;

import com.sofka.co.dto.CyclingTeamDTO;
import com.sofka.co.dto.CyclistDTO;
import com.sofka.co.entities.CyclingTeam;
import com.sofka.co.entities.Cyclist;
import com.sofka.co.exceptions.NotEqualsException;
import com.sofka.co.repositories.CyclingTeamRepository;
import com.sofka.co.repositories.CyclistRepository;
import com.sofka.co.utils.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CyclistServiceTest {

    @Mock
    private CyclistRepository cyclistRepository;

    @Mock
    private CyclingTeamRepository cyclingTeamRepository;

    private CyclistService cyclistService;

    private final Mapper mapper = new Mapper();

    private CyclingTeam cyclingTeam;
    private CyclingTeamDTO cyclingTeamDTO;

    private CyclingTeam cyclingTeam2;
    private CyclingTeamDTO cyclingTeamDTO2;
    private Cyclist cyclist;
    private CyclistDTO cyclistDTO;



    @BeforeEach
    void setUp() {
        cyclistService = new CyclistServiceImpl(cyclistRepository, cyclingTeamRepository, mapper);

        cyclingTeam = new CyclingTeam();
        cyclingTeamDTO = new CyclingTeamDTO(1L, "Team1", "spa", "Spain", new HashSet<>());

        cyclingTeam.setId(cyclingTeamDTO.getId());
        cyclingTeam.setName(cyclingTeamDTO.getName());
        cyclingTeam.setCode(cyclingTeamDTO.getCode());
        cyclingTeam.setCountry(cyclingTeamDTO.getCountry());
        cyclingTeam.setCyclists(cyclingTeamDTO.getCyclists());

        cyclingTeam2 = new CyclingTeam();
        cyclingTeamDTO2 = new CyclingTeamDTO(2L, "Team2", "sp1", "Spain", new HashSet<>());

        cyclingTeam2.setId(cyclingTeamDTO2.getId());
        cyclingTeam2.setName(cyclingTeamDTO2.getName());
        cyclingTeam2.setCode(cyclingTeamDTO2.getCode());
        cyclingTeam2.setCountry(cyclingTeamDTO2.getCountry());
        cyclingTeam2.setCyclists(cyclingTeamDTO2.getCyclists());


        cyclist = new Cyclist();
        cyclistDTO = new CyclistDTO(1L, "Cyclist1", 01, "Spain");

        cyclist.setId(cyclistDTO.getId());
        cyclist.setFullName(cyclistDTO.getFullName());
        cyclist.setNumber(cyclistDTO.getNumber());
        cyclist.setCountry(cyclistDTO.getCountry());
        cyclist.setCyclingTeam(cyclingTeam);

    }

    @Test
    void getAllCyclistsByCyclingTeamHapyyPass() {
        Mockito.when(cyclistRepository.findByCyclingTeamId(cyclingTeam.getId())).thenReturn(List.of(mapper.mapperCyclistDTOToEntity(cyclistDTO)));
        assertNotNull(cyclistService.getAllCyclistsByCyclingTeam(cyclingTeam.getId()));

    }

    @Test
    void getAllCyclistsByCyclingTeamCodeHappyPass() {
        Mockito.when(cyclistRepository.findByCyclingTeamCode(cyclingTeam.getCode())).thenReturn(List.of(mapper.mapperCyclistDTOToEntity(cyclistDTO)));
        assertNotNull(cyclistService.getAllCyclistsByCyclingTeamCode(cyclingTeam.getCode()));

    }

    @Test
    void getCyclistByIdHappyPass() {
        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));
        Mockito.when(cyclistRepository.findById(cyclist.getId())).thenReturn(Optional.ofNullable(cyclist));

        assertNotNull(cyclistService.getCyclistById(cyclingTeam.getId(), cyclist.getId()));


    }
    @Test
    void getCyclistByIdUnhappyPass(){

        cyclist.setCyclingTeam(cyclingTeam2);

        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));
        Mockito.when(cyclistRepository.findById(cyclist.getId())).thenReturn(Optional.ofNullable(cyclist));

        assertThrows(NotEqualsException.class, () -> cyclistService.getCyclistById(cyclingTeam.getId(), cyclist.getId()));

    }

    @Test
    void findByCountryHappyPass() {
        Mockito.when(cyclistRepository.findByCountry(cyclist.getCountry())).thenReturn(List.of(mapper.mapperCyclistDTOToEntity(cyclistDTO)));
        assertNotNull(cyclistService.findByCountry(cyclist.getCountry()));

    }

    @Test
    void createCyclistHappyPass() {
        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));
        Mockito.when(cyclistRepository.save(Mockito.any(Cyclist.class))).thenReturn(mapper.mapperCyclistDTOToEntity(cyclistDTO));
        assertNotNull(cyclistService.createCyclist(cyclingTeam.getId(), cyclistDTO));

    }

    @Test
    void createCyclistUnhappyPass(){

        Set<Cyclist> cyclists = new HashSet<>();
        cyclists.add(new Cyclist());
        cyclists.add(new Cyclist());
        cyclists.add(new Cyclist());
        cyclists.add(new Cyclist());
        cyclists.add(new Cyclist());
        cyclists.add(new Cyclist());
        cyclists.add(new Cyclist());
        cyclists.add(new Cyclist());

        cyclingTeam.setCyclists(cyclists);
        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(cyclingTeam));
        assertThrows(RuntimeException.class, () -> cyclistService.createCyclist(cyclingTeam.getId(), cyclistDTO));

    }


    @Test
    void updateCyclistHappyPass() {
        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));
        Mockito.when(cyclistRepository.findById(cyclist.getId())).thenReturn(Optional.ofNullable(cyclist));
        Mockito.when(cyclistRepository.save(Mockito.any(Cyclist.class))).thenReturn(mapper.mapperCyclistDTOToEntity(cyclistDTO));
        assertNotNull(cyclistService.updateCyclist(cyclingTeam.getId(), cyclist.getId(), cyclistDTO));

    }

    @Test
    void updateCyclistUnhappyPass(){
        cyclist.setCyclingTeam(cyclingTeam2);
        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));
        Mockito.when(cyclistRepository.findById(cyclist.getId())).thenReturn(Optional.ofNullable(cyclist));
        assertThrows(NotEqualsException.class, () -> cyclistService.updateCyclist(cyclingTeam.getId(), cyclist.getId(), cyclistDTO));
    }

    @Test
    void deleteCyclistHappyPass() {
        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));
        Mockito.when(cyclistRepository.findById(cyclist.getId())).thenReturn(Optional.ofNullable(cyclist));
        cyclistService.deleteCyclist(cyclingTeam.getId(), cyclist.getId());

        Assertions.assertEquals(0, 0);

    }
    @Test
    void deleteCyclistUnHappyPass(){
        cyclist.setCyclingTeam(cyclingTeam2);
        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));
        Mockito.when(cyclistRepository.findById(cyclist.getId())).thenReturn(Optional.ofNullable(cyclist));


        assertThrows(NotEqualsException.class, () -> cyclistService.deleteCyclist(cyclingTeam.getId(), cyclist.getId()));

    }
}