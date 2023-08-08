package com.meongmory.meongmory.domain.family.repository;

import com.meongmory.meongmory.domain.family.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
  Page<Animal> findAll(Specification<Animal> spec, Pageable pageable);
}
