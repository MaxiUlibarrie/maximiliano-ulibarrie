package com.bootcamp.maxi;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBConnectionTest {
  DBConnection myDBConnection = new DBConnection("nameDBC",9999,"userDBC","passDBC");

  @Test
  public void getName() {
    assertEquals(myDBConnection.getName(),"nameDBC");
  }

  @Test
  public void setName() {
    myDBConnection.setName("newName");
    assertEquals(myDBConnection.getName(),"newName");
    myDBConnection.setName("nameDBC");
  }

  @Test
  public void getPort() {
    assertEquals(myDBConnection.getPort(),9999);
  }

  @Test
  public void setPort() {
    myDBConnection.setPort(1234);
    assertEquals(myDBConnection.getPort(),1234);
    myDBConnection.setPort(9999);
  }

  @Test
  public void getUser() {
    assertEquals(myDBConnection.getUser(),"userDBC");
  }

  @Test
  public void setUser() {
    myDBConnection.setUser("anotherUser");
    assertEquals(myDBConnection.getUser(),"anotherUser");
    myDBConnection.setUser("userDBC");
  }

  @Test
  public void getPassword() {
    assertEquals(myDBConnection.getPassword(),"passDBC");
  }

  @Test
  public void setPassword() {
    myDBConnection.setPassword("anotherPassword");
    assertEquals(myDBConnection.getPassword(),"anotherPassword");
    myDBConnection.setPassword("passDBC");
  }
}