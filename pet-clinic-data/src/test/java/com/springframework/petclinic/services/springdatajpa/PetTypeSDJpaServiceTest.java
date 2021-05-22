package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.repositories.PetTypeRepository;
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
class PetTypeSDJpaServiceTest {

    @Mock PetTypeRepository petTypeRepository;

    @InjectMocks PetTypeSDJpaService petTypeSDJpaService;



    PetType returnPetType;


    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet = new HashSet<>();
        petTypeSet.add(PetType.builder().id(1L).build());
        petTypeSet.add(PetType.builder().id(2L).build());
        when(petTypeRepository.findAll()).thenReturn(petTypeSet);
        Set<PetType> pets = petTypeSDJpaService.findAll();
        assertNotNull(pets);
        assertEquals(2,pets.size());
    }

    @Test
    void findByIdNotFound(){
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());
        PetType petType = petTypeSDJpaService.findById(1L);
        assertNull(petType);
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));
        PetType petType = petTypeSDJpaService.findById(1L);
        assertNotNull(petType);
    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.builder().id(1L).build();
        when(petTypeRepository.save(any())).thenReturn(returnPetType);
        PetType savedPetType = petTypeSDJpaService.save(petTypeToSave);
        assertNotNull(savedPetType);
        verify(petTypeRepository).save(any());

    }

    @Test
    void delete() {
        petTypeSDJpaService.delete(returnPetType);
        verify(petTypeRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        petTypeSDJpaService.deleteById(anyLong());
        verify(petTypeRepository,times(1)).deleteById(anyLong());
    }
}