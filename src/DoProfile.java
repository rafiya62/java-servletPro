import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/DoProfile")
public class DoProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		
		//To get value from DoLogin.java...ServletContext is used...
		ServletContext context1=getServletContext();
		String unn=(String)context1.getAttribute("un1");
		out.println("Hi"+unn);
		
		//session to restrict user.....
		if(session!=null)
		{
			String un=(String)session.getAttribute("un1");
			out.println("Welcome"+un);
			RequestDispatcher rd=request.getRequestDispatcher("home.html");
			rd.include(request, response);
		}
		else
		{
			out.print("plz login first...");
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		
	}

}
