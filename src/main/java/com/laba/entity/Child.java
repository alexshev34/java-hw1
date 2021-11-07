package com.laba.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "children")
@Getter
@Setter
public class Child implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    int age;

    @ManyToOne(fetch = FetchType.EAGER)
    EducationalInstitution educationalInstitution;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Parent> parents = new HashSet<>();

    public Child() {
    }

    public Child(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }


    @Override
    public String toString() {
        return String.format("Ребенок id %d, %s,%s, учится: %s",id,firstName,lastName,educationalInstitution);
    }
}
