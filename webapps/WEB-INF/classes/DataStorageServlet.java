import java.io.*;
import java.sql.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet("/DataStorage")
public class DataStorageServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response )
	throws ServletException, IOException{
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	String param = request.getParameter("param");
	String data1 = request.getParameter("data1");
	String data2 = request.getParameter("data2");
	String data3 = request.getParameter("data3");

	try (
	     // Step 1: Allocate a database 'Connection' object
	     Connection conn = DriverManager.getConnection(
							   "jdbc:mysql://localhost:3306/sblocknotes?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
							   "bobu", "gattopardo");   // For MySQL
	     // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

	     // Step 2: Allocate a 'Statement' object in the Connection
	     Statement stmt = conn.createStatement();
	     ) {
	    int docId = -1;
	    String sqlStr = null;
	    if (param.equals("creacorso"))
		sqlStr = "INSERT INTO subject VALUES('"+ data1 +"') ";
	    else if (param.equals("eliminacorso"))
		sqlStr = "DELETE FROM subject WHERE name='"+ data1 +"'";
	    else if (param.equals("creadoc"))
		sqlStr = "INSERT INTO teacher (name, surname) VALUES('"+ data1 +"','"+ data2 +"') ";
	    else if (param.equals("eliminadoc"))
		sqlStr = "DELETE FROM teacher WHERE name='"+ data1 +"' and surname='"+ data2 +"'";
	    else if (param.equals("Aggiungicdoc")) {
		sqlStr = "SELECT id FROM teacher WHERE name='"+ data2 +"' and surname='"+ data3 +"'";
		ResultSet rs = stmt.executeQuery(sqlStr);
		rs.next();
		docId = rs.getInt("id");
		sqlStr = "INSERT INTO teacher_subject VALUES('"+ docId +"','"+ data1 +"')";
	    }
	    else if (param.equals("Rimuovicdoc")){
		sqlStr = "SELECT id FROM teacher WHERE name='"+ data2 +"' and surname='"+ data3 +"'";
		ResultSet rs = stmt.executeQuery(sqlStr);
		rs.next();
		docId = rs.getInt("id");
		sqlStr = "DELETE FROM teacher_subject WHERE teacher='"+ docId +"'and subject='"+ data1 +"'";
	    }
	    
	    stmt.execute(sqlStr);
	    
	    out.print("Comando Eseguito");
	} catch (Exception ex) {
	    out.print(ex.getMessage());
	    ex.printStackTrace();
	}
	
    }

    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
    {
	processRequest(request, response);
    }

    /**
     * We are going to perform the same operations for POST requests
     * as for GET methods, so this method just sends the request to
     * the doGet method.
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
    {
	processRequest(request, response);
    }
}
