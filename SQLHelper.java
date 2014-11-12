import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SQLHelper {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public SQLHelper() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");

            connect = DriverManager.getConnection("jdbc:postgresql:p32002h",
                    "p32002h", "pass");

            statement = connect.createStatement();

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean arbitraryQuery(String query) throws Exception{
        return statement.execute(query);
    }

    public boolean insertMastery(String values) throws Exception{
        System.out.println("INSERT INTO \"Mastery\"(\"MasteryName\", \"Description\", \"Type\") VALUES("+values+");");
        return statement.execute("INSERT INTO \"Mastery\"(\"MasteryName\", \"Description\", \"Type\") VALUES("+values+");");
    }

    public void close() throws Exception{
        try {
            if (connect != null) connect.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
