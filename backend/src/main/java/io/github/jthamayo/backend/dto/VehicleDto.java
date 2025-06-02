package io.github.jthamayo.backend.dto;

public class VehicleDto {

    private Long id;
    private String licensePlate;
    private String model;
    private String color;
    private String chassisNumber;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getLicensePlate() {
	return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
	this.licensePlate = licensePlate;
    }

    public String getModel() {
	return model;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public String getColor() {
	return color;
    }

    public void setColor(String color) {
	this.color = color;
    }

    public String getChassisNumber() {
	return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
	this.chassisNumber = chassisNumber;
    }

    public VehicleDto() {
    }

    public VehicleDto(Long id, String licensePlate, String model, String color, String chassisNumber) {
	this.id = id;
	this.licensePlate = licensePlate;
	this.model = model;
	this.color = color;
	this.chassisNumber = chassisNumber;
    }

}
