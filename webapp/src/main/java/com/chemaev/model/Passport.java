package com.chemaev.model;

import javax.persistence.*;

@Entity(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer series;
    @Column(nullable = false)
    private Integer number;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
