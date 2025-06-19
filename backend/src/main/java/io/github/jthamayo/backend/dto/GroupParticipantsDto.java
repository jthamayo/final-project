package io.github.jthamayo.backend.dto;

import java.util.List;

public class GroupParticipantsDto {

    public Long id;
    public List<UserSummary> users;

    public List<UserSummary> getUsers() {
	return users;
    }

    public Long getId() {
	return id;
    }

    public void setUsers(List<UserSummary> users) {
	this.users = users;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public GroupParticipantsDto(Long id, List<UserSummary> users) {
	this.id = id;
	this.users = users;
    }

}
