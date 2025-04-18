package io.github.jthamayo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long>{

}
