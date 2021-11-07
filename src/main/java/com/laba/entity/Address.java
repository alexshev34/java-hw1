package com.laba.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
        String street;
    @Column(nullable = false)
    String house;
    @OneToMany(mappedBy = "address",fetch = FetchType.EAGER)
    Set<Parent> parents;
    //@OneToOne    @JoinColumn(name = "educational_id", referencedColumnName = "id")    EducationalInstitution educationalInstitution;

    @ManyToOne(fetch = FetchType.EAGER)
    Region region;

    public Address() {
    }

    public Address(String street, String house) {
        this.street = street;
        this.house = house;
    }




    @Override
    public String toString() {
        return String.format("Адрес: id %d, ул. %s, дом %s",id,street,house);
    }
}
