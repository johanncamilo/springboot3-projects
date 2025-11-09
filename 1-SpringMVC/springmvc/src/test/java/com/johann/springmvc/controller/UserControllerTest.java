package com.johann.springmvc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.johann.springmvc.model.dto.UserDto;

class UserControllerTest {

    private UserController controller;

    @BeforeEach
    void setUp() {
        controller = new UserController();
    }

    @Test
    void testGetUserDetails_returnsUserDtoWithCorrectUserInformation() {
        // When
        UserDto result = controller.getUserDetails();

        // Then
        assertNotNull(result);
        assertEquals("User Details from DTO", result.getTitle());
        assertEquals("Johann Belos", result.getFullname());
    }

    @Test
    void testGetUserDetails_createsDtoWithCorrectTitleFormat() {
        // When
        UserDto result = controller.getUserDetails();

        // Then
        assertNotNull(result);
        assertTrue(result.getTitle().contains("User Details"));
        assertTrue(result.getTitle().contains("DTO"));
    }

    @Test
    void testGetUserDetails_combinesNameAndLastnameCorrectly() {
        // When
        UserDto result = controller.getUserDetails();

        // Then
        assertNotNull(result);
        String[] nameParts = result.getFullname().split(" ");
        assertEquals(2, nameParts.length);
        assertEquals("Johann", nameParts[0]);
        assertEquals("Belos", nameParts[1]);
    }

    @Test
    void testGetUsers_returnsListOfUserDtoObjects() {
        // When
        List<UserDto> result = controller.getUsers();

        // Then
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void testGetUsers_returnsUserDtosWithCorrectlyMappedData() {
        // When
        List<UserDto> result = controller.getUsers();

        // Then
        assertNotNull(result);
        
        // Verify first user
        UserDto user1 = result.get(0);
        assertEquals("User from DTO", user1.getTitle());
        assertEquals("Gaby Ch", user1.getFullname());
        
        // Verify second user
        UserDto user2 = result.get(1);
        assertEquals("User from DTO", user2.getTitle());
        assertEquals("Ana Lopez", user2.getFullname());
        
        // Verify third user
        UserDto user3 = result.get(2);
        assertEquals("User from DTO", user3.getTitle());
        assertEquals("Juan Perez", user3.getFullname());
    }

    @Test
    void testGetUsers_allUsersHaveSameTitle() {
        // When
        List<UserDto> result = controller.getUsers();

        // Then
        assertNotNull(result);
        assertTrue(result.stream()
                .allMatch(user -> "User from DTO".equals(user.getTitle())));
    }

    @Test
    void testGetUsers_allUsersHaveNonEmptyFullname() {
        // When
        List<UserDto> result = controller.getUsers();

        // Then
        assertNotNull(result);
        assertTrue(result.stream()
                .allMatch(user -> user.getFullname() != null && !user.getFullname().isEmpty()));
    }

    @Test
    void testGetUsers_fullnamesAreCorrectlyFormatted() {
        // When
        List<UserDto> result = controller.getUsers();

        // Then
        assertNotNull(result);
        for (UserDto user : result) {
            String[] nameParts = user.getFullname().split(" ");
            assertEquals(2, nameParts.length, 
                    "Fullname should consist of first name and last name: " + user.getFullname());
        }
    }

    @Test
    void testGetUsers_verifyStreamMappingProducesCorrectResults() {
        // When
        List<UserDto> result = controller.getUsers();

        // Then
        assertNotNull(result);
        assertEquals(3, result.size());
        
        // Verify each DTO is properly instantiated and mapped
        for (UserDto user : result) {
            assertNotNull(user);
            assertNotNull(user.getTitle());
            assertNotNull(user.getFullname());
            assertTrue(user.getFullname().contains(" "), 
                    "Fullname should contain a space between first and last name");
        }
    }
}
