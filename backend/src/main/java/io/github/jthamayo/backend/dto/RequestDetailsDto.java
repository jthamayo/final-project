package io.github.jthamayo.backend.dto;

import java.time.LocalDate;

import io.github.jthamayo.backend.entity.enums.UserRequestStatus;

public class RequestDetailsDto {

    private Long id;
    private UserSummary userSender;
    private UserSummary userReceiver;
    private LocalDate sentDate;
    private UserRequestStatus status;

    /////////////////////////////// GETTERS&SETTERS///////////////////////////////

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public UserSummary getUserSender() {
	return userSender;
    }

    public void setUserSender(UserSummary userSender) {
	this.userSender = userSender;
    }

    public UserSummary getUserReceiver() {
	return userReceiver;
    }

    public UserRequestStatus getStatus() {
	return status;
    }

    public void setUserReceiver(UserSummary userReceiver) {
	this.userReceiver = userReceiver;
    }

    public LocalDate getSentDate() {
	return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
	this.sentDate = sentDate;
    }

    public void setStatus(UserRequestStatus status) {
	this.status = status;
    }

    /////////////////////////////// CONSTRUCTOR////////////////////////////////////////

    public RequestDetailsDto() {
    }

    public RequestDetailsDto(Long id, UserSummary userSender, UserSummary userReceiver, LocalDate sentDate,
	    UserRequestStatus status) {
	this.id = id;
	this.userSender = userSender;
	this.userReceiver = userReceiver;
	this.sentDate = sentDate;
	this.status = status;
    }
}
