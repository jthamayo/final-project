package io.github.jthamayo.backend.service;

import java.util.List;

import io.github.jthamayo.backend.dto.NetworkDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.dto.UserSummary;

public interface NetworkService {

    NetworkDto createNetwork(NetworkDto networkDto);

    NetworkDto getNetworkById(Long networkId);

    NetworkDto updateNetwork(Long networkId, NetworkDto networkDto);

    void deleteNetwork(Long networkId);

    NetworkDto getNetworkBetweenUsers(Long userId1, Long userId2);

    List<NetworkDto> getAllNetworks();

    List<UserSummary> getUserConnections(Long userId);
    
    List<UserSummary> getUnconnectedUsers(Long userId);

    List<UserDto> getGroupConnections(Long gropupId);

    List<UserDto> getUsersMutualConnections(Long userId1, Long userId2);

    List<UserDto> getUsersExclusiveConnections(Long userId1, Long userId2);

    List<UserSummary> getUserSecondaryConnections(Long userId);

    // TODO add service to return a List of users sorted by the
    // level of affinity (frequency of connections within group)

    // TODO add service to return a List of best professional matches

}
