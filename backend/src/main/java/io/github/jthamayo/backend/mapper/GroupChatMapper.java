package io.github.jthamayo.backend.mapper;

import java.util.stream.Collectors;

import io.github.jthamayo.backend.dto.GroupChatDto;
import io.github.jthamayo.backend.entity.GroupChat;

public class GroupChatMapper {
    public static GroupChatDto mapToGroupChatDto(GroupChat group) {
	return new GroupChatDto(group.getId(),
		group.getGroup().getUsers().stream().map(user -> user.getUsername()).collect(Collectors.toList()),
		group.getMessages().stream().map(MessageMapper::mapToMessageDto).collect(Collectors.toList()));
    }
}
