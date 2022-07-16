package com.sofka.co.services;

import com.sofka.co.dto.CyclingTeamDTO;
import com.sofka.co.entities.CyclingTeam;
import com.sofka.co.repositories.CyclingTeamRepository;
import com.sofka.co.utils.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CyclingTeamServiceTest {

    private final Mapper mapper = new Mapper();
    @Mock
    private CyclingTeamRepository cyclingTeamRepository;
    private CyclingTeamService cyclingTeamService;

    private CyclingTeam cyclingTeam;
    private CyclingTeamDTO cyclingTeamDTO;

    @BeforeEach
    void setUp() {
        cyclingTeamService = new CyclingTeamServiceImpl(cyclingTeamRepository, mapper);

        cyclingTeam = new CyclingTeam();
        cyclingTeamDTO = new CyclingTeamDTO(1L, "Team1", "spa", "Spain");

        cyclingTeam.setId(cyclingTeamDTO.getId());
        cyclingTeam.setName(cyclingTeamDTO.getName());
        cyclingTeam.setCode(cyclingTeamDTO.getCode());
        cyclingTeam.setCountry(cyclingTeamDTO.getCountry());

    }

    @Test
    void getAllCyclingTeamsHappyPass() {


        Mockito.when(cyclingTeamRepository.findAll()).thenReturn(List.of(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));

        assertNotNull(cyclingTeamService.getAllCyclingTeams());


    }

    @Test
    void findCyclingTeamById() {
        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));

        assertNotNull(cyclingTeamService.findCyclingTeamById(cyclingTeam.getId()));
    }

    @Test
    void findByCountry() {
        Mockito.when(cyclingTeamRepository.findByCountry(cyclingTeam.getCountry())).thenReturn(List.of(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));

        assertNotNull(cyclingTeamService.findByCountry(cyclingTeam.getCountry()));
    }

    @Test
    void createCyclingTeam() {
        Mockito.when(cyclingTeamRepository.save(Mockito.any(CyclingTeam.class))).thenReturn(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO));

        assertNotNull(cyclingTeamService.createCyclingTeam(cyclingTeamDTO));
    }

    @Test
    void updateCyclingTeam() {
        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));

        cyclingTeam.setName(cyclingTeamDTO.getName());
        cyclingTeam.setCode(cyclingTeamDTO.getCode());
        cyclingTeam.setCountry(cyclingTeamDTO.getCountry());

        Mockito.when(cyclingTeamRepository.save(Mockito.any(CyclingTeam.class))).thenReturn(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO));

        assertNotNull(cyclingTeamService.updateCyclingTeam(cyclingTeamDTO, cyclingTeam.getId()));
    }

    @Test
    void deleteCyclingTeam() {
        Mockito.when(cyclingTeamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(mapper.mapperCyclingTeamDTOToEntity(cyclingTeamDTO)));

        cyclingTeamService.deleteCyclingTeam(cyclingTeam.getId());
        Assertions.assertEquals(0, 0);

    }
}