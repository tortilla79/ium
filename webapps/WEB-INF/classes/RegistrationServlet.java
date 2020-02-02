import java.io.*;
import java.sql.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {

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
	RegistrationData logdata = new RegistrationData();
	HashMap<String, String> nameValue = Gson.fromJson(tmp);
	logdata.username = nameValue.get("username");
	logdata.email = nameValue.get("email");
	logdata.password = nameValue.get("password");
	logdata.passwordrip = nameValue.get("passwordrip");
	boolean match = logdata.password.equals(logdata.passwordrip);
	if (logdata.password.length() < 8) { // verifico che la password sia di almeno 8 caratteri
	    out.print("psw");
	    out.close();   
	}
	else if (!match) { 	// verifico che la pssword sia stata confermata correttamente
	    out.print("pswrip");
	    out.close();
	} else { 	// verifico l'eventuale presenza di uname nel database
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
	    
		if (rset.next()) { // Se l'username gia esiste lo comunico al client
		    out.print("uname");
		} else {// registro il nuovo utente
		    sqlStr = "INSERT INTO user (privilege, username, password, email) VALUES('client','" +
			logdata.username +"','"+ logdata.password + "','" + logdata.email+ "')"; 
		    stmt.executeQuery(sqlStr);
		    out.print("ok");
		}
	    } catch (Exception ex) {
		out.print("dberror");
		ex.printStackTrace();
	    }
	}
	/*    String registered = "piero";
	    if (logdata.username.equals(registered))
		out.print("uname");
	    else
		out.print(logdata.toString());
	    out.close();
	*/  
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

    class RegistrationData{
	String username;
	String email;
	String password;
	String passwordrip;
    }
