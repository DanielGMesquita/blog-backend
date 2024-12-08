package com.danielmesquita.blogapi.services.impl;

import com.danielmesquita.blogapi.models.Address;
import com.danielmesquita.blogapi.repositories.AddressRepository;
import com.danielmesquita.blogapi.services.AddressService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
  @Autowired private AddressRepository addressRepository;

  public Address save(Address address) {
    try {
      Address newAddress = new Address();
      setAddressData(address, newAddress);
      return addressRepository.save(newAddress);
    } catch (DataIntegrityViolationException e) {
      throw new RuntimeException("Address already exists");
    }
  }

  @Override
  public List<Address> getAll() {
    return addressRepository.findAll();
  }

  @Override
  public Optional<Address> get(Long id) {
    return addressRepository.findById(id);
  }

  @Override
  public Address update(Long id, Address newAddressData) {
    Address addressToEdit =
        addressRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Address not found"));

    setAddressData(newAddressData, addressToEdit);

    return addressRepository.save(addressToEdit);
  }

  @Override
  public void delete(Long id) {
    addressRepository.deleteById(id);
  }

  private void setAddressData(Address newAddressData, Address addressToFillData) {
    addressToFillData.setStreet(newAddressData.getStreet());
    addressToFillData.setCity(newAddressData.getCity());
    addressToFillData.setState(newAddressData.getState());
    addressToFillData.setCountry(newAddressData.getCountry());
    addressToFillData.setZipCode(newAddressData.getZipCode());
    addressToFillData.setUser(newAddressData.getUser());
  }
}
