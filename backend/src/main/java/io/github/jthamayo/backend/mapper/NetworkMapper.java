package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.NetworkDto;
import io.github.jthamayo.backend.entity.Network;

public class NetworkMapper {

    public static NetworkDto mapToNetworkDto(Network network) {
	return new NetworkDto(network.getId(), UserMapper.mapToUserDto(network.getUser1()),
		UserMapper.mapToUserDto(network.getUser2()), network.getDateStart());
    }

    public static Network mapToNetwork(NetworkDto networkDto) {
	return new Network(networkDto.getId(), UserMapper.mapToUser(networkDto.getUser1()),
		UserMapper.mapToUser(networkDto.getUser2()), networkDto.getDateStart());
    }
}
