package io.github.jthamayo.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long>{

}
