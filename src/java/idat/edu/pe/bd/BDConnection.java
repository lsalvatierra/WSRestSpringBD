
package idat.edu.pe.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BDConnection {
    
    private static BDConnection instanciaUnica;
 
 
    public static BDConnection getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new BDConnection();
        }     
        return instanciaUnica;
    }
    
    private String url ="jdbc:sqlserver://LUISANGEL:1433;databaseName=bdnotas";
    private String login ="lsalvat";
    private String password ="lsalvat1989";
    private String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    Connection conn = null;
    
    private BDConnection() {
    }
    
    public Connection EstablecerConexion(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, 
                    login, password);
        } catch (ClassNotFoundException | SQLException ex ) {
            ex.printStackTrace();
        }
        return conn;
    }    
    public void Desconectar(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
}
