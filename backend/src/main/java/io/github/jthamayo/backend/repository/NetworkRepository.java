package io.github.jthamayo.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.jthamayo.backend.entity.Network;
import io.github.jthamayo.backend.entity.User;

public interface NetworkRepository extends JpaRepository<Network, Long> {

    @Query("SELECT n FROM Network WHERE n.user1.id = :userId1 AND n.user2.id = :userId2")
    Optional<Network> findNetworkBetweenUsers(Long userId1, Long userId2);

    @Query("SELECT CASE WHEN n.user1.id = :userId THEN n.user2 ELSE n.user1 END FROM Network n WHERE n.user1.id = :userId OR n.user2.id = :userId")
    List<User> findConnectedUsers(Long userId);

}
