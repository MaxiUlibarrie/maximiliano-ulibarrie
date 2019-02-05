package com.bootcamp.topic2.recentfilelist;

public class File {
  private String name;
  private String location;
  private int size;

  public File(String location, String name) {
    this.name = name;
    this.location = location;
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
    if (o == this) {
      return true;
    }
    if (!(o instanceof File)) {
      return false;
    }
    File aFile = (File) o;

    boolean areEqualsNames = aFile.getName().equals(this.getName());
    boolean areEqualsLocations = aFile.getLocation().equals(this.getLocation());

    return areEqualsNames && areEqualsLocations;
  }

  @Override
  public String toString() {
    return location.concat(name).concat(String.format(" [size = %s]", size));
  }
}