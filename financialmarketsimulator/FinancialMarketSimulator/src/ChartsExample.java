
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @brief Example class of how to use JFreeCharts, the charts library. The chart is
 * generated from dummy data created in the test_table table from the test_database 
 * database. Just an example of how to use the JFreeCharts.
 */
public class ChartsExample {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private final String tableName = "test_table";
    private final String databaseName = "test_db";
    private final String user = "root";
    private final String pass = "";
    private double[][] values;
    private JDBCCategoryDataset dataset;

    public void example() {
    }

    public void connectToDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, pass);
            st = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief USED FOR POPULATING RANDOM DATA INTO THE test_table TABLE!
     */
    public void populate() {
        double amount;
        double range = 100;
        Random random = new Random();

        try {

            for (int i = 1; i < 31; i++) {
                double scaled = random.nextDouble() * range;
                amount = scaled + 0;

                String query1 = "INSERT INTO " + tableName + " (Date,Price) VALUES(?,?)";
                PreparedStatement ps = con.prepareStatement(query1);
                ps.setString(1, "07-" + i);
                ps.setDouble(2, amount);
                ps.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        try {
            String query1 = "SELECT * FROM " + tableName;
            rs = st.executeQuery(query1);

            dataset = new JDBCCategoryDataset(con, query1);

            int i = 1;
            while (rs.next()) {
                System.out.println(i + ".  " + rs.getString("Date") + "   : " + rs.getString("Price"));
                i++;
            }

            System.out.println("END.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ChartsExample exam = new ChartsExample();
        exam.connectToDB();
        //exam.populate(); //WAS USED FOR POPULATING DATA INTO THE DATABASE
        exam.execute();

        //Creating the chart
        JFreeChart chart = ChartFactory.createLineChart("Basic Line Chart", "Date", "Price", exam.dataset, PlotOrientation.VERTICAL, false, true, true);
        ChartFrame frame = new ChartFrame("Basic Line Chart", chart);
        frame.setVisible(true);
        frame.setSize(1000, 650);
    }
}
