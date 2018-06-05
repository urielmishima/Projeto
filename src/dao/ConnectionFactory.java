package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
   static {
      try {
         Class.forName("com.mysql.jdbc.Driver");
      }
      catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }
   public static Connection obtemConexao() throws SQLException {
      return DriverManager
         	.getConnection("jdbc:mysql://localhost/pratprogaula02?user=root&password=uh220799");
   }

}