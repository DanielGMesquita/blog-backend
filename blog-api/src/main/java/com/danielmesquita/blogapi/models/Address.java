package com.danielmesquita.blogapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private Long addressId;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "country")
  private String country;

  @Column(name = "zip_code")
  private String zipCode;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
