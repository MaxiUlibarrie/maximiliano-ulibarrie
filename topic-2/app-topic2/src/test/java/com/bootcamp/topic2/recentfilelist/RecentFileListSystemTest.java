package com.bootcamp.topic2.recentfilelist;

import static org.junit.Assert.*;
import org.junit.Test;

public class RecentFileListSystemTest {
  @Test
  public void whenTheProgramIsRunForTheFirstTimeThenTheListIsEmpty() {
    RecentFileList recentFileList = new RecentFileList(4);
    assertTrue(recentFileList.isEmpty());
  }

  @Test
  public void whenAFileIsOpenedThenItIsAddedToRecentList() {
    File file = new File("C:/","example.java");
    HandleOS handleOS = new HandleOS("Windows10", new RecentFileList(5));
    handleOS.openFile(file);
    assertTrue(handleOS.getRecentFileList().contains(file));
  }

  @Test
  public void whenAnOpenedFileAlreadyExistsInRecentFileListThenItIsBumpedToTop() {
    RecentFileList recentFileList = new RecentFileList(4);
    recentFileList.add(new File("C:/","example1.java"));
    recentFileList.add(new File("C:/","example2.java"));
    recentFileList.add(new File("C:/","example3.java"));
    recentFileList.add(new File("C:/","example1.java"));

    assertEquals(new File("C:/","example1.java"), recentFileList.get(recentFileList.size() - 1));
  }

  @Test
  public void whenAnOpenedFileAlreadyExistsInRecentFileListThenItIsNotDuplicated() {
    RecentFileList recentFileList = new RecentFileList(4);
    recentFileList.add(new File("C:/","example1.java"));
    recentFileList.add(new File("C:/","example2.java"));
    recentFileList.add(new File("C:/","example3.java"));

    File duplicatedFile = new File("C:/","example1.java");
    recentFileList.add(duplicatedFile);

    int amountDuplicated = 0;
    for (File file : recentFileList) {
      if (file.equals(duplicatedFile)) {
        amountDuplicated++;
        if (amountDuplicated == 2) break;
      }
    }

    assertTrue(amountDuplicated == 1);
  }

  @Test
  public void whenAFileIsAddedAndListIsFullThenTheOldestFileIsRemoved() {
    RecentFileList recentFileList = new RecentFileList(3);
    File fileToLeave = new File("C:/","example1.java");
    recentFileList.add(fileToLeave);
    recentFileList.add(new File("C:/","example2.java"));
    recentFileList.add(new File("C:/","example3.java"));
    File newFile = new File("C:/","example4.java");
    recentFileList.add(newFile);

    assertEquals(recentFileList.get(recentFileList.size() - 1), newFile);
    assertFalse(recentFileList.contains(fileToLeave));
  }
}