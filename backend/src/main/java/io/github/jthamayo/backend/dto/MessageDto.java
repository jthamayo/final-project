package io.github.jthamayo.backend.dto;

import java.time.LocalDateTime;

public class MessageDto {

    private Long id;
    private String content;
    private String senderUsername;
    private LocalDateTime sentAt;
    private Long chatId;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public String getSenderUsername() {
	return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
	this.senderUsername = senderUsername;
    }

    public LocalDateTime getSentAt() {
	return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
	this.sentAt = sentAt;
    }

    public Long getChatId() {
	return chatId;
    }

    public void setChatId(Long chatId) {
	this.chatId = chatId;
    }

    public MessageDto(Long id, String content, String senderUsername, LocalDateTime sentAt, Long chatId) {
	this.id = id;
	this.content = content;
	this.senderUsername = senderUsername;
	this.sentAt = sentAt;
	this.chatId = chatId;
    }

}
