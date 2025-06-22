package io.github.jthamayo.backend.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.MessageDto;
import io.github.jthamayo.backend.entity.Chat;
import io.github.jthamayo.backend.entity.Message;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.MessageMapper;
import io.github.jthamayo.backend.repository.ChatRepository;
import io.github.jthamayo.backend.repository.MessageRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private ChatRepository chatRepository;
    private UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, ChatRepository chatRepository,
	    UserRepository userRepository) {
	this.messageRepository = messageRepository;
	this.chatRepository = chatRepository;
	this.userRepository = userRepository;
    }

    @Override
    public MessageDto createMessage(MessageDto message) {
	Chat chat = chatRepository.findById(message.getChatId())
		.orElseThrow(() -> new ResourceNotFoundException("Chat not found"));
	User userSender = userRepository.findByUsername(message.getSenderUsername())
		.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	Message newMessage = MessageMapper.mapToMessage(message);
	newMessage.setSentAt(LocalDateTime.now());
	newMessage.setSender(userSender);
	newMessage.setChat(chat);
	return MessageMapper.mapToMessageDto(messageRepository.save(newMessage));
    }

    @Override
    public MessageDto getMessageById(Long messageId) {
	Message message = messageRepository.findById(messageId)
		.orElseThrow(() -> new ResourceNotFoundException("Message not found"));
	;
	return MessageMapper.mapToMessageDto(message);
    }

    @Override
    public MessageDto updateMessage(Long messageId, MessageDto updatedMessage) {
	Chat chat = chatRepository.findById(updatedMessage.getChatId())
		.orElseThrow(() -> new ResourceNotFoundException("Chat not found"));
	User userSender = userRepository.findByUsername(updatedMessage.getSenderUsername())
		.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	Message message = messageRepository.findById(messageId)
		.orElseThrow(() -> new ResourceNotFoundException("Message not found"));
	message.setSentAt(LocalDateTime.now());
	message.setSender(userSender);
	message.setChat(chat);
	message.setContent(updatedMessage.getContent());
	return MessageMapper.mapToMessageDto(messageRepository.save(message));
    }

    @Override
    public void deleteMessage(Long messageId) {
	messageRepository.findById(messageId).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
	messageRepository.deleteById(messageId);
    }

}
