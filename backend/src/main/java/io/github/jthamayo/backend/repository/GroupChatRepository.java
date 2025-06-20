package io.github.jthamayo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.GroupChat;

public interface GroupChatRepository extends JpaRepository<GroupChat, Long>{

}
