package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Specialty;
import com.springframework.petclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialtySDJpaServiceTest {

    @Mock SpecialtyRepository specialtyRepository;

    @InjectMocks SpecialtySDJpaService specialtySDJpaService;

    Specialty returnSpecialty;

    @BeforeEach
    void setUp() {
        returnSpecialty = Specialty.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Specialty> specialtySet = new HashSet<>();
        specialtySet.add(Specialty.builder().id(1L).build());
        specialtySet.add(Specialty.builder().id(2L).build());

        when(specialtyRepository.findAll()).thenReturn(specialtySet);
        Set <Specialty> specialties = specialtySDJpaService.findAll();
        assertNotNull(specialties);
        assertEquals(2,specialties.size());
    }

    @Test
    void findByIdNotFound() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.empty());
        Specialty specialty = specialtySDJpaService.findById(1L);
        assertNull(specialty);
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(returnSpecialty));
        Specialty specialty = specialtySDJpaService.findById(1L);
        assertNotNull(specialty);
    }

    @Test
    void save() {
        Specialty specialtyToSave = Specialty.builder().id(1L).build();
        when(specialtyRepository.save(any())).thenReturn(returnSpecialty);
        Specialty savedSpecialty = specialtySDJpaService.save(specialtyToSave);
        assertNotNull(savedSpecialty);
        verify(specialtyRepository).save(any());
    }

    @Test
    void delete() {
        specialtySDJpaService.delete(returnSpecialty);
        verify(specialtyRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        specialtySDJpaService.deleteById(1L);
        verify(specialtyRepository,times(1)).deleteById(anyLong());
    }
}