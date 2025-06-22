package io.github.jthamayo.backend.service;

import io.github.jthamayo.backend.dto.ChatDto;
import io.github.jthamayo.backend.dto.MessageDto;

public interface ChatService {

    ChatDto getChatById(Long id);
    
    MessageDto sendMessage(MessageDto message);
}
