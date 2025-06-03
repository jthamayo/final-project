package io.github.jthamayo.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.jthamayo.backend.dto.RequestDto;
import io.github.jthamayo.backend.entity.Request;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.mapper.RequestMapper;
import io.github.jthamayo.backend.repository.RequestRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.impl.RequestServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {

    private User user1;
    private User user2;

    @Mock
    private RequestRepository requestRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RequestServiceImpl requestService;

    @Nested
    class TestCreateRequest {

	@BeforeEach
	void setUp() {
	    user1 = new User(2L, "testUser1", "lastname", "username", "email", "phoneNumber");
	    user2 = new User(3L, "testUser2", "lastname", "username", "email", "phoneNumber");
	    when(userRepository.findById(2L)).thenReturn(Optional.of(user1));
	    when(userRepository.findById(3L)).thenReturn(Optional.of(user2));
	}

	@Test
	void testCreateRequest() {
	    RequestDto requestDto = new RequestDto(user1.getId(), user2.getId());
	    RequestDto savedRequestDto = new RequestDto(user1.getId(), user2.getId());
	    Request request = new Request(user1, user2);
	    Request savedRequest = new Request(user1, user2);

	    try (MockedStatic<RequestMapper> mockMapper = mockStatic(RequestMapper.class)) {
		mockMapper.when(() -> RequestMapper.mapToRequest(requestDto)).thenReturn(request);
		when(requestRepository.save(request)).thenReturn(savedRequest);
		mockMapper.when(() -> RequestMapper.mapToRequestDto(savedRequest)).thenReturn(savedRequestDto);
		RequestDto result = requestService.createRequest(requestDto);
		assertNotNull(result);
		assertEquals(savedRequestDto.getUserSender(), result.getUserSender());
		assertEquals(savedRequestDto.getUserReceiver(), result.getUserReceiver());

	    }
	}

	@Test
	void testUpdateRequest() {
	    Long requestId = 1L;
	    RequestDto updatedRequestDto = new RequestDto(2L, 3L);
	    RequestDto savedRequestDto = new RequestDto(3L, 2L);
	    Request request = new Request(user2, user1);
	    Request savedRequest = new Request(user1, user2);

	    when(requestRepository.findById(requestId)).thenReturn(Optional.of(request));
	    when(requestRepository.save(request)).thenReturn(savedRequest);

	    try (MockedStatic<RequestMapper> mockMapper = mockStatic(RequestMapper.class)) {
		mockMapper.when(() -> RequestMapper.mapToRequestDto(savedRequest)).thenReturn(savedRequestDto);

		RequestDto result = requestService.updateRequest(requestId, updatedRequestDto);
		assertNotNull(result);
		assertNotEquals(result.getUserSender(), request.getUserSender());
	    }
	}
    }

    @Nested
    class TestRequest {

	@BeforeEach
	void setUp() {
	    user1 = new User(2L, "testUser1", "lastname", "username", "email", "phoneNumber");
	    user2 = new User(3L, "testUser2", "lastname", "username", "email", "phoneNumber");
	}

	@Test
	void testGetRequestById() {
	    Long requestId = 1L;
	    Request request = new Request(user2, user1);
	    RequestDto RequestDto = new RequestDto(2L, 3L);

	    when(requestRepository.findById(requestId)).thenReturn(Optional.of(request));

	    try (MockedStatic<RequestMapper> mockMapper = mockStatic(RequestMapper.class)) {
		mockMapper.when(() -> RequestMapper.mapToRequestDto(request)).thenReturn(RequestDto);

		RequestDto result = requestService.getRequestById(requestId);
		assertNotNull(result);
	    }
	}

	@Test
	void deleteRequest() {
	    Long requestId = 1L;
	    Request request = new Request(user2, user1);

	    when(requestRepository.findById(requestId)).thenReturn(Optional.of(request));
	    requestService.deleteRequest(requestId);
	    verify(requestRepository).deleteById(requestId);
	}

	@Test
	void getAllRequests() {
	    when(requestRepository.findAll()).thenReturn(
		    Stream.of(new Request(user1, user2), new Request(user2, user1)).collect(Collectors.toList()));
	    try (MockedStatic<RequestMapper> mockMapper = mockStatic(RequestMapper.class)) {
		mockMapper.when(() -> RequestMapper.mapToRequestDto(any(Request.class))).thenAnswer(invocation -> {
		    Request req = invocation.getArgument(0);
		    return new RequestDto(req.getUserSender().getId(), req.getUserReceiver().getId());
		});
		List<RequestDto> result = requestService.getAllRequests();
		assertNotNull(result);
		assertEquals(2, result.size());
	    }
	}
    }

}
