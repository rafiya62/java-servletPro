import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Registration")
public class Registration extends HttpServlet 
{
	Connection con;
	PreparedStatement ps;
	private static final long serialVersionUID = 1L;
    public Registration()
    {
        super();
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
    	
		

    	PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		String city=request.getParameter("city");
		String addr=request.getParameter("addr");
		
		try 
		{
			ps=con.prepareStatement("insert into registration values(?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,pass);
			ps.setString(3,city);
			ps.setString(4,addr);
			ps.executeUpdate();
			out.print("Registered Success fully");
			out.print("<script language='javascript'>");
			out.print("alert('Registered Success fully')");
			out.print("</script>");
			
			
			
	  }
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
	}
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}
	

}
