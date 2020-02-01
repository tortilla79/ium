import java.sql.*;


public class Query {
    /*
    // Step 1: Allocate a database 'Connection' object
    Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/sblocknotes?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
    "myuser");   // For MySQL
    // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

    // Step 2: Allocate a 'Statement' object in the Connection
    Statement stmt = conn.createStatement();
    );
    String sqlStr = "SELECT * FROM user WHERE username = '" + logdata.username + "'";
    ResultSet rset = stmt.executeQuery(sqlStr);
	    
    if (rset.next()) { // Se l'username gia esiste lo comunico al client
    out.print("usr");
    } else {// registro il nuovo utente
    sqlStr = "INSERT INTO user (privilege, username, password, email) VALUES('client','" +
    logdata.username +"','"+ logdata.password + "','" + logdata.email+ "')"; 
    stmt.executeQuery(sqlStr);
    out.print("ok");
    }
    */
    // Check if uname is registered
    public static boolean isRegistered(String uname, Statement stmt) throws SQLException{
	String sqlStr = "SELECT * FROM user WHERE username = '" + uname + "'";
	ResultSet rset = stmt.executeQuery(sqlStr);
	return rset.next();
    }

    public static boolean checkPsw(String uname, String psw, Statement stmt) throws SQLException{
	String sqlStr = "SELECT password FROM user WHERE username = '" + uname + "'";
	ResultSet rset = stmt.executeQuery(sqlStr);
	return psw.equals(rset.getString(1));
    }	

    public static void main(String args[]) throws SQLException{
	try{
	    DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
	    Connection conn = DriverManager
	    .getConnection(
			   "jdbc:mysql://localhost:3306/sblocknotes",
			   "kap", "");
	Statement stmt = conn.createStatement();
	System.out.println(args[0]);
	System.out.println(new Query().isRegistered(args[0], stmt));
	} catch(Exception e) {System.out.println(e.getMessage());}

    }
}
