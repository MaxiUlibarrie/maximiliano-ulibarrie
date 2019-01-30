package Topic0.Point4;

public class TestPoint4 {

  public static void main(String[] args) {

    DBConnectionEngineer dbcEng = new DBConnectionEngineer(new MySQLBuilder());

    dbcEng.createDBConnection();

    System.out.println("Name: "+ dbcEng.getDBConnection().getNameConnection());
    System.out.println("Port: "+ dbcEng.getDBConnection().getPortConnection());
    System.out.println("");

    dbcEng.setDbConBuilder(new MongoDBBuilder());

    dbcEng.createDBConnection();

    System.out.println("Name: "+ dbcEng.getDBConnection().getNameConnection());
    System.out.println("Port: "+ dbcEng.getDBConnection().getPortConnection());


  }

}
