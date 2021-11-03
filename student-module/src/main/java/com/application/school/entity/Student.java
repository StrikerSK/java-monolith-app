package com.application.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_gen")
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", initialValue = 20, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "country")
    private String country;

    @Column(name = "proglanguage")
    private String favoriteLanguage;

    @Column(name = "typeOfStudy")
    private String typeOfStudy;

    @Column(name = "grade")
    private String grade;

    @Column(name = "university")
    private String university = "";

    @Column(name = "faculty")
    private String faculty = "";

    @ElementCollection
    @CollectionTable(name = "student_spoken_languages", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "spoken_languages")
    private Set<String> spokenLanguages = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "student_known_languages", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "known_languages")
    private Set<String> knownLanguages = new HashSet<>();

    public Student(String firstName, String lastName, String country, String favoriteLanguage, String typeOfStudy, String grade, String university, String faculty, Set<String> spokenLanguages, Set<String> knownLanguages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.favoriteLanguage = favoriteLanguage;
        this.typeOfStudy = typeOfStudy;
        this.grade = grade;
        this.university = university;
        this.faculty = faculty;
        this.spokenLanguages = spokenLanguages;
        this.knownLanguages = knownLanguages;
    }

    @JsonIgnore
    public String getStringSpoken(){
        StringBuilder stringField = new StringBuilder();
        for (String string : spokenLanguages) {
            if (stringField.length() > 0 ){
                stringField.append(", ");
            }
            stringField.append(string);
        }
        return stringField.toString();
    }

    @JsonIgnore
    public String getStringProgramming(){
        StringBuilder stringField = new StringBuilder();
        for (String string : knownLanguages) {
            if (stringField.length() > 0 ){
                stringField.append(", ");
            }
            stringField.append(string);
        }
        return stringField.toString();
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", favoriteLanguage='" + favoriteLanguage + '\'' +
                ", typeOfStudy='" + typeOfStudy + '\'' +
                ", grade='" + grade + '\'' +
                ", university='" + university + '\'' +
                ", faculty='" + faculty + '\'' +
                ", spokenLanguages=" + spokenLanguages +
                ", knownLanguages=" + knownLanguages +
                '}';
    }
}
