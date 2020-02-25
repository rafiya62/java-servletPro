import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet
{
	Statement s;
	ResultSet rs;
	Connection con;
	
	private static final long serialVersionUID = 1L;
    public DoLogin()
    {
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		try 
		{
    			Class.forName("com.mysql.jdbc.Driver");
    			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			PrintWriter out=response.getWriter();
			String name1=request.getParameter("name");
			String pass1=request.getParameter("pass");	
			
			s=con.createStatement();
			rs=s.executeQuery("select name,pass from registration where name='"+name1+"'and pass='"+pass1+"'");	
			if(rs.next())
			{
				out.print("<script language='javascript'>");
				out.print("alert('login successful')");
				out.print("</script>");
				String name2=rs.getString(1);
				String pass2=rs.getString(2);
				
				//session to set user
				HttpSession session=request.getSession();
				session.setAttribute("un1",name1);
				out.print("Welcome"+name1);
				RequestDispatcher rd=request.getRequestDispatcher("home.html");
				rd.include(request, response);
				
				//set ServletContext to send value of which entered by user on user name field...
		    	ServletContext context1=getServletContext();
		    	context1.setAttribute("un1",name1);
			}
			else
			{
				out.print("<script language='javascript'>");
				out.print("alert('Incorrect username or password')");
				out.print("</script>");
				out.print("Incorrect Password");
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
			
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		//rs=ps.executeQuery();out.print("Welcome" +un);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
