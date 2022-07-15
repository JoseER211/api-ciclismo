package com.sofka.co.repositories;

import com.sofka.co.entities.CyclingTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CyclingTeamRepository extends JpaRepository<CyclingTeam, Long> {

    List<CyclingTeam> findByCountry(String country);

}

