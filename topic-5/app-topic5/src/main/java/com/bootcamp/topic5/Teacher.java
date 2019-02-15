package com.bootcamp.topic5;

import java.time.LocalDate;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("teacher")
public class Teacher {

  @Id
  private ObjectId id;

  private Long idTeacher;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;

  public Teacher(Long idTeacher, String firstName, String lastName, LocalDate dateOfBirth) {
    this.idTeacher = idTeacher;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
  }

  public Teacher() {}

  public ObjectId getId() {
    return id;
  }

  public Long getIdTeacher() {
    return idTeacher;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }
}
