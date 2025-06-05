package io.github.jthamayo.backend.dto;

public class JobDto {

    private Long id;
    private Long addressId;
    private Long userId;
    private Boolean irregular;
    private Boolean nocturnal;

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
	return irregular;
    }

    public void setIrregular(Boolean irregular) {
	this.irregular = irregular;
    }

    public Boolean isNocturnal() {
	return nocturnal;
    }

    public void setNocturnal(Boolean nocturnal) {
	this.nocturnal = nocturnal;
    }

    ////////////////////// CONSTRUCTORS////////////////////////////

    public JobDto() {

    }

    public JobDto(Long id, Long addressId, Long userId, Boolean irregular, Boolean nocturnal) {
	this.id = id;
	this.addressId = addressId;
	this.userId = userId;
	this.irregular = irregular;
	this.nocturnal = nocturnal;
    }

    public JobDto(Long addressId, Long userId, Boolean irregular, Boolean nocturnal) {
	this.addressId = addressId;
	this.userId = userId;
	this.irregular = irregular;
	this.nocturnal = nocturnal;
    }

}
