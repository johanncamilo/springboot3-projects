package com.johann.springmvc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.johann.springmvc.model.dto.ParamDto;
import com.johann.springmvc.model.dto.ParamMixDto;

import jakarta.servlet.http.HttpServletRequest;

class RequestParamsControllerTest {

    private RequestParamsController controller;

    @BeforeEach
    void setUp() {
        controller = new RequestParamsController();
    }

    @Test
    void testFoo_correctlyProcessesAndCombinesStringParameters() {
        // Given
        String message = "Hello";
        String otro = "World";

        // When
        ParamDto result = controller.foo(message, otro);

        // Then
        assertNotNull(result);
        assertEquals("Hello World", result.getMessage());
    }

    @Test
    void testFoo_withEmptyStrings() {
        // Given
        String message = "";
        String otro = "";

        // When
        ParamDto result = controller.foo(message, otro);

        // Then
        assertNotNull(result);
        assertEquals(" ", result.getMessage());
    }

    @Test
    void testFoo_withSpecialCharacters() {
        // Given
        String message = "Hello!";
        String otro = "@#$%";

        // When
        ParamDto result = controller.foo(message, otro);

        // Then
        assertNotNull(result);
        assertEquals("Hello! @#$%", result.getMessage());
    }

    @Test
    void testBar_correctlyProcessesStringAndIntegerParameters() {
        // Given
        String text = "Test message";
        Integer code = 200;

        // When
        ParamMixDto result = controller.bar(text, code);

        // Then
        assertNotNull(result);
        assertEquals("Test message", result.getMessage());
        assertEquals(200, result.getCode());
    }

    @Test
    void testBar_withNegativeCode() {
        // Given
        String text = "Error message";
        Integer code = -1;

        // When
        ParamMixDto result = controller.bar(text, code);

        // Then
        assertNotNull(result);
        assertEquals("Error message", result.getMessage());
        assertEquals(-1, result.getCode());
    }

    @Test
    void testBar_withZeroCode() {
        // Given
        String text = "Zero code";
        Integer code = 0;

        // When
        ParamMixDto result = controller.bar(text, code);

        // Then
        assertNotNull(result);
        assertEquals("Zero code", result.getMessage());
        assertEquals(0, result.getCode());
    }

    @Test
    void testGetServlet_correctlyParsesParametersFromHttpServletRequest() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("message")).thenReturn("Servlet message");
        when(request.getParameter("code")).thenReturn("500");

        // When
        ParamMixDto result = controller.getServlet(request);

        // Then
        assertNotNull(result);
        assertEquals("Servlet message", result.getMessage());
        assertEquals(500, result.getCode());
    }

    @Test
    void testGetServlet_handlesInvalidCodeGracefully() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("message")).thenReturn("Invalid code");
        when(request.getParameter("code")).thenReturn("not-a-number");

        // When
        ParamMixDto result = controller.getServlet(request);

        // Then
        assertNotNull(result);
        assertEquals("Invalid code", result.getMessage());
        assertEquals(111, result.getCode()); // Default value when parsing fails
    }

    @Test
    void testGetServlet_handlesNullCodeParameter() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("message")).thenReturn("Null code");
        when(request.getParameter("code")).thenReturn(null);

        // When
        ParamMixDto result = controller.getServlet(request);

        // Then
        assertNotNull(result);
        assertEquals("Null code", result.getMessage());
        assertEquals(111, result.getCode()); // Default value when code is null
    }

    @Test
    void testGetServlet_handlesEmptyCodeString() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("message")).thenReturn("Empty code");
        when(request.getParameter("code")).thenReturn("");

        // When
        ParamMixDto result = controller.getServlet(request);

        // Then
        assertNotNull(result);
        assertEquals("Empty code", result.getMessage());
        assertEquals(111, result.getCode()); // Default value when code is empty
    }

    @Test
    void testGetServlet_handlesNullMessageParameter() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("message")).thenReturn(null);
        when(request.getParameter("code")).thenReturn("999");

        // When
        ParamMixDto result = controller.getServlet(request);

        // Then
        assertNotNull(result);
        assertEquals(null, result.getMessage());
        assertEquals(999, result.getCode());
    }
}
