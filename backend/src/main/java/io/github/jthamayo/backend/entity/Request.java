package io.github.jthamayo.backend.entity;

import java.time.LocalDate;

import io.github.jthamayo.backend.entity.enums.UserRequestStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fk_sender", nullable = false)
    private User userSender;
    @ManyToOne
    @JoinColumn(name = "fk_receiver", nullable = false)
    private User userReceiver;
    private LocalDate sentDate;
    @Enumerated(EnumType.STRING)
    private UserRequestStatus status = UserRequestStatus.PENDING;

    /////////////////////////////// GETTERS&SETTERS///////////////////////////////

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public User getUserSender() {
	return userSender;
    }

    public void setUserSender(User userSender) {
	this.userSender = userSender;
    }

    public User getUserReceiver() {
	return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
	this.userReceiver = userReceiver;
    }

    public LocalDate getSentDate() {
	return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
	this.sentDate = sentDate;
    }

    public UserRequestStatus getStatus() {
	return status;
    }

    public void setStatus(UserRequestStatus status) {
	this.status = status;
    }

    /////////////////////////////// CONSTRUCTOR////////////////////////////////////////

    public Request() {
    }

    public Request(Long id, User userSender, User userReceiver, LocalDate sentDate, UserRequestStatus status) {
	this.id = id;
	this.userSender = userSender;
	this.userReceiver = userReceiver;
	this.sentDate = sentDate;
	this.status = status;
    }

    public Request(User userSender, User userReceiver) {
	this.userSender = userSender;
	this.userReceiver = userReceiver;
    }

    public Request(Long id, LocalDate sentDate, UserRequestStatus status) {
	this.id = id;
	this.sentDate = sentDate;
	this.status = status;
    }

}
