import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class viewData
 */
@WebServlet("/viewData")
public class viewData extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Connection con;
	Statement s;
	ResultSet rs;
	
    public viewData() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
			s=con.createStatement();
			rs=s.executeQuery("select * from registration");	
			out.print("<table border=2 align=center>");
			out.print("<tr>");
			out.print("<th>Name</th>");
			out.print("<th>Password'</th>");
			out.print("<th>City</th>");
			out.print("<th>Address</th>");
			out.print("</tr>");
			while(rs.next())
			{
				
				String name2=rs.getString(1);
				String pass2=rs.getString(2);
				String city2=rs.getString(3);
				String addr2=rs.getString(4);
				out.print("<tr>");
				out.print("<td>"+rs.getString(1)+"</td>");
				out.print("<td>"+rs.getString(2)+"</td>");
				out.print("<td>"+rs.getString(3)+"</td>");
				out.print("<td>"+rs.getString(4)+"</td>");
				out.print("</tr>");
				
				
				
			}
			
			out.print("</table>");
			RequestDispatcher rd=request.getRequestDispatcher("home.html");
			rd.include(request, response);
			
			
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
