package com.myapp.aula_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "specialization", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cycle_id", "specialization_name"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id")
    private Integer specializationId;

    @Column(name = "specialization_name", nullable = false)
    private String specializationName;

    @ManyToOne
    @JoinColumn(name = "cycle_id", nullable = false)
    private StudyCycle studyCycle;


}
