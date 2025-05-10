package io.github.jthamayo.backend.dto;

import io.github.jthamayo.backend.entity.enums.RoleType;

public class RoleDto {

    private Long id;

    private RoleType name;

    /////////////////// GETTERS&SETTER //////////////////////

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public RoleType getName() {
	return name;
    }

    public void setName(RoleType name) {
	this.name = name;
    }

    //////////////////////// CONSTRUCTOR /////////////////////////

    public RoleDto() {

    }

    public RoleDto(RoleType name) {
	this.name = name;
    }

    public RoleDto(Long id, RoleType name) {
	this.id = id;
	this.name = name;
    }
}
