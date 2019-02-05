package com.bootcamp.topic2.recentfilelist;

import java.util.Objects;

public class File {
  private String name;
  private String location;
  private int size;

  public File(String location, String name, int size) {
    this.name = name;
    this.location = location;
    this.size = size;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    File file = (File) o;
    return name.equals(file.name) &&
        location.equals(file.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, location);
  }

  @Override
  public String toString() {
    return "File{" +
        "name='" + name + '\'' +
        ", location='" + location + '\'' +
        ", size=" + size +
        '}';
  }
}