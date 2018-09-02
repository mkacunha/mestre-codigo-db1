package h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class H2Memory {

    private static final String URL = "jdbc:h2:mem:";

    private static Connection connection;

    private Connection handlerException(SQLException ex) {
        Logger lgr = Logger.getLogger(H2Memory.class.getName());
        lgr.log(Level.SEVERE, ex.getMessage(), ex);
        throw new RuntimeException(ex);
    }

    private void executeSql(String sql) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            handlerException(e);
        }
    }

    public Connection getConnection() {
        try {
            if (Objects.nonNull(connection)) {
                return connection;
            }
            return connection = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            return handlerException(ex);
        }
    }


    public void initDatabase() {
        createTable();
        insertValues();
    }

    public void createTable() {
        String sql = "CREATE TABLE   PESSOA " +
                "(id INTEGER not NULL, " +
                " nome VARCHAR(255), " +
                " data_nascimento DATE, " +
                " idade INTEGER, " +
                " PRIMARY KEY ( id ))";
        executeSql(sql);
    }

    public void insertValues() {
        executeSql("insert into PESSOA values (1, 'JOÃO DA SILVA', '1990-02-01', 28);");
        executeSql("insert into PESSOA values (3, 'EVANDRO DOS SANTOS', '1999-01-01', '19')");
        executeSql("insert into PESSOA values (4, 'EDUARDO SILVA', '1995-01-01', '23')");
        executeSql("insert into PESSOA values (2, 'MARIA DA DORES', '1980-01-01', '38')");
        executeSql("insert into PESSOA values (5, 'MARIA FILÓ', '1993-01-01', '25')");
        executeSql("insert into PESSOA values (6, 'MAIKO CUNHA', '1991-01-01', '27')");
    }
}
