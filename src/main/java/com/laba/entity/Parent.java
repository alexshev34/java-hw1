package com.laba.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "parents")
@Getter
@Setter
public class Parent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id",nullable = false)
    Address address;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Child> children;

    public Parent() {
    }

    public Parent(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }



    @Override
    public String toString() {
        return String.format("Родитель id %d,  %s %s, дети: %s, %s",id,firstName,lastName,
                (children==null?"нет":children),address);
    }
}
