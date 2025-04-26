package io.github.jthamayo.backend.dto;

import java.time.LocalDate;

public class NetworkDto {

    private Long id;
    private Long userId1;
    private Long userId2;
    private LocalDate dateStart;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getUserId1() {
	return userId1;
    }

    public void setUserId1(Long userId1) {
	this.userId1 = userId1;
    }

    public Long getUserId2() {
	return userId2;
    }

    public void setUserId2(Long userId2) {
	this.userId2 = userId2;
    }

    public LocalDate getDateStart() {
	return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
	this.dateStart = dateStart;
    }

    //////////////// CONSTRUCTOR/////////////////////

    public NetworkDto() {
    }
    
    public NetworkDto(Long id, Long userId1, Long userId2, LocalDate dateStart) {
	this.id = id;
	this.userId1 = Math.min(userId1, userId2);
	this.userId2 = Math.max(userId1, userId2);
	this.dateStart = dateStart;
    }

    public NetworkDto(Long userId1, Long userId2, LocalDate dateStart) {
	this.userId1 = Math.min(userId1, userId2);
	this.userId2 = Math.max(userId1, userId2);
	this.dateStart = dateStart;
    }

    public NetworkDto(Long userId1, Long userId2) {
	this.userId1 = Math.min(userId1, userId2);
	this.userId2 = Math.max(userId1, userId2);
    }

}
