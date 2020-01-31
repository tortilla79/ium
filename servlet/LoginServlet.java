
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response ) {
	PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder(); 
	BufferedReader br = request.getReader(); //per leggere dati da POST
	String tmp = null;

	while ((tmp = br.readLine()) != null){
	    sb.append(tmp);
	}

	tmp = sb.toString();

	
	//deserializzo i dati
	Gson gson = new Gson();
	LoginData logdata = gson.fromGson(tmp, LoginData.class);

	try (
	     // Step 1: Allocate a database 'Connection' object
	     Connection conn = DriverManager.getConnection(
							   "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
							   "myuser");   // For MySQL
	     // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

	     // Step 2: Allocate a 'Statement' object in the Connection
	     Statement stmt = conn.createStatement();
	     );
	String sqlStr = "SELECT password FROM user WHERE username = '" + logdata.username + "'";
	ResultSet rset = stmt.executeQuery(sqlStr);
	
	if (rset.next()) { // Se l'username è presente nel db verifico la password
	    boolean match = (logindata.password == rset.getString());
	    if (!match)
		out.print("psw");
	    else
		out.print("ok");
	} else {// ritorno l'errore id sbagliato all'utente
	    out.print("usr");
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

class LoginData{
    String username;
    String password;     
}
