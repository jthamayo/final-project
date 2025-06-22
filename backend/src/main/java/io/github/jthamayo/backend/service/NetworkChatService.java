package io.github.jthamayo.backend.service;

import java.util.List;

import io.github.jthamayo.backend.dto.MessageDto;
import io.github.jthamayo.backend.dto.NetworkChatDto;

public interface NetworkChatService extends ChatService {

    NetworkChatDto createNetworkChat(Long networkId);

    NetworkChatDto getNetworkChatById(Long networkChatId);

    NetworkChatDto updateNetworkChat(Long networkChatId, NetworkChatDto networkChat);

    void deleteNetworkChat(Long networkChatId);

    MessageDto sendMessage(MessageDto message);
    
    List<NetworkChatDto> getAllUserChats(Long userId);

}
