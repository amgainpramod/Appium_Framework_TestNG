package utilities;

import java.sql.*;

public class JDBCConnection {
    public static Object[][] getDataFromDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/QADBTest",
                "postgres", "root");

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery("select name, country, gender from userdata");
        resultSet.last();
        Object[][] data = new Object[resultSet.getRow()][3];
        resultSet.beforeFirst();

        while (resultSet.next()){
            data[resultSet.getRow()-1][0] =resultSet.getString("name");
            data[resultSet.getRow()-1][1] = resultSet.getString("country");
            data[resultSet.getRow()-1][2] = resultSet.getString("gender");
        }
        return data;
    }
}
