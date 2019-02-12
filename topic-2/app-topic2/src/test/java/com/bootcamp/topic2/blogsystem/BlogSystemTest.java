package com.bootcamp.topic2.blogsystem;

import com.bootcamp.topic2.blogsystemdao.EntryDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BlogSystemTest {
  private EntryDAO entryDAO;
  private Blog blog;

  @Before
  public void setUp() {
    blog = new Blog("ownerName","www.blog.com");
    entryDAO = new FakeEntryDAO();
  }

  @Test
  public void whenPostNewEntryThenItExistsInTheBlogList() {
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(0));

    assertTrue(blog.containsEntry(entryDAO.recoverMostRecentEntries().get(0)));
  }

  @Test
  public void whenDeleteExistingEntryThenItIsGoneFromTheBlog() {
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(0));
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(1));
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(2));

    blog.deleteEntry(new Entry("title1","topic1","body1"));

    assertFalse(blog.containsEntry(new Entry("title1","topic1","body1")));
  }

  @Test
  public void whenShowMostRecentEntryThenTheseHaveToBeInOrderDate() {
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(0));
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(1));
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(2));
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(3));

    assertFalse(blog.getEntry(0).getDatetime().isAfter(blog.getEntry(1).getDatetime()));
    assertFalse(blog.getEntry(1).getDatetime().isAfter(blog.getEntry(2).getDatetime()));
    assertFalse(blog.getEntry(2).getDatetime().isAfter(blog.getEntry(3).getDatetime()));
  }
}
