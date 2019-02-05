package com.bootcamp.topic2.blogsystem;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Blog extends ArrayList<Entry> {
  private String ownerName;
  private String url;

  public Blog(int initialCapacity, String ownerName, String url) {
    super(initialCapacity);
    this.ownerName = ownerName;
    this.url = url;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean deleteEntry(Entry entry) {
    if (super.contains(entry)) {
      return super.remove(entry);
    }
    return false;
  }

  @Override
  public boolean add(Entry entry) {
    if (super.contains(entry)) {
      super.remove(entry);
    }
    entry.setDate(new GregorianCalendar());

    return super.add(entry);
  }

  public void showMostRecentEntry() {
    for (int i = this.size() - 1; i >= 0; i--) {
      System.out.println(this.get(i));
    }
  }
}
