package io.github.jthamayo.backend.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.ChatDto;
import io.github.jthamayo.backend.dto.MessageDto;
import io.github.jthamayo.backend.dto.NetworkChatDto;
import io.github.jthamayo.backend.entity.GroupChat;
import io.github.jthamayo.backend.entity.Message;
import io.github.jthamayo.backend.entity.Network;
import io.github.jthamayo.backend.entity.NetworkChat;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.InvalidOperationException;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.GroupChatMapper;
import io.github.jthamayo.backend.mapper.MessageMapper;
import io.github.jthamayo.backend.mapper.NetworkChatMapper;
import io.github.jthamayo.backend.repository.GroupChatRepository;
import io.github.jthamayo.backend.repository.MessageRepository;
import io.github.jthamayo.backend.repository.NetworkChatRepository;
import io.github.jthamayo.backend.repository.NetworkRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.NetworkChatService;
import jakarta.transaction.Transactional;

@Service
public class NetworkChatServiceImpl implements NetworkChatService {

    private GroupChatRepository groupChatRepository;
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private NetworkChatRepository networkChatRepository;
    private NetworkRepository networkRepository;

    public NetworkChatServiceImpl(GroupChatRepository groupChatRepository, MessageRepository messageRepository,
	    UserRepository userRepository, NetworkChatRepository networkChatRepository,
	    NetworkRepository networkRepository) {
	this.groupChatRepository = groupChatRepository;
	this.messageRepository = messageRepository;
	this.userRepository = userRepository;
	this.networkChatRepository = networkChatRepository;
	this.networkRepository = networkRepository;
    }

    @Override
    public ChatDto getChatById(Long id) {
	GroupChat chat = groupChatRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Group chat not found"));
	return GroupChatMapper.mapToGroupChatDto(chat);
    }

    @Override
    @Transactional
    public MessageDto sendMessage(MessageDto message) {
	NetworkChat chat = networkChatRepository.findById(message.getChatId())
		.orElseThrow(() -> new ResourceNotFoundException("Network chat not found"));
	User sender = userRepository.findByUsername(message.getSenderUsername())
		.orElseThrow(() -> new ResourceNotFoundException("Sender not found"));

	Message newMessage = new Message(message.getContent(), LocalDateTime.now(), sender, chat);
	chat.getMessages().add(newMessage);
	messageRepository.save(newMessage);
	return MessageMapper.mapToMessageDto(newMessage);
    }

    @Override
    public NetworkChatDto createNetworkChat(Long networkId) {
	Network network = networkRepository.findById(networkId)
		.orElseThrow(() -> new ResourceNotFoundException("Network not found: " + networkId));
	Optional<NetworkChat> networkChat = networkChatRepository.findByNetwork_Id(networkId);
	if (networkChat.isPresent()) {
	    throw new InvalidOperationException("This chat already exists");
	}
	NetworkChat newNetworkChat = new NetworkChat();
	newNetworkChat.setNetwork(network);
	return NetworkChatMapper.mapToNetworkChatDto(networkChatRepository.save(newNetworkChat));
    }

    @Override
    public NetworkChatDto getNetworkChatById(Long networkChatId) {
	NetworkChat networkChat = networkChatRepository.findById(networkChatId)
		.orElseThrow(() -> new ResourceNotFoundException("Chat not found: " + networkChatId));
	return NetworkChatMapper.mapToNetworkChatDto(networkChat);
    }

    @Override
    public NetworkChatDto updateNetworkChat(Long networkChatId, NetworkChatDto updatedNetworkChat) {
	NetworkChat networkChat = networkChatRepository.findById(networkChatId)
		.orElseThrow(() -> new ResourceNotFoundException("Chat not found: " + networkChatId));
	networkChat.setMessages(updatedNetworkChat.getMessages().stream().map(messageDto -> {
	    Message message = MessageMapper.mapToMessage(messageDto);
	    message.setChat(networkChat);
	    return message;
	}).collect(Collectors.toList()));
	return NetworkChatMapper.mapToNetworkChatDto(networkChatRepository.save(networkChat));
    }

    @Override
    public void deleteNetworkChat(Long networkChatId) {
	networkChatRepository.findById(networkChatId)
		.orElseThrow(() -> new ResourceNotFoundException("Chat not found: " + networkChatId));
	networkChatRepository.deleteById(networkChatId);
    }

    @Override
    public List<NetworkChatDto> getAllUserChats(Long userId) {
	userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	List<Network> networks = networkRepository.findAllUserNetworks(userId);
	List<NetworkChat> chats = networkChatRepository
		.findAllUserActiveChats(networks.stream().map(network -> network.getId()).collect(Collectors.toList()));
	return chats.stream().map(NetworkChatMapper::mapToNetworkChatDto).collect(Collectors.toList());
    }
}
