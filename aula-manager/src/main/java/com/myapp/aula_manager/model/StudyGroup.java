package com.myapp.aula_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "study_group", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"specialization_id", "study_year", "group_name"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Check(constraints = "study_year > 0")
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @ManyToOne
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;

    @Column(name = "study_year", nullable = false)
    private Integer studyYear;

    @Column(name = "group_name", nullable = false)
    private String groupName;
}
