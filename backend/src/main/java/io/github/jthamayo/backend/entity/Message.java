package io.github.jthamayo.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime sentAt;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

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

    public LocalDateTime getSentAt() {
	return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
	this.sentAt = sentAt;
    }

    public User getSender() {
	return sender;
    }

    public void setSender(User sender) {
	this.sender = sender;
    }

    public Chat getChat() {
	return chat;
    }

    public void setChat(Chat chat) {
	this.chat = chat;
    }

    public Message() {

    }
    
    public Message(Long id, String content, LocalDateTime sentAt) {
	this.id = id;
	this.content = content;
	this.sentAt = sentAt;
    }

    public Message(Long id, String content, LocalDateTime sentAt, User sender, Chat chat) {
	this.id = id;
	this.content = content;
	this.sentAt = sentAt;
	this.sender = sender;
	this.chat = chat;
    }

}
