package io.github.jthamayo.backend.dto;

import java.time.LocalDate;

public class NetworkDto {

    private Long id;
    private UserDto user1;
    private UserDto user2;
    private LocalDate dateStart;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public UserDto getUser1() {
	return user1;
    }

    public void setUser1(UserDto user1) {
	this.user1 = user1;
    }

    public UserDto getUser2() {
	return user2;
    }

    public void setUser2(UserDto user2) {
	this.user2 = user2;
    }

    public LocalDate getDateStart() {
	return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
	this.dateStart = dateStart;
    }

    //////////////// CONSTRUCTOR/////////////////////

    public NetworkDto(Long id, UserDto user1, UserDto user2, LocalDate dateStart) {
	this.id = id;
	this.user1 = user1;
	this.user2 = user2;
	this.dateStart = dateStart;
    }

    public NetworkDto(UserDto user1, UserDto user2, LocalDate dateStart) {
	this.user1 = user1;
	this.user2 = user2;
	this.dateStart = dateStart;
    }

}
