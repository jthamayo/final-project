package io.github.jthamayo.backend.dto;

import io.github.jthamayo.backend.entity.enums.AddressType;

public class AddressDto {

    private Long id;
    private String city;
    private String street;
    private String zip;
    private String country;
    private Integer number;
    private AddressType type;

    ///////////////////// GETTES&SETTERS////////////

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getZip() {
	return zip;
    }

    public void setZip(String zip) {
	this.zip = zip;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public Integer getNumber() {
	return number;
    }

    public void setNumber(Integer number) {
	this.number = number;
    }

    public AddressType getType() {
	return type;
    }

    public void setType(AddressType type) {
	this.type = type;
    }

    ///////////////////// CONSTRUCTOR////////////

    public AddressDto() {

    }

    public AddressDto(Long id, String city, String street, String zip, String country, Integer number, AddressType type) {
	this.id = id;
	this.city = city;
	this.street = street;
	this.zip = zip;
	this.country = country;
	this.number = number;
	this.type = type;
    }

    public AddressDto(String city, String street, String zip, String country, Integer number, AddressType type) {
	this.city = city;
	this.street = street;
	this.zip = zip;
	this.country = country;
	this.number = number;
	this.type = type;
    }
}
