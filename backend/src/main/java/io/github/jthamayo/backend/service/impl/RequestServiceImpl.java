package io.github.jthamayo.backend.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.RequestDto;
import io.github.jthamayo.backend.entity.Request;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.RequestMapper;
import io.github.jthamayo.backend.repository.RequestRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

    private UserRepository userRepository;
    private RequestRepository requestRepository;

    public RequestServiceImpl(UserRepository userRepository, RequestRepository requestRepository) {
	this.userRepository = userRepository;
	this.requestRepository = requestRepository;
    }

    @Override
    public RequestDto createRequest(RequestDto requestDto) {
	Request request = new Request();
	request.setUserSender(userRepository.findById(requestDto.getUserSender())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + requestDto.getUserSender())));
	request.setUserReceiver(userRepository.findById(requestDto.getUserReceiver())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + requestDto.getUserReceiver())));
	request.setSentDate(LocalDate.now());
	return RequestMapper.mapToNetworkDto(requestRepository.save(request));
    }

    @Override
    public RequestDto getRequestById(Long requestId) {
	Request request = requestRepository.findById(requestId)
		.orElseThrow(() -> new ResourceNotFoundException("Request not found: " + requestId));
	return RequestMapper.mapToNetworkDto(request);
    }

    @Override
    public RequestDto updateRequest(Long requestId, RequestDto requestDto) {
	Request request = requestRepository.findById(requestId)
		.orElseThrow(() -> new ResourceNotFoundException("Request not found: " + requestId));
	request.setUserSender(userRepository.findById(requestDto.getUserSender())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + requestDto.getUserSender())));
	request.setUserReceiver(userRepository.findById(requestDto.getUserReceiver())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + requestDto.getUserReceiver())));
	request.setSentDate(LocalDate.now());
	return RequestMapper.mapToNetworkDto(request);
    }

    @Override
    public void deleteRequest(Long requestId) {
	requestRepository.findById(requestId)
		.orElseThrow(() -> new ResourceNotFoundException("Network not found: " + requestId));
	requestRepository.deleteById(requestId);
    }

    @Override
    public List<RequestDto> getAllRequests() {
	List<Request> requests = requestRepository.findAll();
	return requests.stream().map(RequestMapper::mapToNetworkDto).collect(Collectors.toList());
    }

}
