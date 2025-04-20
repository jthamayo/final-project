package io.github.jthamayo.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jthamayo.backend.dto.GroupDto;
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
}
