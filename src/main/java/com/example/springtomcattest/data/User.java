package com.example.springtomcattest.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {

  @Id
  @GeneratedValue
  private long id;
  private String userName;
  private String firstName;
  private String lastName;
}
