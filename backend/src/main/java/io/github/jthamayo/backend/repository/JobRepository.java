package io.github.jthamayo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
