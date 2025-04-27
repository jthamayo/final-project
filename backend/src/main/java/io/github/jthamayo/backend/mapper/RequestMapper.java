package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.RequestDto;
import io.github.jthamayo.backend.entity.Request;

public class RequestMapper {

    public static RequestDto mapToNetworkDto(Request request) {
	return new RequestDto(request.getId(), request.getUserSender().getId(), request.getUserReceiver().getId(),
		request.getSentDate());
    }

    public static Request mapToNetwork(RequestDto requestDto) {
	return new Request(requestDto.getId(), requestDto.getSentDate());
    }
}
