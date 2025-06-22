package io.github.jthamayo.backend.service;

import io.github.jthamayo.backend.dto.MessageDto;

public interface MessageService {

    MessageDto createMessage(MessageDto message);

    MessageDto getMessageById(Long messageId);

    MessageDto updateMessage(Long messageId, MessageDto message);

    void deleteMessage(Long messageId);
}
