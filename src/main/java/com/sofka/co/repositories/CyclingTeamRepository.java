package com.sofka.co.repositories;

import com.sofka.co.entities.CyclingTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CyclingTeamRepository extends JpaRepository<CyclingTeam, Long> {

}

