import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class FilterServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/plain");
    PrintWriter out = response.getWriter();
    String subject = request.getParameter("subject");
    String teacher = request.getParameter("teacher");
    if (teacher == null) {
      out.println("subject");
    } else {
      out.println("subject\nteacher");
    }     
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
      processRequest(request, response);
    }
}
