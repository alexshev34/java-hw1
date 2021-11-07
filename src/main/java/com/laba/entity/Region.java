package com.laba.entity;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "regions")
@Getter
@Setter
public class Region implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "region",fetch = FetchType.EAGER)
    Set<Address> addresses;

    public Region(String name) {
        this.name = name;
    }

    public Region() {
    }

    @Override
    public String toString() {
        return String.format("Регион: id %d, %s",id,name);
    }
}
