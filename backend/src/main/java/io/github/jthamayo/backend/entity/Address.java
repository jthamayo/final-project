package io.github.jthamayo.backend.entity;

import io.github.jthamayo.backend.entity.enums.AddressType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String zip;
    private String country;
    private int number;
    @Column(nullable = false)
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

    public int getNumber() {
	return number;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    public AddressType getType() {
	return type;
    }

    public void setAddressType(AddressType type) {
	this.type = type;
    }

    ///////////////////// CONSTRUCTOR////////////

    public Address() {

    }

    public Address(Long id, String city, String street, String zip, String country, int number, AddressType type) {
	this.id = id;
	this.city = city;
	this.street = street;
	this.zip = zip;
	this.country = country;
	this.number = number;
	this.type = type;
    }

    public Address(String city, String street, String zip, String country, int number, AddressType type) {
	this.city = city;
	this.street = street;
	this.zip = zip;
	this.country = country;
	this.number = number;
	this.type = type;
    }

}
