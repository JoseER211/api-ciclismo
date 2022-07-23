package com.sofka.co.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cycling_teams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CyclingTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", length = 3, unique = true, nullable = false)
    private String code;

    @Column(name = "country", nullable = false)
    private String country;


    @OneToMany(mappedBy = "cyclingTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cyclist> cyclists = new HashSet<>();


}


