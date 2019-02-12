package com.bootcamp.topic2.blogsystemdao;

import com.bootcamp.topic2.blogsystem.Entry;
import java.util.List;

public interface EntryDAO {
  public List<Entry> recoverMostRecentEntries();
}
