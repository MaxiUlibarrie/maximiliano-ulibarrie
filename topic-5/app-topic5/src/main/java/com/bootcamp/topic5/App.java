package com.bootcamp.topic5;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

public class App {

  public static void main(String[] args) throws UnknownHostException, MongoException {
    String dbName = "high_school";
    MongoClient mongo = new MongoClient();
    Morphia morphia = new Morphia();
    Datastore datastore = morphia.createDatastore(mongo, dbName);

    morphia.mapPackage("com.bootcamp.topic5");

    Student student = new Student(Long.valueOf(1),"Marcos","Martinez",1, LocalDate.of(1992,5,1));
    Key<Student> savedStudent = datastore.save(student);
    System.out.println(savedStudent.getId());

    // storing data

    Student student1 = new Student(Long.valueOf(2),"Maria","Geno",2, LocalDate.of(1992,6,1));
    datastore.save(student1);

    Teacher teacher = new Teacher(Long.valueOf(1),"Carlos","Fernadez",LocalDate.of(1980,5,4));
    datastore.save(teacher);

    Course course = new Course(Long.valueOf(1),teacher,"Physics");
    datastore.save(course);

    Note note = new Note(course,student,7,6,8,7);
    datastore.save(note);

    Note note1 = new Note(course,student1,3,4,5,3);
    datastore.save(note1);

    // using DAO

    NoteDAO noteDAO = new NoteDAO(mongo,morphia,dbName);

    Query<Note> query = datastore.createQuery(Note.class);
    query.and(
        query.criteria("finalNote").greaterThan(4),
        query.criteria("course").equal(course)
    );

    QueryResults<Note> retrievedNotes = noteDAO.find(query);

    for (Note retrievedNote : retrievedNotes) {
      System.out.print(retrievedNote.getStudent().getIdStudent()+" ");
      System.out.print(retrievedNote.getStudent().getFirstName()+" ");
      System.out.println(retrievedNote.getStudent().getLastName());
    }
  }
}
