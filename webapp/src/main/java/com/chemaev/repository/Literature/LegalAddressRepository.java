package com.chemaev.repository.Literature;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface LegalAddressRepository extends JpaRepository<LegalAddressRepository, Integer> {
    LegalAddressRepository getLegalAdressById(Integer id);

    Page<LegalAddressRepository> findAll(Pageable pageable);
}
