package io.github.jthamayo.backend.service;

import io.github.jthamayo.backend.dto.DependentDto;

public interface DependentService {

    DependentDto createDependent(DependentDto dependentDto);

    DependentDto updateDependent(Long dependentId, DependentDto dependentDto);

    DependentDto getDependentById(Long dependentId);
    
    void deleteDependent(Long dependentId);
}
