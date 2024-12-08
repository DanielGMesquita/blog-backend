package com.danielmesquita.blogapi.repositories;

import com.danielmesquita.blogapi.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {}
