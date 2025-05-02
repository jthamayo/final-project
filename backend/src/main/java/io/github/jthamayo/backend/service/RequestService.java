package io.github.jthamayo.backend.service;

import java.util.List;

import io.github.jthamayo.backend.dto.RequestDto;

public interface RequestService {

    RequestDto createRequest(RequestDto requestDto);

    RequestDto getRequestById(Long requestId);

    RequestDto updateRequest(Long requestId, RequestDto requestDto);

    void deleteRequest(Long requestId);

    List<RequestDto> getAllRequests();

    RequestDto rejectRequest(Long requestId, Long userId);

    RequestDto acceptRequest(Long requestId, Long userId);

    RequestDto sendUserRequest(Long senderId, Long receiverId);

    List<RequestDto> getPendingReceivedRequests(Long userId);

    List<RequestDto> getPendingSentRequests(Long userId);
}
