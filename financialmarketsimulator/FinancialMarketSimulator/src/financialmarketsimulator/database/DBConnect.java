package financialmarketsimulator.database;

import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
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
       
   }
   
   /**
    * @brief Stores the trade information in the database from the trade object passed
    * through as parameter
    * @param trade MatcheedMarketEntryAttempt object housing trade information
    */
   public void recordTrade(MatchedMarketEntryAttempt trade)
   {
       try{
           /**
            * query_ is the actual query string
            */
           //String query1 = "select * FROM trade_data";
           String date;
           String query1 = "INSERT INTO trade_data (Date,Volume,Price,Offeror, Bidder) "
                   + "VALUES("++","+trade.getQuantity()+","+trade.getPrice()+","+",")";
           rs = st.executeQuery(query1);
           
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
