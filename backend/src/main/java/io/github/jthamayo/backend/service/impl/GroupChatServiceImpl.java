package io.github.jthamayo.backend.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.ChatDto;
import io.github.jthamayo.backend.dto.GroupChatDto;
import io.github.jthamayo.backend.dto.MessageDto;
import io.github.jthamayo.backend.entity.Group;
import io.github.jthamayo.backend.entity.GroupChat;
import io.github.jthamayo.backend.entity.Message;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.InvalidOperationException;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.GroupChatMapper;
import io.github.jthamayo.backend.mapper.MessageMapper;
import io.github.jthamayo.backend.repository.GroupChatRepository;
import io.github.jthamayo.backend.repository.GroupRepository;
import io.github.jthamayo.backend.repository.MessageRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.GroupChatService;
import jakarta.transaction.Transactional;

@Service
public class GroupChatServiceImpl implements GroupChatService {

    private GroupChatRepository groupChatRepository;
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private GroupRepository groupRepository;

    public GroupChatServiceImpl(GroupChatRepository groupChatRepository, MessageRepository messageRepository,
	    UserRepository userRepository, GroupRepository grouRepository) {
	this.groupChatRepository = groupChatRepository;
	this.messageRepository = messageRepository;
	this.userRepository = userRepository;
	this.groupChatRepository = groupChatRepository;
    }

    @Override
    @Transactional
    public MessageDto sendMessage(MessageDto message) {
	GroupChat chat = groupChatRepository.findById(message.getChatId())
		.orElseThrow(() -> new ResourceNotFoundException("Group chat not found"));
	User sender = userRepository.findByUsername(message.getSenderUsername())
		.orElseThrow(() -> new ResourceNotFoundException("Sender not found"));

	Message newMessage = new Message(message.getContent(), LocalDateTime.now(), sender, chat);
	chat.getMessages().add(newMessage);
	messageRepository.save(newMessage);
	return MessageMapper.mapToMessageDto(newMessage);
    }

    @Override
    public GroupChatDto createGroupChat(Long groupId) {
	Group group = groupRepository.findById(groupId)
		.orElseThrow(() -> new ResourceNotFoundException("Group not found: " + groupId));
	Optional<GroupChat> groupChat = groupChatRepository.findByGroup_Id(groupId);
	if (groupChat.isPresent()) {
	    throw new InvalidOperationException("This chat already exists");
	}
	GroupChat newGroupChat = new GroupChat();
	newGroupChat.setGroup(group);
	return GroupChatMapper.mapToGroupChatDto(groupChatRepository.save(newGroupChat));
    }

    @Override
    public GroupChatDto getGroupChatById(Long groupChatId) {
	GroupChat chat = groupChatRepository.findById(groupChatId)
		.orElseThrow(() -> new ResourceNotFoundException("Group chat not found"));
	return GroupChatMapper.mapToGroupChatDto(chat);
    }

    @Override
    public GroupChatDto updateGroupChat(Long groupChatId, GroupChatDto updatedGroupChat) {
	GroupChat groupChat = groupChatRepository.findById(groupChatId)
		.orElseThrow(() -> new ResourceNotFoundException("Chat not found: " + groupChatId));
	groupChat.setMessages(updatedGroupChat.getMessages().stream().map(messageDto -> {
	    Message message = MessageMapper.mapToMessage(messageDto);
	    message.setChat(groupChat);
	    return message;
	}).collect(Collectors.toList()));
	return GroupChatMapper.mapToGroupChatDto(groupChatRepository.save(groupChat));
    }

    @Override
    public void deleteGroupChat(Long groupChatId) {
	groupChatRepository.findById(groupChatId)
		.orElseThrow(() -> new ResourceNotFoundException("Group chat not found"));
	groupChatRepository.deleteById(groupChatId);
    }

    @Override
    public ChatDto getChatById(Long id) {
	GroupChat chat = groupChatRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Group chat not found"));
	return GroupChatMapper.mapToGroupChatDto(chat);
    }

    @Override
    public GroupChatDto getUserGroupChat(Long userId) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("Sender not found"));
	if (user.getGroup() == null) {
	    throw new InvalidOperationException("User must have a group");
	}
	GroupChat groupChat = groupChatRepository.findByGroup_Id(user.getGroup().getId())
		.orElseThrow(() -> new ResourceNotFoundException("Group chat not found"));
	return GroupChatMapper.mapToGroupChatDto(groupChat);
    }

}
