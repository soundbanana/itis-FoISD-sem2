package com.chemaev.repository.Literature;

import com.chemaev.model.Literature.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher getPublisherByName(String name);

    Page<Publisher> findAll(Pageable pageable);
}
