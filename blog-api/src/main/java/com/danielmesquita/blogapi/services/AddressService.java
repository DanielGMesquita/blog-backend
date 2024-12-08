package com.danielmesquita.blogapi.services;

import com.danielmesquita.blogapi.models.Address;
import java.util.List;
import java.util.Optional;

public interface AddressService {
  Address save(Address address);

  List<Address> getAll();

  Optional<Address> get(Long id);

  Address update(Long id, Address address);

  void delete(Long id);
}
