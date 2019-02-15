package com.bootcamp.topic5;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("assist")
public class Assist {

  @Id
  private ObjectId id;

  @Reference
  private Course course;

  @Reference
  private Student student;

  private int year;

  public Assist(Course course, Student student, int year) {
    this.course = course;
    this.student = student;
    this.year = year;
  }

  public Assist() {}

  public ObjectId getId() {
    return id;
  }

  public Course getCourse() {
    return course;
  }

  public Student getStudent() {
    return student;
  }

  public int getYear() {
    return year;
  }
}
