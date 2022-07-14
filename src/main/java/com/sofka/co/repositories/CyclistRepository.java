package com.sofka.co.repositories;

import com.sofka.co.entities.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CyclistRepository extends JpaRepository<Cyclist, Long> {

    public List<Cyclist> findByCyclingTeamId(Long cyclingTeamId);

}

