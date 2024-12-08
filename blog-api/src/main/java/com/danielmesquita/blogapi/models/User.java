package com.danielmesquita.blogapi.models;

import com.danielmesquita.blogapi.deserializers.CustomAuthorityDeserializer;
import com.danielmesquita.blogapi.enums.UserRole;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "user_email")
  private String email;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private UserRole userRole;

  @Column(name = "addresses")
  @OneToMany
  @Enumerated(EnumType.STRING)
  private List<Address> addresses;

  @Override
  @JsonDeserialize(using = CustomAuthorityDeserializer.class)
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.userRole == UserRole.ADMIN) {
      return List.of(
          new SimpleGrantedAuthority(UserRole.ADMIN.getRole()),
          new SimpleGrantedAuthority(UserRole.USER.getRole()));
    }
    return List.of(new SimpleGrantedAuthority(UserRole.USER.getRole()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
