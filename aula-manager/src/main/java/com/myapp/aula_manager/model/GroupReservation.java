package com.myapp.aula_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "group_reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupReservation {

    @EmbeddedId
    private GroupReservationId groupReservationId;

    @ManyToOne
    @MapsId(value = "roomReservationId")
    @JoinColumn(name = "room_reservation_id")
    private RoomReservation roomReservation;

    @ManyToOne
    @MapsId(value = "groupId")
    @JoinColumn(name = "group_id")
    private StudyGroup studyGroup;
}
