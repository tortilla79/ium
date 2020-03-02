import java.io.*;
import java.sql.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet("/Ripetizioni")
public class RipetizioniServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response )
	throws ServletException, IOException{
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder(); 
	String action = request.getParameter("param");
	String tmpUsr, tmpCorso, tmpDoc, tmpDate, tmpStat;
	//String tmpUsr, tmpCorso, tmpDate, tmpStat;
	String sqlStr = null;

	try (
	     // Step 1: Allocate a database 'Connection' object
	     Connection conn = DriverManager.getConnection(
							   "jdbc:mysql://localhost:3306/sblocknotes?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
							   "bobu", "gattopardo");   // For MySQL
	     // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

	     // Step 2: Allocate a 'Statement' object in the Connection
	     Statement stmt = conn.createStatement();
	     PreparedStatement getUsr = conn.prepareStatement("SELECT username FROM user WHERE id = ?");
	     PreparedStatement getDoc = conn.prepareStatement("SELECT surname FROM teacher WHERE id = ?");
	     ) {

	    if (action.equals("tutte"))
		sqlStr = "SELECT * FROM lesson";
	    else if (action.equals("attuali"))
		sqlStr = "SELECT * FROM lesson WHERE status='book'";
	    else if (action.equals("passate"))
		sqlStr = "SELECT * FROM lesson WHERE status='done'";
	    else if (action.equals("disdette"))
		sqlStr = "SELECT * FROM lesson WHERE status='annul'";
	    else
		sqlStr = "parameterError";
	    
	    ResultSet rset = stmt.executeQuery(sqlStr);

	    ResultSet rset2;
	    sb.append("<table>");
	    sb.append("<theader><th>Utente</th><th>Corso</th><th>Docente</th><th>Data</th><th>Status</th></theader>");
	    
	    
	    while (rset.next()) { // finche non ho letto tutte le ripetizioni scorro rset
		sb.append("<tr>");
		
		getUsr.setInt(1, rset.getInt("user"));
		rset2 = getUsr.executeQuery();
		rset2.next();
		tmpUsr = rset2.getString("username");
		getDoc.setInt(1, rset.getInt("teacher"));
		rset2 = getDoc.executeQuery();
		rset2.next();
		tmpDoc = rset2.getString("surname");
		//tmpDoc = rset.getInt("teacher");
		tmpCorso = rset.getString("subject");
		tmpDate = rset.getDate("appointment").toString();
		tmpStat = rset.getString("status");
		sb.append("<td>" + tmpUsr + "</td><td>" + tmpCorso + "</td><td>" + tmpDoc + "</td><td>" + tmpDate + "</td><td>" + tmpStat + "</td>");
		sb.append("</tr>");
	    }
	
	    sb.append("</table>");
	    out.print(sb.toString());
	     
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
