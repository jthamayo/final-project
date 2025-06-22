package io.github.jthamayo.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jthamayo.backend.entity.NetworkChat;

public interface NetworkChatRepository extends JpaRepository<NetworkChat, Long> {


    @Query("SELECT c FROM NetworkChat c WHERE c.network.id IN :userNetworkIds")
    List<NetworkChat> findAllUserActiveChats(@Param("userNetworkIds") List<Long> userNetworkIds);
    
    Optional<NetworkChat> findByNetwork_Id(Long networkId);

}
