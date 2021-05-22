package com.springframework.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialty")
public class Specialty extends BaseEntity{


    @Builder
    public Specialty (Long id, String description){
        super(id);
        this.description = description;
    }

    @Column(name = "description")
    private String description;

}
