package io.github.jthamayo.backend.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.RequestDto;
import io.github.jthamayo.backend.entity.Network;
import io.github.jthamayo.backend.entity.Request;
import io.github.jthamayo.backend.entity.enums.UserRequestStatus;
import io.github.jthamayo.backend.exception.InvalidOperationException;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.RequestMapper;
import io.github.jthamayo.backend.repository.NetworkRepository;
import io.github.jthamayo.backend.repository.RequestRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

    private UserRepository userRepository;
    private RequestRepository requestRepository;
    private NetworkRepository networkRepository;

    public RequestServiceImpl(UserRepository userRepository, RequestRepository requestRepository,
	    NetworkRepository networkRepository) {
	this.userRepository = userRepository;
	this.requestRepository = requestRepository;
	this.networkRepository = networkRepository;
    }

    @Override
    public RequestDto createRequest(RequestDto requestDto) {
	Request request = RequestMapper.mapToRequest(requestDto);
	request.setUserSender(userRepository.findById(requestDto.getUserSender())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + requestDto.getUserSender())));
	request.setUserReceiver(userRepository.findById(requestDto.getUserReceiver())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + requestDto.getUserReceiver())));
	request.setSentDate(LocalDate.now());
	return RequestMapper.mapToRequestDto(requestRepository.save(request));
    }

    @Override
    public RequestDto getRequestById(Long requestId) {
	Request request = requestRepository.findById(requestId)
		.orElseThrow(() -> new ResourceNotFoundException("Request not found: " + requestId));
	return RequestMapper.mapToRequestDto(request);
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
	return RequestMapper.mapToRequestDto(requestRepository.save(request));
    }

    @Override
    public void deleteRequest(Long requestId) {
	requestRepository.findById(requestId)
		.orElseThrow(() -> new ResourceNotFoundException("Request not found: " + requestId));
	requestRepository.deleteById(requestId);
    }

    @Override
    public List<RequestDto> getAllRequests() {
	List<Request> requests = requestRepository.findAll();
	return requests.stream().map(RequestMapper::mapToRequestDto).collect(Collectors.toList());
    }

    @Override
    public RequestDto sendUserRequest(Long senderId, Long receiverId) {
	Optional<Network> network = networkRepository.findNetworkBetweenUsers(senderId, receiverId);
	Optional<Request> pendingRequest = requestRepository.findPendingRequestBetweenUsers(senderId, receiverId);
	if (network.isPresent()) {
	    throw new InvalidOperationException("Users are already connected");
	}
	if (pendingRequest.isPresent()) {
	    throw new InvalidOperationException("Users already have a pending request");
	}
	if (senderId.equals(receiverId)) {
	    throw new InvalidOperationException("A user cannot send a request to themselves");
	}
	Request request = new Request();
	request.setUserSender(userRepository.findById(senderId)
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + senderId)));
	request.setUserReceiver(userRepository.findById(receiverId)
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + receiverId)));
	request.setSentDate(LocalDate.now());
	return RequestMapper.mapToRequestDto(requestRepository.save(request));
    }

    @Override
    public List<RequestDto> getPendingReceivedRequests(Long userId) {
	userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
	List<Request> requests = requestRepository.findUserPendingReceivedRequests(userId);
	return requests.stream().map(RequestMapper::mapToRequestDto).collect(Collectors.toList());
    }

    @Override
    public List<RequestDto> getPendingSentRequests(Long userId) {
	userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
	List<Request> requests = requestRepository.findUserPendingSentRequests(userId);
	return requests.stream().map(RequestMapper::mapToRequestDto).collect(Collectors.toList());
    }

    @Override
    public RequestDto rejectRequest(Long userId, Long requestId) {
	userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
	Request request = requestRepository.findById(requestId)
		.orElseThrow(() -> new ResourceNotFoundException("Request not found: " + requestId));
	if (!request.getUserReceiver().getId().equals(userId)) {
	    throw new InvalidOperationException("User cannot accept this request");
	}
	request.setStatus(UserRequestStatus.REJECTED);
	return RequestMapper.mapToRequestDto(requestRepository.save(request));
    }

    @Override
    public RequestDto acceptRequest(Long userId, Long requestId) {
	userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
	Request request = requestRepository.findById(requestId)
		.orElseThrow(() -> new ResourceNotFoundException("Request not found: " + requestId));
	if (!request.getUserReceiver().getId().equals(userId)) {
	    throw new InvalidOperationException("User cannot reject this request");
	}
	request.setStatus(UserRequestStatus.ACCEPTED);
	return RequestMapper.mapToRequestDto(requestRepository.save(request));
    }

    @Override
    public Optional<RequestDto> getPendingRequestBetweenUsers(Long senderId, Long receiverId) {
	Optional<Request> request = requestRepository.findPendingRequestBetweenUsers(senderId, receiverId);
	return request.map(RequestMapper::mapToRequestDto);
    }

}
