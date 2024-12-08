package com.danielmesquita.blogapi.controllers;

import com.danielmesquita.blogapi.models.Address;
import com.danielmesquita.blogapi.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/address")
public class AddressController {
  @Autowired private AddressService service;

  @Operation(summary = "Save a new address", description = "Saves a new address to the database")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Address saved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid address details provided")
      })
  @PostMapping("/save")
  private @ResponseBody Address save(@RequestBody Address address) {
    return service.save(address);
  }

  @Operation(summary = "Get all addresses", description = "Retrieves a list of all addresses")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the addresses")
      })
  @GetMapping(path = "/getAll")
  private @ResponseBody List<Address> getAllAddresses() {
    return service.getAll();
  }

  @Operation(summary = "Get an address", description = "Retrieves an address by its ID")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Address retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Address not found")
      })
  @GetMapping(path = "/get")
  private @ResponseBody Optional<Address> getAddress(@RequestParam final Long id) {
    return service.get(id);
  }

  @Operation(
      summary = "Update an address",
      description = "Updates the details of an existing address")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Address updated successfully"),
        @ApiResponse(responseCode = "404", description = "Address not found"),
        @ApiResponse(responseCode = "400", description = "Invalid address details provided")
      })
  @PutMapping(path = "/update")
  private @ResponseBody Address updateAddress(
      @RequestParam final Long id, @RequestBody Address address) {
    return service.update(id, address);
  }

  @Operation(summary = "Delete an address", description = "Deletes an address by its ID")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Address not found")
      })
  @DeleteMapping(path = "/delete")
  private ResponseEntity<?> delete(@RequestParam final Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
