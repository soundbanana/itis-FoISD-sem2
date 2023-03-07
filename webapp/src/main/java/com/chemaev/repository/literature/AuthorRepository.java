package com.chemaev.repository.literature;

import com.chemaev.model.literature.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findAllById(List<Integer> ids);
}
