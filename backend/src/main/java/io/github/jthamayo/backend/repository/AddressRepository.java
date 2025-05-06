package io.github.jthamayo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
