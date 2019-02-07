package com.bootcamp.topic2.recentfilelist;

import java.util.ArrayList;

/**
 * RecentFileList organize files, the list is organized from
 * the most recent file (first element of the list) to the less
 * recent (last element of the list).
 */
public class RecentFileList {
  private ArrayList<File> fileList;
  private int maxSize;

  public RecentFileList(int maxSize) {
    this.maxSize = maxSize;
    this.fileList = new ArrayList<File>(maxSize);
  }

  public int getMaxSize() {
    return maxSize;
  }

  public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
  }

  public ArrayList<File> getFileList() {
    return fileList;
  }

  public void setFileList(ArrayList<File> fileList) {
    this.fileList = fileList;
  }

  public void addFile(File file) {
    if (fileList.contains(file)) {
      fileList.remove(file);
    } else if (this.getMaxSize() == fileList.size()) {
      fileList.remove(fileList.size()-1);
    }

    fileList.add(0,file);
  }
}