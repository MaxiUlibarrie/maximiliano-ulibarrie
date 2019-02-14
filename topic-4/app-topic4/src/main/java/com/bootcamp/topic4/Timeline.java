package com.bootcamp.topic4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Timeline {

  public static void timelineForEachCourse(long idTeacher) {
    String url = "jdbc:mysql://localhost:3306/high_school?useTimezone=true&serverTimezone=UTC";
    String user = "root";
    String password ="asdqwe";
    try {
      Connection connection = DriverManager.getConnection(url,user,password);
      Statement statement = connection.createStatement();
      String queryNameTeacher = String.format("select concat(t.last_name,\", \",t.first_name) as teacher\n"
                                    + "        from Teacher as t\n"
                                    + "        where t.id_teacher = %d",idTeacher);
      ResultSet resultNameTeacher = statement.executeQuery(queryNameTeacher);

      String querySchedule = String.format("select concat(case \n"
                                + "                        when s.day_of_week = 0 then \"Sunday\"\n"
                                + "                        when s.day_of_week = 1 then \"Monday\"\n"
                                + "                        when s.day_of_week = 2 then \"Tuesday\"\n"
                                + "                        when s.day_of_week = 3 then \"Wednesday\"\n"
                                + "                        when s.day_of_week = 4 then \"Thursday\"\n"
                                + "                        when s.day_of_week = 5 then \"Friday\"\n"
                                + "                        when s.day_of_week = 6 then \"Saturday\"\n"
                                + "                        else \"Wrong day\"\n"
                                + "                      end,\" \",s.time_from,\" - \",s.time_to,\": \",c.name) as schedule\n"
                                + "        from Teacher as t join Course as c on t.id_teacher = c.id_teacher\n"
                                + "            join Schedule as s on s.id_course = c.id_course\n"
                                + "    \t\twhere t.id_teacher = %d",idTeacher);

      statement = connection.createStatement();
      ResultSet resultSchedule = statement.executeQuery(querySchedule);

      System.out.println("Teacher:");
      while (resultNameTeacher.next()) {
        System.out.println(resultNameTeacher.getString("teacher"));
      }

      System.out.println("Schedule:");
      while (resultSchedule.next()) {
        System.out.println(resultSchedule.getString("schedule"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
