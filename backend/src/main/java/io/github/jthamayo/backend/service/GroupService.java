package io.github.jthamayo.backend.service;

import java.util.List;

import io.github.jthamayo.backend.dto.GroupDto;
import io.github.jthamayo.backend.dto.UserDto;

public interface GroupService {

    GroupDto createGroup(GroupDto groupDto);

    GroupDto getGroupById(Long groupId);

    List<UserDto> getAllUsers(Long groupId);
    
    List<GroupDto> getAllGroups();

    GroupDto updateGroup(Long groupId, GroupDto groupDto);

    void deleteGroup(Long groupId);
    
    GroupDto removeUserFromGroup(Long groupId, Long userId);
}
