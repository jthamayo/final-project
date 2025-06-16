package io.github.jthamayo.backend.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.github.jthamayo.backend.entity.base.BaseEntity;
import io.github.jthamayo.backend.entity.enums.AllergyType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dependents")
public class Dependent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Boolean isAllergic;

    @ElementCollection(targetClass = AllergyType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "dependent_allergies", joinColumns = @JoinColumn(name = "dependent_id"))
    @Column(name = "allergy")
    private List<AllergyType> allergies = new ArrayList<>();

    private Boolean isSpecialNeeds;

    private LocalDate birthDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guardian_id")
    private User guardian;

////////////////////////////////////////GETTERS&SETTERS//////////////////////////////////////////

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

    public Boolean isAllergic() {
	return isAllergic;
    }

    public void setAllergic(Boolean isAllergic) {
	this.isAllergic = isAllergic;
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

    public LocalDate getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
	this.birthDate = birthDate;
    }

    public User getGuardian() {
	return guardian;
    }

    public void setGuardian(User guardian) {
	this.guardian = guardian;
    }

////////////////////////////////////////////// CONSTRUCTOR///////////////////////////////

    public Dependent() {

    }

    public Dependent(Long id, String firstName, String lastName, Boolean isAllergic, List<AllergyType> allergies,
	    Boolean isSpecialNeeds, LocalDate birthDate, User guardian) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.isAllergic = isAllergic;
	this.allergies = allergies;
	this.isSpecialNeeds = isSpecialNeeds;
	this.birthDate = birthDate;
	this.guardian = guardian;
    }

    public Dependent(Long id, String firstName, String lastName, Boolean isAllergic, List<AllergyType> allergies,
	    Boolean isSpecialNeeds, LocalDate birthDate) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.isAllergic = isAllergic;
	this.allergies = allergies;
	this.isSpecialNeeds = isSpecialNeeds;
	this.birthDate = birthDate;
    }

}
