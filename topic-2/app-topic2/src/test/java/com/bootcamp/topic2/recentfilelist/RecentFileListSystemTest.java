package com.bootcamp.topic2.recentfilelist;

import static org.junit.Assert.*;
import org.junit.Test;

public class RecentFileListSystemTest {
  @Test
  public void whenTheProgramIsRunForTheFirstTimeThenTheListIsEmpty() {
    RecentFileList recentFileList = new RecentFileList(4);

    assertTrue(recentFileList.getFileList().isEmpty());
  }

  @Test
  public void whenAFileIsOpenedThenItIsAddedToRecentList() {
    File file = new File("C:/","example.java",50);
    HandleOS handleOS = new HandleOS("Windows10", new RecentFileList(5));
    handleOS.openFile(file);

    assertTrue(handleOS.getRecentFileList().getFileList().contains(file));
  }

  @Test
  public void whenAnOpenedFileAlreadyExistsInRecentFileListThenItIsBumpedToTop() {
    RecentFileList recentFileList = new RecentFileList(4);
    recentFileList.addFile(new File("C:/","example1.java",50));
    recentFileList.addFile(new File("C:/","example2.java",50));
    recentFileList.addFile(new File("C:/","example3.java",50));
    recentFileList.addFile(new File("C:/","example1.java",50));

    File lastFile = recentFileList.getFileList().get(0);
    assertEquals(new File("C:/","example1.java",50), lastFile);
  }

  @Test
  public void whenAnOpenedFileAlreadyExistsInRecentFileListThenItIsNotDuplicated() {
    RecentFileList recentFileList = new RecentFileList(4);
    recentFileList.addFile(new File("C:/","example1.java",50));
    recentFileList.addFile(new File("C:/","example2.java",50));
    recentFileList.addFile(new File("C:/","example3.java",50));

    File duplicatedFile = new File("C:/","example1.java",50);
    recentFileList.addFile(duplicatedFile);

    int amountDuplicated = 0;
    for (File file : recentFileList.getFileList()) {
      if (file.equals(duplicatedFile)) {
        amountDuplicated++;
        if (amountDuplicated == 2) break;
      }
    }

    assertTrue(recentFileList.getFileList().size() == 3);
    assertTrue(amountDuplicated == 1);
  }

  @Test
  public void whenAFileIsAddedAndListIsFullThenTheOldestFileIsRemoved() {
    RecentFileList recentFileList = new RecentFileList(3);
    File fileToLeave = new File("C:/","example1.java",50);
    recentFileList.addFile(fileToLeave);

    recentFileList.addFile(new File("C:/","example2.java",50));
    recentFileList.addFile(new File("C:/","example3.java",50));

    File addedFile = new File("C:/","example4.java",50);
    recentFileList.addFile(addedFile);

    File oldestFile = recentFileList.getFileList().get(0);

    assertEquals(oldestFile, addedFile);
    assertFalse(recentFileList.getFileList().contains(fileToLeave));
  }
}