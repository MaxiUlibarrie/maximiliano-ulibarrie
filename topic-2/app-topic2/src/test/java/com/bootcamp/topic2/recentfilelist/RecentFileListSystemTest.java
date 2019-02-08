package com.bootcamp.topic2.recentfilelist;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RecentFileListSystemTest {
  private RecentFileList recentFileList;

  @Before
  public void setUp() {
    recentFileList = new RecentFileList(4);
    recentFileList.addFile(new File("C:/","example1.java",10));
    recentFileList.addFile(new File("C:/","example2.java",20));
    recentFileList.addFile(new File("C:/","example3.java",30));
  }

  @Test
  public void whenTheProgramIsRunForTheFirstTimeThenTheListIsEmpty() {
    recentFileList = new RecentFileList(4);

    assertTrue(recentFileList.amountOfFiles() == 0);
    assertTrue(recentFileList.isEmptyOfFiles());
  }

  @Test
  public void whenAFileIsOpenedThenItIsAddedToRecentList() {
    File file = new File("C:/","example.java",0);
    HandleOS handleOS = new HandleOS("Windows10", recentFileList);
    handleOS.openFile(file);

    assertTrue(handleOS.getRecentFileList().containsFile(file));
  }

  @Test
  public void whenAnOpenedFileAlreadyExistsInRecentFileListThenItIsBumpedToTopAndNotDuplicated() {
    File newFile = new File("C:/","example1.java",10);
    recentFileList.addFile(newFile);

    int amountDuplicated = 0;
    for (File file : recentFileList.getFileList()) {
      if (file.equals(newFile)) {
        amountDuplicated++;
        if (amountDuplicated == 2) break;
      }
    }

    assertTrue(recentFileList.amountOfFiles() == 3);
    assertTrue(amountDuplicated == 1);
    assertEquals(newFile, recentFileList.getFile(0));
  }

  @Test
  public void whenAFileIsAddedAndListIsFullThenTheOldestFileIsRemoved() {
    recentFileList.setMaxSize(3);
    File newFile = new File("C:/","example4.java",40);
    File oldestFile = recentFileList.getFile(recentFileList.amountOfFiles()-1);

    recentFileList.addFile(newFile);

    assertTrue(recentFileList.amountOfFiles() == 3);
    assertEquals(newFile,recentFileList.getFile(0));
    assertFalse(recentFileList.containsFile(oldestFile));
  }
}