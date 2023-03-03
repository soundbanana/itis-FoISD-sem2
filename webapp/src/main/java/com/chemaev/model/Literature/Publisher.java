package com.chemaev.model.Literature;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "publishers")
@Getter
@Setter
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "publisher")
    private LegalAddress legalAddress;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "publishers")
    private List<Book> books;
}
