package com.chemaev.model.literature;

import javax.persistence.*;

@Entity(name = "legal_address")
public class LegalAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String house;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
}
