package io.github.jthamayo.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_networks")
public class Network {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_user_1", nullable = false)
    private User user1;
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_user_2", nullable = false)
    private User user2;
    private LocalDate dateStart;

    //////////////////////// GETTERS&SETTERS ////////////////////////////

    public Long getId() {
	return id;
    }

    public User getUser1() {
	return user1;
    }

    public User getUser2() {
	return user2;
    }

    public LocalDate getDateStart() {
	return dateStart;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setUser1(User user1) {
	this.user1 = user1;
    }

    public void setUser2(User user2) {
	this.user2 = user2;
    }

    public void setDateStart(LocalDate dateStart) {
	this.dateStart = dateStart;
    }

    //////////////////////// CONSTRUCTORS ////////////////////////////

    public Network(Long id, User user1, User user2, LocalDate dateStart) {
	this.id = id;

	if (user1.getId() < user2.getId()) {
	    this.user1 = user1;
	    this.user2 = user2;
	} else {
	    this.user1 = user2;
	    this.user2 = user1;
	}

	this.dateStart = dateStart;
    }

    public Network(User user1, User user2, LocalDate dateStart) {

	if (user1.getId() < user2.getId()) {
	    this.user1 = user1;
	    this.user2 = user2;
	} else {
	    this.user1 = user2;
	    this.user2 = user1;
	}

	this.dateStart = dateStart;
    }

}
