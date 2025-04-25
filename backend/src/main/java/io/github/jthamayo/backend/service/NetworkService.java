package io.github.jthamayo.backend.service;

import java.util.List;
import java.util.Optional;

import io.github.jthamayo.backend.dto.NetworkDto;
import io.github.jthamayo.backend.dto.UserDto;

public interface NetworkService {

    NetworkDto createNetwork(NetworkDto networkDto);

    NetworkDto getNetworkById(Long networkId);

    NetworkDto updateNetwork(Long networkId, NetworkDto networkDto);

    void deleteNetwork(Long networkId);

    NetworkDto getNetworkBetweenUsers(Long userId1, Long userId2);

    List<NetworkDto> getAllNetworks();

    List<UserDto> getUserConnections(Long userId);

    List<UserDto> getGroupConnections(Long gropupId);

    List<UserDto> getUsersMutualConnections(Long userId1, Long userId2);

    List<UserDto> getUsersExclusiveConnections(Long userId1, Long userId2);

    List<UserDto> getGroupMutualConnections(Long groupId);

    List<UserDto> getUserSecondaryConnections(Long userId);

}
