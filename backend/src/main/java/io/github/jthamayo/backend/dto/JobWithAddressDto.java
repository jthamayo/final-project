package io.github.jthamayo.backend.dto;

public class JobWithAddressDto {
    private JobDto job;
    private AddressDto address;

    public JobDto getJob() {
	return job;
    }

    public void setJob(JobDto job) {
	this.job = job;
    }

    public AddressDto getAddress() {
	return address;
    }

    public void setAddress(AddressDto address) {
	this.address = address;
    }

    public JobWithAddressDto() {

    }

    public JobWithAddressDto(JobDto job, AddressDto address) {
	super();
	this.job = job;
	this.address = address;
    }
}
