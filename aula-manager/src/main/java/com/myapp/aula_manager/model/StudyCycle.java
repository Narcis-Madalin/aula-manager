package com.myapp.aula_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "study_cycle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cycle_id")
    private Integer cycleId;

    @Column(name = "cycle_name", nullable = false, unique = true)
    private String cycleName;
}
