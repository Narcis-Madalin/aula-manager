package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.GroupReservation;
import com.myapp.aula_manager.model.GroupReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupReservationRepository extends JpaRepository<GroupReservation, GroupReservationId> {

    List<GroupReservation> findByRoomReservation_RoomReservationId(Long roomReservationId);

    List<GroupReservation> findByStudyGroup_GroupId(Long groupId);

    boolean existsByStudyGroup_GroupIdAndRoomReservation_RoomReservationId(Long groupId, Long roomReservationId);

    Optional<GroupReservation> findByGroupReservationId_RoomReservationIdAndGroupReservationId_GroupId(Long roomReservationId, Long groupId);


}
