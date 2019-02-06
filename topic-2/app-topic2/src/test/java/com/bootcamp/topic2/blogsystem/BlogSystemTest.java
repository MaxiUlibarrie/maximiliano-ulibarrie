package com.bootcamp.topic2.blogsystem;

import com.bootcamp.topic2.blogsystemdao.EntryDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BlogSystemTest {
  private EntryDAO entryDAO;
  private Blog blog;

  @Before
  public void setup() {
    blog = new Blog("ownerName","www.blog.com");

    entryDAO = mock(EntryDAO.class);
    when(entryDAO.recoveryById(1)).thenReturn(new Entry("title1","topic1","body1"));
    when(entryDAO.recoveryById(2)).thenReturn(new Entry("title2","topic2","body2"));
    when(entryDAO.recoveryById(3)).thenReturn(new Entry("title3","topic3","body3"));
    when(entryDAO.recoveryById(4)).thenReturn(new Entry("title4","topic4","body4"));
  }

  @Test
  public void whenPostNewEntryThenItExistsInTheBlogList() {
    Entry entry = entryDAO.recoveryById(1);
    blog.addEntry(entry);

    assertTrue(blog.getEntryList().contains(entry));
  }

  @Test
  public void whenDeleteExistingEntryThenItIsGoneFromTheBlog() {
    Entry entry1 = entryDAO.recoveryById(1);
    blog.addEntry(entry1);

    Entry entry2 = entryDAO.recoveryById(2);
    blog.addEntry(entry2);

    Entry entry3 = entryDAO.recoveryById(3);
    blog.addEntry(entry3);

    blog.deleteEntry(entry2);

    assertFalse(blog.getEntryList().contains(entry2));
  }

  @Test
  public void whenShowMostRecentEntryThenTheseHaveToBeInOrderDate() {
    Entry entry1 = entryDAO.recoveryById(1);
    blog.addEntry(entry1);

    Entry entry2 = entryDAO.recoveryById(2);
    blog.addEntry(entry2);

    Entry entry3 = entryDAO.recoveryById(3);
    blog.addEntry(entry3);

    Entry entry4 = entryDAO.recoveryById(4);
    blog.addEntry(entry4);

    assertFalse(entry1.getDatetime().isAfter(entry2.getDatetime()));
    assertFalse(entry2.getDatetime().isAfter(entry3.getDatetime()));
    assertFalse(entry3.getDatetime().isAfter(entry4.getDatetime()));
  }
}
