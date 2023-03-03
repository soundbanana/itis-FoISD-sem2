package com.chemaev.repository.Literature;

import com.chemaev.model.Literature.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findAllById(List<Integer> ids);
}
