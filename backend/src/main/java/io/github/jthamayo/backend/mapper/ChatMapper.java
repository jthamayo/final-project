package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.ChatDto;
import io.github.jthamayo.backend.entity.Chat;
import io.github.jthamayo.backend.entity.GroupChat;
import io.github.jthamayo.backend.entity.NetworkChat;

public class ChatMapper {

    public static ChatDto mapToChatDto(Chat chat) {
	if (chat instanceof GroupChat) {
	    return GroupChatMapper.mapToGroupChatDto((GroupChat) chat);
	} else if (chat instanceof NetworkChat) {
	    return NetworkChatMapper.mapToNetworkChatDto((NetworkChat) chat);
	}
	throw new IllegalArgumentException("Unknown chat type");
    }
}
