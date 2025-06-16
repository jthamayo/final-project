package io.github.jthamayo.backend.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.github.jthamayo.backend.entity.enums.AllergyType;
import jakarta.validation.constraints.NotBlank;

public class DependentDto {

    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private List<AllergyType> allergies = new ArrayList<>();

    private Boolean isSpecialNeeds;

    private Boolean isAllergic;

    private LocalDate birthDate;

    private Long guardianId;

////////////////////////////////////////////////////////////////////////

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public List<AllergyType> getAllergies() {
	return allergies;
    }

    public void setAllergies(List<AllergyType> allergies) {
	this.allergies = allergies;
    }

    public Boolean isSpecialNeeds() {
	return isSpecialNeeds;
    }

    public void setSpecialNeeds(Boolean isSpecialNeeds) {
	this.isSpecialNeeds = isSpecialNeeds;
    }

    public Boolean isAllergic() {
	return isAllergic;
    }

    public void setAllergic(Boolean isAllergic) {
	this.isAllergic = isAllergic;
    }

    public LocalDate getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
	this.birthDate = birthDate;
    }

    public Long getGuardianId() {
	return guardianId;
    }

    public void setGuardianId(Long guardianId) {
	this.guardianId = guardianId;
    }

    ///////////////////////////////////// CONSTRUCTOR///////////////////////////////////////////

    public DependentDto() {

    }

    public DependentDto(Long id, @NotBlank(message = "First name is required") String firstName,
	    @NotBlank(message = "Last name is required") String lastName, List<AllergyType> allergies,
	    Boolean isAllergic, Boolean isSpecialNeeds, LocalDate birthDate, Long guardianId) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.allergies = allergies;
	this.isSpecialNeeds = isSpecialNeeds;
	this.isAllergic = isAllergic;
	this.birthDate = birthDate;
	this.guardianId = guardianId;
    }

}
