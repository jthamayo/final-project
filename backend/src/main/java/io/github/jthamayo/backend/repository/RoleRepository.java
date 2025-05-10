package io.github.jthamayo.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.Role;
import io.github.jthamayo.backend.entity.enums.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType roleType);
}
