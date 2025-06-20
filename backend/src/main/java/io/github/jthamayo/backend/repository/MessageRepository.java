package io.github.jthamayo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
