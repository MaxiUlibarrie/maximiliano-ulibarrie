package com.bootcamp.topic5;

import java.time.LocalDate;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("student")
public class Student {

  @Id
  private ObjectId id;

  private Long idStudent;
  private String firstName;
  private String lastName;
  private long registrationNumber;
  private LocalDate dateOfBirt;

  public Student(Long idStudent, String firstName, String lastName, long registrationNumber,
      LocalDate dateOfBirt) {
    this.idStudent = idStudent;
    this.firstName = firstName;
    this.lastName = lastName;
    this.registrationNumber = registrationNumber;
    this.dateOfBirt = dateOfBirt;
  }

  public Student() {}

  public ObjectId getId() {
    return id;
  }

  public Long getIdStudent() {
    return idStudent;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public long getRegistrationNumber() {
    return registrationNumber;
  }

  public LocalDate getDateOfBirt() {
    return dateOfBirt;
  }
}
