package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.repositories.VetRepository;
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
class VetSDJpaServiceTest {
    @Mock VetRepository vetRepository;
    @InjectMocks VetSDJpaService vetSDJpaService;

    Vet returnVet;

    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Vet> vetSet = new HashSet<>();
        vetSet.add(Vet.builder().id(1L).build());
        vetSet.add(Vet.builder().id(2L).build());

        when(vetRepository.findAll()).thenReturn(vetSet);
        Set<Vet> vets = vetSDJpaService.findAll();
        assertNotNull(vets);
        assertEquals(2,vets.size());

    }

    @Test
    void findByIdNotFound(){
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());
        Vet vet = vetSDJpaService.findById(1L);
        assertNull(vet);
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));
        Vet vet = vetSDJpaService.findById(1L);
        assertNotNull(vet);
    }

    @Test
    void save() {
        Vet vetToSave = Vet.builder().id(1L).build();
        when(vetRepository.save(any())).thenReturn(returnVet);
        Vet savedVet = vetSDJpaService.save(vetToSave);
        assertNotNull(savedVet);
        verify(vetRepository).save(any());
    }

    @Test
    void delete() {
        vetSDJpaService.delete(returnVet);
        verify(vetRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        vetSDJpaService.deleteById(1L);
        verify(vetRepository,times(1)).deleteById(anyLong());
    }
}