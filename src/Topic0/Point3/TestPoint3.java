package Topic0.Point3;

public class TestPoint3 {

  public static void main(String[] args) {
    DBConnection dbc = new ProxyDBConnection("MySQL",3306,"Maxi","asd123");

    dbc.connect();
  }
}
