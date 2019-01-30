package Topic0.Point4;

public class DBConnection implements DBConnectionPlan {

  private String nameConnection;
  private int portConnection;

  public String getNameConnection() {
    return nameConnection;
  }

  public int getPortConnection() {
    return portConnection;
  }

  @Override
  public void setNameConnection(String nameConnection) {
    this.nameConnection = nameConnection;
  }

  @Override
  public void setPortConnection(int portConnection) {
    this.portConnection = portConnection;
  }

}
