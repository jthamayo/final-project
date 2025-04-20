package io.github.jthamayo.backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonManagedReference
    @OneToMany(mappedBy = "group", orphanRemoval = true)
    private List<User> users;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public List<User> getUsers() {
	return users;
    }

    public void setUsers(List<User> users) {
	this.users = users;
    }

    ///////////////////// CONTRUCTOR/////////////////////////

    public Group() {

    }

    public Group(Long id, List<User> users) {
	this.id = id;
	this.users = users;
    }

    public Group(List<User> users) {
	this.users = users;
    }

}
