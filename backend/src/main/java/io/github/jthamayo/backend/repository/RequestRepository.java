package io.github.jthamayo.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jthamayo.backend.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("SELECT r from Request r where r.userReceiver.id = :receiverId AND r.status = 'PENDING'")
    List<Request> findUserPendingReceivedRequests(@Param("receiverId") Long receiverId);

    @Query("SELECT r from Request r where r.userSender.id = :senderId AND r.status = 'PENDING'")
    List<Request> findUserPendingSentRequests(@Param("senderId") Long senderId);

    @Query("SELECT r from Request r where r.userSender.id = :senderId AND r.userReceiver = :receiverId AND r.status = 'PENDING'")
    Optional<Request> findPendingRequestBetweenUsers(@Param("senderId") Long senderId,
	    @Param("receiverId") Long receiverId);

}
