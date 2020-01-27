import java.io.*;
import java.sql.*;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FilterServlet")
class FilterServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    
              try (
             // Step 1: Allocate a database 'Connection' object
             Connection conn = DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                   "myuser", "xxxx");   // For MySQL
                   // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

             // Step 2: Allocate a 'Statement' object in the Connection
             Statement stmt = conn.createStatement();
          )
                
    response.setContentType("text/plain");
    PrintWriter out = response.getWriter();
    String subject = request.getParameter("subject");
    String teacher = request.getParameter("teacher");
    if (teacher == null) {
      out.println("subject");
    } else {
      out.println("subject\nteacher");
    }   
             // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         // Step 3: Execute a SQL SELECT query
         String sqlStr = "select * from books where author = "
               + "'" + request.getParameter("author") + "'"   // Single-quote SQL string
               + " and qty > 0 order by price desc";
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
      processRequest(request, response);
    }
}
