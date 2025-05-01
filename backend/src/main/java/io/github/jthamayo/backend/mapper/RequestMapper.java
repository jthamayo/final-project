package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.RequestDto;
import io.github.jthamayo.backend.entity.Request;

public class RequestMapper {

    public static RequestDto mapToRequestDto(Request request) {
	return new RequestDto(request.getId(), request.getUserSender().getId(), request.getUserReceiver().getId(),
		request.getSentDate(), request.getStatus());
    }

    public static Request mapToRequest(RequestDto requestDto) {
	return new Request(requestDto.getId(), requestDto.getSentDate(), requestDto.getStatus());
    }
}
