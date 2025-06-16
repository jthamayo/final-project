package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.DependentDto;
import io.github.jthamayo.backend.entity.Dependent;

public class DependentMapper {

    public static DependentDto mapToDependentDto(Dependent dependent) {
	return new DependentDto(dependent.getId(), dependent.getFirstName(), dependent.getLastName(),
		dependent.getAllergies(), dependent.isAllergic(), dependent.isSpecialNeeds(), dependent.getBirthDate(),
		dependent.getGuardian().getId());
    }

    public static Dependent mapToDependent(DependentDto dependentDto) {
	return new Dependent(dependentDto.getId(), dependentDto.getFirstName(), dependentDto.getLastName(),
		dependentDto.isAllergic(), dependentDto.getAllergies(), dependentDto.isSpecialNeeds(),
		dependentDto.getBirthDate());
    }

}
