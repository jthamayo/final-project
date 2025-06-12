package io.github.jthamayo.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jthamayo.backend.entity.Network;
import io.github.jthamayo.backend.entity.User;

public interface NetworkRepository extends JpaRepository<Network, Long> {

    @Query("SELECT n FROM Network n WHERE n.user1.id = :userId1 AND n.user2.id = :userId2")
    Optional<Network> findNetworkBetweenUsers(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

    @Query("""
    	SELECT u FROM User u WHERE u.id IN
    	(SELECT n.user2.id FROM Network n WHERE n.user1.id = :userId
    	UNION
    	SELECT n.user1.id FROM Network n WHERE n.user2.id = :userId)
    	""")
    List<User> findConnectedUsers(@Param("userId") Long userId);

    @Query("""
    	SELECT u FROM User u
    	   WHERE u.id != :userId
    	   AND NOT EXISTS (
    	   SELECT 1 FROM Network n
    	   WHERE (n.user1.id = :userId AND n.user2.id = u.id)
    	   OR (n.user2.id = :userId AND n.user1.id = u.id))
    	""")
    List<User> findUnconnectedUsers(@Param("userId") Long userId, Pageable pageable);

    @Query("""
    	SELECT u FROM User u WHERE u.id IN
    	(SELECT n.user2.id FROM Network n WHERE n.user1.id = :userId2
    	UNION
    	SELECT n.user1.id FROM Network n WHERE n.user2.id = :userId2)
    	AND u.id NOT IN
    	(SELECT n.user1.id FROM Network n WHERE n.user2.id = :userId1
    	UNION
    	SELECT n.user2.id FROM Network n WHERE n.user1.id = :userId1)
    	AND u.id <> :userId1
    	""")
    List<User> findExclusiveConnections(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

    @Query("""
    	SELECT u FROM User u WHERE u.id IN
    	(SELECT n.user2.id FROM Network n WHERE n.user1.id = :userId2
    	UNION
    	SELECT n.user1.id FROM Network n WHERE n.user2.id = :userId2)
    	AND u.id IN
    	(SELECT n.user1.id FROM Network n WHERE n.user2.id = :userId1
    	UNION
    	SELECT n.user2.id FROM Network n WHERE n.user1.id = :userId1)
    	""")
    List<User> findMutualConnections(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

    @Query("""
    	SELECT DISTINCT u FROM User u WHERE u.id IN
    	(SELECT n.user1.id FROM Network n WHERE n.user2.id IN :groupUserIds
    	UNION
    	SELECT n.user2.id FROM Network n WHERE n.user1.id IN :groupUserIds)
    	AND u.id NOT IN :groupUserIds
    	""")
    List<User> findConnectionsOutsideGroup(@Param("groupUserIds") List<Long> groupUserIds);

}
