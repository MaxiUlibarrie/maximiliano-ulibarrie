package com.bootcamp.topic2.blogsystem;

import com.bootcamp.topic2.blogsystemdao.EntryDAO;
import java.util.ArrayList;
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
    ArrayList<Entry> entries = new ArrayList<>();
    entries.add(new Entry("title0","topic0","body0"));
    entries.add(new Entry("title1","topic1","body1"));
    entries.add(new Entry("title2","topic2","body2"));
    entries.add(new Entry("title3","topic3","body3"));
    when(entryDAO.recoverMostRecentEntries()).thenReturn(entries);
  }

  @Test
  public void whenPostNewEntryThenItExistsInTheBlogList() {
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(0));

    assertTrue(blog.getEntryList().contains(entryDAO.recoverMostRecentEntries().get(0)));
  }

  @Test
  public void whenDeleteExistingEntryThenItIsGoneFromTheBlog() {
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(0));
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(1));
    blog.addEntry(entryDAO.recoverMostRecentEntries().get(2));

    blog.deleteEntry(new Entry("title1","topic1","body1"));

    assertFalse(blog.getEntryList().contains(new Entry("title1","topic1","body1")));
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
