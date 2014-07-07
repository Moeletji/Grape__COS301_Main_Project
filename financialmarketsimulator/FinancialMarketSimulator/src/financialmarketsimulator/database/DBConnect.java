package financialmarketsimulator.database;

import java.sql.*;

/**
 *
 * 
 */
public class DBConnect {
   private Connection con;
   private Statement st;
   private ResultSet rs;
   
   public DBConnect()
   {
       try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy_db", "root", "");
           st = con.createStatement();
       }
       catch(Exception e){
           e.printStackTrace();
       }
   }
   
   public void getData()
   {
       try{
           /**
            * Query is the actual query string
            */
           String query = "select * FROM "; //Add table name after FROM
           rs = st.executeQuery(query);
           
           while(rs.next())
           {
               String name = rs.getString(""); //Table column Name goes in parameter
               String age = rs.getString(""); //Table column Name goes in parameter
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
   }
}
