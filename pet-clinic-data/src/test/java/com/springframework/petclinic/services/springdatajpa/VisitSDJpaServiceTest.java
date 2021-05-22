package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Visit;
import com.springframework.petclinic.repositories.VisitRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock VisitRepository visitRepository;

    @InjectMocks VisitSDJpaService visitSDJpaService;

    Visit returnVisit;

    @BeforeEach
    void setUp() {
        returnVisit = Visit.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Visit> visitSet = new HashSet<>();
        visitSet.add(Visit.builder().id(1L).build());
        visitSet.add(Visit.builder().id(2L).build());
        when(visitRepository.findAll()).thenReturn(visitSet);
        Set<Visit> visits = visitSDJpaService.findAll();
        assertNotNull(visits);
        assertEquals(2,visits.size());
    }

    @Test
    void findByIdNotFound(){
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());
        Visit visit = visitSDJpaService.findById(1L);
        assertNull(visit);
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnVisit));
        Visit visit = visitSDJpaService.findById(1L);
        assertNotNull(visit);
    }

    @Test
    void save() {
        Visit visitToSave = Visit.builder().id(1L).build();
        when(visitRepository.save(any())).thenReturn(returnVisit);
        Visit savedVisit = visitSDJpaService.save(visitToSave);
        assertNotNull(savedVisit);
        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        visitSDJpaService.delete(returnVisit);
        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        visitSDJpaService.deleteById(1L);
        verify(visitRepository).deleteById(anyLong());
    }
}