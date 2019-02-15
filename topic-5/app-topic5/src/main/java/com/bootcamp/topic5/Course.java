package com.bootcamp.topic5;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("course")
public class Course {

  @Id
  private ObjectId id;

  private Long idCourse;

  @Reference
  private Teacher teacher;

  private String name;

  public Course(Long idCourse, Teacher teacher, String name) {
    this.idCourse = idCourse;
    this.teacher = teacher;
    this.name = name;
  }

  public Course() {}

  public ObjectId getId() {
    return id;
  }

  public Long getIdCourse() {
    return idCourse;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public String getName() {
    return name;
  }
}
