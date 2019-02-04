package com.bootcamp.topic2.recentfilelist;

import java.util.ArrayList;

/**
 * RecentFileList organize files, the list is organized from
 * the most recent file (last element of the list) to the less
 * recent (first element of the list).
 */
public class RecentFileList extends ArrayList<File> {

  private int maxSize;

  public RecentFileList(int maxSize) {
    super(maxSize);
    this.maxSize = maxSize;
  }

  public int getMaxSize() {
    return maxSize;
  }

  public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
  }

  @Override
  public boolean add(File f) {
    if (super.contains(f)) {
      super.remove(f);
    } else if (this.getMaxSize() == super.size()) {
      super.remove(0);
    }

    return super.add(f);
  }
}