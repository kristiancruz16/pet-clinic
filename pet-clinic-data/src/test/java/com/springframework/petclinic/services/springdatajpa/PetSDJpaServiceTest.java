package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.repositories.PetRepository;
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
class PetSDJpaServiceTest {

    @Mock PetRepository petRepository;

    @InjectMocks PetSDJpaService petSDJpaService;

    public static final String PET_NAME = "doggie";

    Pet returnPet;

    @BeforeEach
    void setUp(){
        returnPet = Pet.builder().id(1L).name(PET_NAME).build();
    }


    @Test
    void findAll() {
        Set<Pet> petSet = new HashSet<>();
        petSet.add(Pet.builder().id(1L).build());
        petSet.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(petSet);
        Set <Pet> pets = petSDJpaService.findAll();
        assertNotNull(pets);
        assertEquals(2,pets.size());

    }

    @Test
    void findByIdNotFound(){
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());
        Pet pet = petSDJpaService.findById(1L);
        assertNull(pet);
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));
        Pet pet = petSDJpaService.findById(1L);
        assertNotNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().id(1L).build();
        when(petRepository.save(any())).thenReturn(returnPet);
        Pet savedPet = petSDJpaService.save(petToSave);
        assertNotNull(savedPet);
        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        petSDJpaService.delete(returnPet);
        verify(petRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        petSDJpaService.deleteById(1L);
        verify(petRepository,times(1)).deleteById(anyLong());
    }
}