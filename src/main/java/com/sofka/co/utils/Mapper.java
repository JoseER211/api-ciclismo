package com.sofka.co.utils;

import com.sofka.co.dto.CyclingTeamDTO;
import com.sofka.co.dto.CyclistDTO;
import com.sofka.co.entities.CyclingTeam;
import com.sofka.co.entities.Cyclist;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Mapper {

    public CyclingTeamDTO mapperCyclingTeamToDTO(CyclingTeam cyclingTeam) {
        CyclingTeamDTO cyclingTeamDTO = new CyclingTeamDTO();

        cyclingTeamDTO.setId(cyclingTeam.getId());
        cyclingTeamDTO.setName(cyclingTeam.getName());
        cyclingTeamDTO.setCode(cyclingTeam.getCode());
        cyclingTeamDTO.setCountry(cyclingTeam.getCountry());

        return cyclingTeamDTO;

    }

    public CyclingTeam mapperCyclingTeamDTOToEntity(CyclingTeamDTO cyclingTeamDTO) {
        CyclingTeam cyclingTeam = new CyclingTeam();

        cyclingTeam.setId(cyclingTeamDTO.getId());
        cyclingTeam.setName(cyclingTeamDTO.getName());
        cyclingTeam.setCode(cyclingTeamDTO.getCode());
        cyclingTeam.setCountry(cyclingTeamDTO.getCountry());

        return cyclingTeam;

    }

    public CyclistDTO mapperCyclistToDTO(Cyclist cyclist) {
        CyclistDTO cyclistDTO = new CyclistDTO();

        cyclistDTO.setId(cyclist.getId());
        cyclistDTO.setFullName(cyclist.getFullName());
        cyclistDTO.setNumber(cyclist.getNumber());
        cyclistDTO.setCountry(cyclist.getCountry());

        return cyclistDTO;
    }

    public Cyclist mapperCyclistDTOToEntity(CyclistDTO cyclistDTO) {
        Cyclist cyclist = new Cyclist();

        cyclist.setId(cyclistDTO.getId());
        cyclist.setFullName(cyclistDTO.getFullName());
        cyclist.setNumber(cyclistDTO.getNumber());
        cyclist.setCountry(cyclistDTO.getCountry());

        return cyclist;
    }

}
