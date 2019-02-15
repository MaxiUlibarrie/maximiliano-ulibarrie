package com.bootcamp.topic5;

import com.bootcamp.topic5.Note;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

public class NoteDAO extends BasicDAO<Note,Long> {

  protected NoteDAO(MongoClient mongoClient, Morphia morphia,
      String dbName) {
    super(mongoClient, morphia, dbName);
  }
}
