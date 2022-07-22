package com.sofka.co.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cyclist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cyclist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "number", nullable = false, unique = true, length = 3)
    private Integer number;

    @Column(name = "country", nullable = false)
    private String country;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cycling_team_id", nullable = false)
    private CyclingTeam cyclingTeam;


}
