package com.myapp.aula_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seat_reservation", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"room_reservation_id", "user_id"}),
        @UniqueConstraint(columnNames = {"room_reservation_id", "seat_nr"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_reservation_id")
    private Long seatReservationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "room_reservation_id", nullable = false)
    private RoomReservation roomReservation;

    @Column(name = "seat_nr", nullable = false)
    private Integer seatNr;
}
