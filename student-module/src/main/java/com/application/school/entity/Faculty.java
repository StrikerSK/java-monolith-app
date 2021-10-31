package com.application.school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "faculty")
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {

    @Id
    @Column(name = "faculty_id")
    private Long facultyId;

    @Column(name = "name")
    private String name;

    @Column(name = "abbreviation")
    private String abbreviation;

    @ManyToOne
    @JoinColumn(name="university_id", nullable = false)
    private University university;

}
