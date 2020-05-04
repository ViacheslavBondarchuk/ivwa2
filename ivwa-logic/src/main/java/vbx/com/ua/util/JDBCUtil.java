package vbx.com.ua.util;

import org.springframework.util.StringUtils;

import java.sql.*;

public class JDBCUtil {
    private static JDBCUtil instance;

    private final String dbUsername = ApplicationGlobalPropertiesUtil.getInstance().getProperty("db.username");
    private final String dbPassword = ApplicationGlobalPropertiesUtil.getInstance().getProperty("db.password");
    private final String dbUrl = ApplicationGlobalPropertiesUtil.getInstance().getProperty("db.url");

    public static JDBCUtil getInstance() {
        if (instance == null)
            instance = new JDBCUtil();
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNextValue(final String seqName) {
        if (!StringUtils.isEmpty(seqName)) {
            try (final Connection connection = this.getConnection()) {
                if (connection != null) {
                    String query = String.format("select nextval(\'%s\')", seqName);
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet != null) {
                        return resultSet.getString(1);
                    }
                    resultSet.close();
                    preparedStatement.close();
                }
                return null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
