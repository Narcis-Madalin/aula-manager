package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    private Room generateRoomExample(String roomName, Integer capacity){
        Room testRoom = new Room();

        testRoom.setCapacity(capacity);
        testRoom.setRoomName(roomName);

        return  testRoom;
    }

    @Test
    void findByRoomName_shouldReturnRoom_whenRoomExists (){

        // Arrange

        Room testRoom = generateRoomExample("Testing Room", 200);
        roomRepository.save(testRoom);

        // Act

        Optional<Room> foundRoom  = roomRepository.findByRoomName("Testing Room");

        // Assert

        assertTrue(foundRoom.isPresent());
        assertEquals("Testing Room", foundRoom.get().getRoomName());
    }

    @Test
    void existsByRoomName_shouldReturnTrue_whenRoomExists (){

        // Arrange

        Room testRoom = generateRoomExample("Testing Room", 200);
        roomRepository.save(testRoom);

        // Act

        boolean result = roomRepository.existsByRoomName("Testing Room");

        // Assert

        assertTrue(result);

    }

    @Test
    void findByCapacity_shouldReturnRoomList_whenRoomExists (){

        // Arrange

        // List.of creeaza o lista immutable, iar daca vrem o lista peste care putem face modificari de elemente, trebuie folosit new ArrayList<>(Arrays.asList(...))
        List<Room> allTestRooms = List.of(
                generateRoomExample("First Testing Room", 200),
                generateRoomExample("Another Testing Room", 100),
                generateRoomExample("Yet Another Testing Room", 200),
                generateRoomExample("Nr 4 Testing Room", 50),
                generateRoomExample("Testing Room", 100),
                generateRoomExample("Last Test Room", 200)
        );

        roomRepository.saveAll(allTestRooms);

        // Act

        List<Room> foundRooms = roomRepository.findByCapacity(200);

        // Assert

        assertFalse(foundRooms.isEmpty());
        assertEquals(3,foundRooms.size());
        assertEquals(200, foundRooms.getFirst().getCapacity());

    }

    @Test
    void findByRoomName_shouldReturnEmpty_whenRoomDoesNotExist(){

        // Arrange

        // Act

        Optional<Room> roomFound = roomRepository.findByRoomName("Some random room");

        // Assert
        assertTrue(roomFound.isEmpty());
    }


    @Test
    void existsByRoomName_shouldReturnFalse_whenRoomDoesNotExist(){

        // Arrange

        // Act
        boolean result = roomRepository.existsByRoomName("Some room name");

        // Assert

        assertFalse(result);
    }

    @Test
    void findByCapacity_shouldReturnEmptyList_whenRoomDoesNotExist(){

        // Arrange

        // Act
        List<Room> foundRooms = roomRepository.findByCapacity(7);

        // Assert
        assertTrue(foundRooms.isEmpty());
    }

}
