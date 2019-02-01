package com.bootcamp.maxi;

public class DBConnection {
  private String name;
  private int port;
  private String user;
  private String password;

  public DBConnection(String name, int port, String user, String password) {
    this.name = name;
    this.port = port;
    this.user = user;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
