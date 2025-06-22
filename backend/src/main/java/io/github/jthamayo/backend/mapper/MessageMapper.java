package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.MessageDto;
import io.github.jthamayo.backend.entity.Message;

public class MessageMapper {

    public static MessageDto mapToMessageDto(Message message) {
	return new MessageDto(message.getContent(), message.getSender().getUsername(), message.getSentAt(),
		message.getChat().getId());
    }

    public static Message mapToMessage(MessageDto messageDto) {
	return new Message(messageDto.getContent(), messageDto.getSentAt());

    }
}
