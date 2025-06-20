package io.github.jthamayo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.NetworkChat;

public interface NetworkChatRepository extends JpaRepository<NetworkChat, Long>{

}
