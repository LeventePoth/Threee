package com.huli.greenfoxacademy.devma.Partners;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Partner {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private String companyName;
  private String email;
  private String password;
  private boolean activated;

  public Partner() {
  }

  public Partner(String firstName, String lastName, String companyName, String email, String password, boolean activated) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.companyName = companyName;
    this.email = email;
    this.password = password;
    this.activated = activated;
  }
}
