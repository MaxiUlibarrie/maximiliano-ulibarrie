package com.bootcamp.topic2.blogsystem;

import java.util.GregorianCalendar;

public class Entry {
  private String title;
  private String topic;
  private String body;
  private GregorianCalendar date;

  public Entry(String title, String topic, String body) {
    this.title = title;
    this.topic = topic;
    this.body = body;
    this.date = new GregorianCalendar();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public GregorianCalendar getDate() {
    return date;
  }

  public void setDate(GregorianCalendar date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Entry)) {
      return false;
    }
    Entry anEntry = (Entry) o;

    boolean areEqualsTitle = anEntry.getTitle().equals(this.getTitle());
    boolean areEqualsTopic = anEntry.getTopic().equals(this.getTopic());
    boolean areEqualsBody = anEntry.getBody().equals(this.getBody());

    return areEqualsTitle && areEqualsTopic && areEqualsBody;
  }

  @Override
  public String toString() {
    return "Entry{" +
        "title='" + title + '\'' +
        ", topic='" + topic + '\'' +
        ", body='" + body + '\'' +
        ", date=" + date.getTime().toString() +
        '}';
  }
}
