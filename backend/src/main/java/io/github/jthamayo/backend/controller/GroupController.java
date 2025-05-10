package io.github.jthamayo.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jthamayo.backend.dto.GroupDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
	this.groupService = groupService;

    }

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
	GroupDto savedGroup = groupService.createGroup(groupDto);
	return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable("id") Long groupId) {
	GroupDto group = groupService.getGroupById(groupId);
	return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
	List<GroupDto> groups = groupService.getAllGroups();
	return ResponseEntity.ok(groups);
    }

    @GetMapping("{id}/users")
    public ResponseEntity<List<UserDto>> getGroupUsers(@PathVariable("id") Long groupId) {
	List<UserDto> groupUsers = groupService.getAllUsers(groupId);
	return ResponseEntity.ok(groupUsers);
    }

    @PutMapping("{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable("id") Long groupId, @RequestBody GroupDto groupDto) {
	GroupDto updatedGroup = groupService.updateGroup(groupId, groupDto);
	return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable("id") Long groupId) {
	groupService.deleteGroup(groupId);
	return ResponseEntity.ok("The group has been successfully deleted");
    }

    @DeleteMapping("{groupId}/users/{userId}")
    public ResponseEntity<GroupDto> removeUser(@PathVariable Long groupId, @PathVariable Long userId) {
	GroupDto group = groupService.removeUserFromGroup(groupId, userId);
	return ResponseEntity.ok(group);

    }
}
