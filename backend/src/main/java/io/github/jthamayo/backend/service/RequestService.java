package io.github.jthamayo.backend.service;

import java.util.List;
import java.util.Optional;

import io.github.jthamayo.backend.dto.RequestDto;
import io.github.jthamayo.backend.dto.RequestDetailsDto;

public interface RequestService {

    RequestDto createRequest(RequestDto requestDto);

    RequestDto getRequestById(Long requestId);

    RequestDto updateRequest(Long requestId, RequestDto requestDto);

    void deleteRequest(Long requestId);

    List<RequestDto> getAllRequests();

    RequestDto rejectRequest(Long requestId, Long userId);

    RequestDto acceptRequest(Long requestId, Long userId);

    RequestDto sendUserRequest(Long senderId, Long receiverId);

    List<RequestDetailsDto> getPendingReceivedRequests(Long userId);

    List<RequestDetailsDto> getPendingSentRequests(Long userId);
    
    Optional<RequestDto> getPendingRequestBetweenUsers(Long senderId, Long receiverId);
}
