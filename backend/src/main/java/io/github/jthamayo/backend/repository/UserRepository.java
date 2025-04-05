package io.github.jthamayo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
