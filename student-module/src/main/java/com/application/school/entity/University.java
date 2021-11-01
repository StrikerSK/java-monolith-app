package com.application.school.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "university")
@AllArgsConstructor
@NoArgsConstructor
public class University {

    @Id
    @GeneratedValue
    @Column(name = "university_id")
    private Long universityId;

    @Column(name = "name")
    private String name;

    @Column(name = "abbreviation")
    private String abbreviation;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "university")
    private List<Faculty> faculties;

}
