package io.github.jthamayo.backend.dto;

import java.time.LocalDate;

public class RequestDto {

    private Long id;
    private Long userSenderId;
    private Long userReceiverId;
    private LocalDate sentDate;
    // private Enum status;

    /////////////////////////////// GETTERS&SETTERS///////////////////////////////

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getUserSender() {
	return userSenderId;
    }

    public void setUserSender(Long userSenderId) {
	this.userSenderId = userSenderId;
    }

    public Long getUserReceiver() {
	return userReceiverId;
    }

    public void setUserReceiver(Long userReceiverId) {
	this.userReceiverId = userReceiverId;
    }

    public LocalDate getSentDate() {
	return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
	this.sentDate = sentDate;
    }

    /////////////////////////////// CONSTRUCTOR////////////////////////////////////////

    public RequestDto() {
    }

    public RequestDto(Long id, Long userSenderId, Long userReceiverId, LocalDate sentDate) {
	this.id = id;
	this.userSenderId = userSenderId;
	this.userReceiverId = userReceiverId;
	this.sentDate = sentDate;
    }

    public RequestDto(Long userSenderId, Long userReceiverId) {
	this.userSenderId = userSenderId;
	this.userReceiverId = userReceiverId;
    }
}
