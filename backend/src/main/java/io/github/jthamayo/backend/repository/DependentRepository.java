package io.github.jthamayo.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.Dependent;

public interface DependentRepository extends JpaRepository<Dependent, Long>{

    List<Dependent> findByGuardianId(Long guardianId);
}
