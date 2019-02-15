package com.bootcamp.topic5;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import java.time.LocalTime;

@Entity("schedule")
public class Schedule {

  @Id
  private ObjectId id;

  private Long idSchedule;

  @Reference
  private Course course;

  private int dayOfWeek;
  private LocalTime timeFrom;
  private LocalTime timeTo;

  public Schedule(Long idSchedule, Course course, int dayOfWeek, LocalTime timeFrom,
      LocalTime timeTo) {
    this.idSchedule = idSchedule;
    this.course = course;
    this.dayOfWeek = dayOfWeek;
    this.timeFrom = timeFrom;
    this.timeTo = timeTo;
  }

  public Schedule() {}

  public ObjectId getId() {
    return id;
  }

  public Long getIdSchedule() {
    return idSchedule;
  }

  public Course getCourse() {
    return course;
  }

  public int getDayOfWeek() {
    return dayOfWeek;
  }

  public LocalTime getTimeFrom() {
    return timeFrom;
  }

  public LocalTime getTimeTo() {
    return timeTo;
  }
}
