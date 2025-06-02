package io.github.jthamayo.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "license_plate")
    private String licensePlate;
    private String model;
    private String color;
    @Column(name = "chassis_number")
    private String chassisNumber;

    ///////////////////////////////////////////////////////////

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

    ///////////////////////////////////////////////////////////

    public Vehicle() {

    }

    public Vehicle(Long id, String licensePlate, String model, String color, String chassisNumber) {
	this.id = id;
	this.licensePlate = licensePlate;
	this.model = model;
	this.color = color;
	this.chassisNumber = chassisNumber;
    }
}
