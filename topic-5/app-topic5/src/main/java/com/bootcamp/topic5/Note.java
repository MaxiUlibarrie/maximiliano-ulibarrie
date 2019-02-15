package com.bootcamp.topic5;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("note")
public class Note {

  @Id
  private ObjectId id;

  @Reference
  private Course course;

  @Reference
  private Student student;

  private int firtPartialNote;
  private int secondPartialNote;
  private int thirdPartialNote;
  private int finalNote;

  public Note(Course course, Student student, int firtPartialNote, int secondPartialNote,
      int thirdPartialNote, int finalNote) {
    this.course = course;
    this.student = student;
    this.firtPartialNote = firtPartialNote;
    this.secondPartialNote = secondPartialNote;
    this.thirdPartialNote = thirdPartialNote;
    this.finalNote = finalNote;
  }

  public Note () {}

  public ObjectId getId() {
    return id;
  }

  public Course getCourse() {
    return course;
  }

  public Student getStudent() {
    return student;
  }

  public int getFirtPartialNote() {
    return firtPartialNote;
  }

  public int getSecondPartialNote() {
    return secondPartialNote;
  }

  public int getThirdPartialNote() {
    return thirdPartialNote;
  }

  public int getFinalNote() {
    return finalNote;
  }
}
