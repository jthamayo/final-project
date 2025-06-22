package io.github.jthamayo.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.GroupChat;

public interface GroupChatRepository extends JpaRepository<GroupChat, Long>{


    Optional<GroupChat> findByGroup_Id(Long groupId);

    
}
