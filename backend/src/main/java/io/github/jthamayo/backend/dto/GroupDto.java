package io.github.jthamayo.backend.dto;

import java.util.List;

public class GroupDto {

    private Long id;
    private List<Long> userIds;

    ///////////////////////////////////// GETTERS&SETTERS//////////////

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public List<Long> getUserIds() {
	return userIds;
    }

    public void setUserIds(List<Long> userIds) {
	this.userIds = userIds;
    }

    //////////////////////////////////////////// CONSTRUCTORS/////////////////

    public GroupDto() {

    }

    public GroupDto(Long id, List<Long> userIds) {
	this.id = id;
	this.userIds = userIds;
    }

    public GroupDto(List<Long> userIds) {
	this.userIds = userIds;
    }

}
