package com.bootcamp.topic2.blogsystem;

import com.bootcamp.topic2.blogsystemdao.EntryDAO;
import java.time.LocalDateTime;
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
    entry1.setDatetime(LocalDateTime.of(2019,2,5,16,40));
    blog.addEntry(entry1);

    Entry entry2 = entryDAO.recoveryById(2);
    entry2.setDatetime(LocalDateTime.of(2019,2,5,16,43));
    blog.addEntry(entry2);

    Entry entry3 = entryDAO.recoveryById(3);
    entry3.setDatetime(LocalDateTime.of(2018,2,5,10,43));
    blog.addEntry(entry3);

    Entry entry4 = entryDAO.recoveryById(4);
    entry4.setDatetime(LocalDateTime.of(2019,1,1,10,43));
    blog.addEntry(entry4);

    boolean areInOrder = true;
    LocalDateTime previous, next;
    for (int i = 0; i < blog.getEntryList().size() - 1; i++) {
      previous = blog.getEntryList().get(i).getDatetime();
      next = blog.getEntryList().get(i+1).getDatetime();

      if (previous.isBefore(next)) {
        areInOrder = false;
      }
    }

    assertTrue(areInOrder);
  }
}
