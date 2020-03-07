import java.io.*;
import java.sql.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response )
	throws ServletException, IOException{
	PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder(); 
	BufferedReader br = request.getReader(); //per leggere dati da POST
	String tmp = null;

	while ((tmp = br.readLine()) != null){
	    sb.append(tmp);
	}

	tmp = sb.toString();

	//deserializzo i dati
	LoginData logdata = new LoginData();
	HashMap<String, String> nameValue = Gson.fromJson(tmp);
	logdata.username = nameValue.get("username");
	logdata.password = nameValue.get("password");

	try (
	     // Step 1: Allocate a database 'Connection' object
	     Connection conn = DriverManager.getConnection(
							   "jdbc:mysql://localhost:3306/sblocknotes?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
							   "bobu", "gattopardo");   // For MySQL
	     // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

	     // Step 2: Allocate a 'Statement' object in the Connection
	     Statement stmt = conn.createStatement();
	     ) {
	    String sqlStr = "SELECT * FROM user WHERE username = '" + logdata.username + "'";
	    ResultSet rset = stmt.executeQuery(sqlStr);
	    
	    if (!rset.next())  // Se l'username non esiste lo comunico al client
		out.print("usr");
	    else if (!rset.getString("password").equals(logdata.password))
		out.print("usr");
	    else { //login
		HttpSession session = request.getSession();
		if(session.isNew())
		    session.setAttribute("user", logdata.username);
		
		out.print("ok " + response.encodeUrl("user.jsp"));
	    }
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

class LoginData{
    String username;
    String password;
}
