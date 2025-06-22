package io.github.jthamayo.backend.service;

import io.github.jthamayo.backend.dto.GroupChatDto;
import io.github.jthamayo.backend.dto.MessageDto;

public interface GroupChatService extends ChatService {

    GroupChatDto createGroupChat(Long groupId);

    GroupChatDto getGroupChatById(Long groupChatId);

    GroupChatDto updateGroupChat(Long groupChatId, GroupChatDto groupChat);

    void deleteGroupChat(Long groupChatId);
    
    MessageDto sendMessage(MessageDto message);
    
    public GroupChatDto getUserGroupChat(Long userId);

}
