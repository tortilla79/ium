import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet("/Prenotazione")
public class PrenotazioneServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response )
	throws ServletException, IOException{
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder(); 
	String action = request.getParameter("param");
	String usr = request.getParameter("usr");
	String corso = request.getParameter("corso");
	String docname = request.getParameter("docname");
	String docsur = request.getParameter("docsur");
	String ora = request.getParameter("ora");
	String giorno = request.getParameter("giorno");
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
	    // richieste da admin
	    int usrId, docId;
	    ResultSet rs = stmt.executeQuery("SELECT id FROM user WHERE username='"+ usr +"'");
	    rs.next();
	    usrId = rs.getInt("id");
	    rs = stmt.executeQuery("SELECT id FROM teacher WHERE name='"+ docname +"' and surname='"+ docsur +"'");
	    rs.next();
	    docId = rs.getInt("id");
	    LocalDate date = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	    if (action.equals("prenota")){
		sqlStr = "INSERT INTO lesson VALUES('"+ usrId +"','"+ docId +"','"+ corso +"','"+ date.format(formatter) +"','"+ giorno +"','"+ ora +"','book')";
	    } else if (action.equals("conferma")) {
		sqlStr = "DELETE FROM lesson WHERE user='"+ usrId +"' and teacher='"+ docId +"' and subject='"+ corso +"' and daydate='"+ giorno +"' and hourdate='"+ ora +"'";
		stmt.execute(sqlStr);
		sqlStr = "INSERT INTO lesson VALUES('"+ usrId +"','"+ docId +"','"+ corso +"','"+ date.format(formatter) +"','"+ giorno +"','"+ ora +"','done')";
	    } else { //disdici
		sqlStr = "DELETE FROM lesson WHERE user='"+ usrId +"' and teacher='"+ docId +"' and subject='"+ corso +"' and daydate='"+ giorno +"' and hourdate='"+ ora +"'";
		stmt.execute(sqlStr);
		sqlStr = "INSERT INTO lesson VALUES('"+ usrId +"','"+ docId +"','"+ corso +"','"+ date.format(formatter) +"','"+ giorno +"','"+ ora +"','annul')";
	    }
	    stmt.execute(sqlStr);
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
