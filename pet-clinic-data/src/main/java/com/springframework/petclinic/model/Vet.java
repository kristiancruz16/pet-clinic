package com.springframework.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vet")
public class Vet extends Person{

    @Builder
    public Vet(Long id, String firstName, String lastName, Set<Specialty> specialty) {
        super(id, firstName, lastName);
        this.specialty = specialty;
    }


    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialty",
        joinColumns = @JoinColumn(name = "vet_id"),
        inverseJoinColumns = @JoinColumn(name = "specialty_id"))
     private Set <Specialty> specialty =new HashSet<>();

}
