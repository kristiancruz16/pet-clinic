package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock VetService vetService;

    @InjectMocks VetController vetController;

    private MockMvc mockMvc;

    Vet vet;

    @BeforeEach
    void setUp() {

    }

    @Test
    void listVets() {
    }

    @Test
    void getVetsJson() {
    }
}