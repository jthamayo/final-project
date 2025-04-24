package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.NetworkDto;
import io.github.jthamayo.backend.entity.Network;

public class NetworkMapper {

    public static NetworkDto mapToNetworkDto(Network network) {
	return new NetworkDto(network.getId(), network.getUser1().getId(), network.getUser2().getId(),
		network.getDateStart());
    }

    public static Network mapToNetwork(NetworkDto networkDto) {
	return new Network(networkDto.getId(), networkDto.getDateStart());
    }
}
