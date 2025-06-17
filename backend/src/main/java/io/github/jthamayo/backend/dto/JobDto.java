package io.github.jthamayo.backend.dto;

public class JobDto {

    private Long id;
    private Long addressId;
    private Long userId;
    private Boolean isIrregular;
    private Boolean isNocturnal;

    ///////////////////////////// GETTERS&SETTERS//////////////////////////////

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getAddressId() {
	return addressId;
    }

    public void setAddressId(Long addressId) {
	this.addressId = addressId;
    }

    public Long getUserId() {
	return userId;
    }

    public void setUserId(Long userId) {
	this.userId = userId;
    }

    public Boolean isIrregular() {
	return isIrregular;
    }

    public void setIrregular(Boolean isIrregular) {
	this.isIrregular = isIrregular;
    }

    public Boolean isNocturnal() {
	return isNocturnal;
    }

    public void setNocturnal(Boolean isNocturnal) {
	this.isNocturnal = isNocturnal;
    }

    ////////////////////// CONSTRUCTORS////////////////////////////

    public JobDto() {

    }

    public JobDto(Long id, Long addressId, Long userId, Boolean isIrregular, Boolean isNocturnal) {
	this.id = id;
	this.addressId = addressId;
	this.userId = userId;
	this.isIrregular = isIrregular;
	this.isNocturnal = isNocturnal;
    }

    public JobDto(Long addressId, Long userId, Boolean isIrregular, Boolean isNocturnal) {
	this.addressId = addressId;
	this.userId = userId;
	this.isIrregular = isIrregular;
	this.isNocturnal = isNocturnal;
    }

}
