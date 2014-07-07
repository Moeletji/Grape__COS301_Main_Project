package financialmarketsimulator.database;

import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 *
 */
public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private final String tableName = "trade_data";
    private final String databaseName = "trade_db";
    private final String user = "root";
    private final String pass = "";

    public DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, user, pass);
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Stores the trade information in the database from the trade object
     * passed through as parameter
     * @param trade MatcheedMarketEntryAttempt object housing trade information
     */
    public void recordTrade(MatchedMarketEntryAttempt trade) {
        try {
            /**
             * query_ is the actual query string
             */
            String query1 = "INSERT INTO "+tableName+" (Date,Volume,Price,Offeror, Bidder) "
                    + "VALUES(" + trade.getDateIssuedToString() + "," + trade.getQuantity() + "," + trade.getPrice() + ","
                    + trade.getOffererName() + "," + trade.getBidderName() + ")";
            
            rs = st.executeQuery(query1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public MatchedMarketEntryAttempt getTradeInfoUsingID(UUID id)
    {
        MatchedMarketEntryAttempt trade = null;
        try
        {
            String query = "SELECT * FROM " +tableName+ " WHERE uuid='" +id+ "'";
            rs = st.executeQuery(query);
            
            String _id = rs.getString("uuid");
            java.util.Date dateIssued = new SimpleDateFormat("YYYY.MM.DD HH:MM:SS").parse(rs.getString("Date"));
            int numShares = Integer.parseInt(rs.getString("Volume"));
            double price = Double.parseDouble(rs.getString("Volume"));
            trade = new MatchedMarketEntryAttempt(_id, dateIssued, numShares, price);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return trade;
    }
    
    public ArrayList<MatchedMarketEntryAttempt> getTradeInfoUsingName(String name)
    {
        ArrayList<MatchedMarketEntryAttempt> array = null;
        MatchedMarketEntryAttempt trade = null;
        try
        {
            String query = "SELECT * FROM " +tableName+ " WHERE Offeror='" +name+ "' or Bidder='" +name+"'";
            rs = st.executeQuery(query);
            
            while(rs.next())
            {
                String _id = rs.getString("uuid");
                java.util.Date dateIssued = new SimpleDateFormat("YYYY.MM.DD HH:MM:SS").parse(rs.getString("Date"));
                int numShares = Integer.parseInt(rs.getString("Volume"));
                double price = Double.parseDouble(rs.getString("Volume"));
                trade = new MatchedMarketEntryAttempt(_id, dateIssued, numShares, price);
                
                array.add(trade);
            }
        }
        catch(NumberFormatException | SQLException | ParseException e)
        {
            e.printStackTrace();
        }
        
        return array;
    }
}
