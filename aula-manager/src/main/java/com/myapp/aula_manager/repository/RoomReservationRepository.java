package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {

    List<RoomReservation> findByAppUser_UserId (Long userId);

    List<RoomReservation> findByRoom_RoomId(Integer roomId);

    List<RoomReservation> findByRoom_RoomName (String roomName);

    List<RoomReservation> findByStartTime(LocalDateTime startTime);

    List<RoomReservation> findByEndTime(LocalDateTime endTime);

    boolean existsByAppUser_UserId (Long userId);

    boolean existsByAppUser_LastName (String lastName);

    boolean existsByAppUser_Email (String email);

    List<RoomReservation> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
