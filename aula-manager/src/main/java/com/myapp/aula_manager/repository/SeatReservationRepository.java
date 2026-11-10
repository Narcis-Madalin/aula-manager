package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.Room;
import com.myapp.aula_manager.model.RoomReservation;
import com.myapp.aula_manager.model.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {

    List<SeatReservation> findByAppUser_UserId (Long userId);

    List<SeatReservation> findByRoomReservation_RoomReservationId (Long roomReservationId);

    boolean existsBySeatNrAndRoomReservation_RoomReservationId (Integer seatNr, Long roomReservationId);

    boolean existsBySeatNrAndRoomReservation(Integer seatNr, RoomReservation roomReservation);

    boolean existsByAppUser_UserIdAndRoomReservation_RoomReservationId(Long userid, Long roomReservationId);

    boolean existsByAppUser_EmailAndRoomReservation_Room_RoomName(String email, String roomName);

    boolean existsByAppUser_EmailAndRoomReservation_Room_RoomId(String email, Integer roomId);

    boolean existsByAppUser_EmailAndRoomReservation_Room (String email, Room room);


}
