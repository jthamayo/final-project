package io.github.jthamayo.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import io.github.jthamayo.backend.dto.GroupDto;
import io.github.jthamayo.backend.dto.GroupParticipantsDto;
import io.github.jthamayo.backend.dto.UserSummary;
import io.github.jthamayo.backend.entity.Group;
import io.github.jthamayo.backend.entity.User;

public class GroupMapper {

    public static GroupDto mapToGroupDto(Group group) {
	List<Long> userIds = group.getUsers().stream().map(User::getId).collect(Collectors.toList());
	return new GroupDto(group.getId(), userIds);

    }

    public static Group mapToGroup(GroupDto groupDto) {
	Group group = new Group();
	group.setId(groupDto.getId());
	return group;

    }

    public static GroupParticipantsDto mapToGroupParticipantsDto(Group group) {
	List<UserSummary> participants = group.getUsers().stream().map(UserMapper::mapToUserSummary)
		.collect(Collectors.toList());
	return new GroupParticipantsDto(group.getId(), participants);
    }
}
