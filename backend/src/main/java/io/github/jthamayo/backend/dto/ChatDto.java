package io.github.jthamayo.backend.dto;

import java.util.List;

public abstract class ChatDto {

    private Long id;
    private List<MessageDto> messages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }
}
