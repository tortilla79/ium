import java.io.*;
import java.sql.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet("/ItemGetter")
public class ItemGetterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response )
	throws ServletException, IOException{
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder(); 
	String tmp = null;
	String param = request.getParameter("param");

	try (
	     // Step 1: Allocate a database 'Connection' object
	     Connection conn = DriverManager.getConnection(
							   "jdbc:mysql://localhost:3306/sblocknotes?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
							   "bobu", "gattopardo");   // For MySQL
	     // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

	     // Step 2: Allocate a 'Statement' object in the Connection
	     Statement stmt = conn.createStatement();
	     ) {
	    String sqlStr = null;
	    if (param.equals("corsi"))
		sqlStr = "SELECT * FROM subject";
	    else if (param.equals("doc"))
		sqlStr = "SELECT * FROM teacher ORDER BY surname";
	    ResultSet rset = stmt.executeQuery(sqlStr);
	    
	    while (rset.next()) { // finche non ho letto tutte le materie scorro rset
		if (param.equals("corsi"))
		    tmp = rset.getString("name");
		else if (param.equals("doc"))
		    tmp = rset.getString("name") + " " + rset.getString("surname");
		sb.append(","+ tmp);
		
	    }
	    out.print(sb.toString());
	} catch (Exception ex) {
	    out.print("dberror");
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
