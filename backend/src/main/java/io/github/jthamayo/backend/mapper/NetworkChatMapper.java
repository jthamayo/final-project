package io.github.jthamayo.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import io.github.jthamayo.backend.dto.NetworkChatDto;
import io.github.jthamayo.backend.entity.NetworkChat;

public class NetworkChatMapper {

    public static NetworkChatDto mapToNetworkChatDto(NetworkChat network) {
	return new NetworkChatDto(network.getId(),
		List.of(network.getNetwork().getUser1().getUsername(), network.getNetwork().getUser2().getUsername()),
		network.getMessages().stream().map(MessageMapper::mapToMessageDto).collect(Collectors.toList()));
    }
}
