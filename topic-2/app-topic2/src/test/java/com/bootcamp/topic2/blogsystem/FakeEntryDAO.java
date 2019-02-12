package com.bootcamp.topic2.blogsystem;

import com.bootcamp.topic2.blogsystemdao.EntryDAO;
import java.util.ArrayList;
import java.util.List;

public class FakeEntryDAO implements EntryDAO {

  private List<Entry> entries;

  public FakeEntryDAO() {
    entries = new ArrayList<>();
    entries.add(new Entry("title0","topic0","body0"));
    entries.add(new Entry("title1","topic1","body1"));
    entries.add(new Entry("title2","topic2","body2"));
    entries.add(new Entry("title3","topic3","body3"));
  }

  @Override
  public List<Entry> recoverMostRecentEntries() {
    return entries;
  }
}
