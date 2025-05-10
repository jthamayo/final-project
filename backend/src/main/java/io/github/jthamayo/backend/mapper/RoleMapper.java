package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.RoleDto;
import io.github.jthamayo.backend.entity.Role;

public class RoleMapper {

    public static RoleDto mapToRoleDto(Role role) {
	return new RoleDto(role.getId(), role.getName());
    }

    public static Role mapToRole(RoleDto roleDto) {
	return new Role(roleDto.getId(), roleDto.getName());
    }
}
