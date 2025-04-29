package io.github.jthamayo.backend.service;

import io.github.jthamayo.backend.dto.RequestDto;

public interface RequestService {

    RequestDto createRequest(RequestDto requestDto);

    RequestDto getRequestById(Long requestId);

    RequestDto updateRequest(Long requestId, RequestDto requestDto);

    void deleteRequest(Long requestId);
}
