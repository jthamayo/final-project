package io.github.jthamayo.backend.entity;

import io.github.jthamayo.backend.entity.enums.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
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

    public Role() {

    }

    public Role(RoleType name) {
	this.name = name;
    }

    public Role(Long id, RoleType name) {
	this.id = id;
	this.name = name;
    }
}
