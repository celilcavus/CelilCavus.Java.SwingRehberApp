package celilcavus.java.SqlHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbContext {

    private final String Name = "localhost";
    private final String User = "root";
    private final String Password = "celil123";
    private final int port = 3306;
    private final String DbName = "telrehberidb";

    public Connection connect = null;
    public Statement state = null;
    public ResultSet set;
    public PreparedStatement pre;

    public DbContext() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + Name + ":" + port + "/" + DbName;
            try {
                connect = DriverManager.getConnection(url, User, Password);
                state = connect.createStatement();

            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
