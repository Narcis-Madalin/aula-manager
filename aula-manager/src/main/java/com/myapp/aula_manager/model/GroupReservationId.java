package com.myapp.aula_manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GroupReservationId implements Serializable {

    @Column(name = "room_reservation_id", nullable = false)
    private Long roomReservationId;

    @Column(name = "group_id", nullable = false)
    private Long groupId;
}
