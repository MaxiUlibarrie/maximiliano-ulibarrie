package com.bootcamp.topic2.blogsystem;

import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BlogSystemTest {
  @Test
  public void whenPostNewEntryThenItExistsInTheBlogList() {
    Blog blog = new Blog(5,"ownerName","www.blog.com");
    Entry entry = mock(Entry.class);
    blog.add(entry);

    assertTrue(blog.contains(entry));
  }

  @Test
  public void whenDeleteExistingEntryThenItIsGoneFromTheBlog() {
    Blog blog = new Blog(5,"ownerName","www.blog.com");
    Entry entry1 = mock(Entry.class);
    blog.add(entry1);
    Entry entry2 = mock(Entry.class);
    blog.add(entry2);
    Entry entry3 = mock(Entry.class);
    blog.add(entry3);

    blog.deleteEntry(entry2);

    assertFalse(blog.contains(entry2));
  }

  @Test
  public void whenShowMostRecentEntryThenTheseHaveToBeInOrderDate() {
    Blog blog = new Blog(4,"ownerName","www.blog.com");
    Entry entry1 = mock(Entry.class);
    when(entry1.getDate()).thenReturn(new GregorianCalendar(2019,5,2));
    blog.add(entry1);

    Entry entry2 = mock(Entry.class);
    when(entry2.getDate()).thenReturn(new GregorianCalendar(2019,5,3));
    blog.add(entry2);

    Entry entry3 = mock(Entry.class);
    when(entry3.getDate()).thenReturn(new GregorianCalendar(2019,5,4));
    blog.add(entry3);

    Entry entry4 = mock(Entry.class);
    when(entry4.getDate()).thenReturn(new GregorianCalendar(2019,5,5));
    blog.add(entry4);

    boolean areInOrder = true;
    for (int i = 0; i < blog.size() - 1; i++) {
      if (!blog.get(i).getDate().before(blog.get(i+1).getDate())) {
        areInOrder = false;
      }
    }

    assertTrue(areInOrder);
  }
}
