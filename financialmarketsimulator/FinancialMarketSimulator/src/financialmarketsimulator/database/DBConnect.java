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

    public DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy_db", "root", "");
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getData() {

    }

    /**
     * @brief Stores the trade information in the database from the trade object
     * passed through as parameter
     * @param trade MatcheedMarketEntryAttempt object housing trade information
     */
    public void recordTrade(MatchedMarketEntryAttempt trade) {
        try {
            String date = trade.getDateIssuedToString();
            /**
             * query_ is the actual query string
             */
            String query1 = "INSERT INTO trade_data (Date,Volume,Price,Offeror, Bidder) "
                    + "VALUES(" + date + "," + trade.getQuantity() + "," + trade.getPrice() + ","
                    + trade.getOffererName() + "," + trade.getBidderName() + ")";
            rs = st.executeQuery(query1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
