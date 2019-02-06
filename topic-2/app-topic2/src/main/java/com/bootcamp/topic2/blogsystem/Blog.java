package com.bootcamp.topic2.blogsystem;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Blog organize entries, the list is organized from
 * the most recent entry (FIRST element of the list) to the less
 * recent (LAST element of the list).
 */
public class Blog {
  private ArrayList<Entry> entryList;
  private String ownerName;
  private String url;

  public Blog(String ownerName, String url) {
    this.ownerName = ownerName;
    this.url = url;
    this.entryList = new ArrayList<Entry>();
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

  public ArrayList<Entry> getEntryList() {
    return entryList;
  }

  public void setEntryList(ArrayList<Entry> entryList) {
    this.entryList = entryList;
  }

  public boolean deleteEntry(Entry entry) {
    return entryList.remove(entry);
  }

  public void addEntry(Entry entry) {
    if (entryList.contains(entry)) {
      entryList.remove(entry);
    }
    entryList.add(0, entry);
  }

  public void showMostRecentEntries() {
    for (int i = 0; i < entryList.size(); i++) {
      System.out.println(entryList.get(i));
    }
  }
}
