package com.application.school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "university")
@AllArgsConstructor
@NoArgsConstructor
public class University {

    @Id
    @Column(name = "university_id")
    private Long university_id;

    @Column(name = "name")
    private String name;

    @Column(name = "abbreviation")
    private String abbreviation;

    @OneToMany(mappedBy = "university")
    private Set<Faculty> faculties;

}
