package io.github.jthamayo.backend.dto;

public class JobDto {

    private Long id;
    private Long addressId;
    private Long userId;
    private boolean irregular;
    private boolean nocturnal;

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

    public void setAddress(Long addressId) {
	this.addressId = addressId;
    }

    public Long getUser() {
	return userId;
    }

    public void setUser(Long userId) {
	this.userId = userId;
    }

    public boolean isIrregular() {
	return irregular;
    }

    public void setIrregular(boolean irregular) {
	this.irregular = irregular;
    }

    public boolean isNocturnal() {
	return nocturnal;
    }

    public void setNocturnal(boolean nocturnal) {
	this.nocturnal = nocturnal;
    }

    ////////////////////// CONSTRUCTORS////////////////////////////

    public JobDto() {

    }

    public JobDto(Long id, Long addressId, Long userId, boolean irregular, boolean nocturnal) {
	this.id = id;
	this.addressId = addressId;
	this.userId = userId;
	this.irregular = irregular;
	this.nocturnal = nocturnal;
    }

    public JobDto(Long addressId, Long userId, boolean irregular, boolean nocturnal) {
	this.addressId = addressId;
	this.userId = userId;
	this.irregular = irregular;
	this.nocturnal = nocturnal;
    }

}
