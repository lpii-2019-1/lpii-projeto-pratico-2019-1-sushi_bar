package sushibar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection con = null;
    
    private static final String USER = "adrianob_bd72019";
    private static final String PASS = "bd7goiano";
    private static final String URL = "jdbc:mysql://www.adrianobraga.com.br/adrianob_bd72019?autoReconnect=true&useSSL=false";

    

    public Connection getConnection() {
        try {
            if (con == null) {
                
                return DriverManager.getConnection(URL, USER, PASS);
            } else if (con.isClosed()) {
                con = null;
                return getConnection();
            }
        } catch (Exception e) {

            //TODO: use um sistema de log apropriado.
            e.printStackTrace();
        } 
        return con;
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                //TODO: use um sistema de log apropriado.
                e.printStackTrace();
            }
        }
    }

}
