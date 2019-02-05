package com.bootcamp.topic2.recentfilelist;

public class HandleOS {
  private String nameOS;
  private RecentFileList recentFileList;

  public HandleOS(String nameOS, RecentFileList recentFileList) {
    this.nameOS = nameOS;
    this.recentFileList = recentFileList;
  }

  public String getNameOS() {
    return nameOS;
  }

  public void setNameOS(String nameOS) {
    this.nameOS = nameOS;
  }

  public RecentFileList getRecentFileList() {
    return recentFileList;
  }

  public void setRecentFileList(RecentFileList recentFileList) {
    this.recentFileList = recentFileList;
  }

  public void openFile(File myFile) {
    recentFileList.addFile(myFile);
  }
}