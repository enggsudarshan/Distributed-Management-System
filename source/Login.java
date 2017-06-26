import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Login extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String user=req.getParameter("login").toUpperCase();
		String pass=req.getParameter("passwd");
		String pos=null;
		String code=null;
		String userid,passwd;
		HttpSession hs1=req.getSession(true);
		ResultSet rs=null;
		try
		{
			HttpSession hs=req.getSession(true);
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:dis");
			Statement st=con.createStatement();
			rs=st.executeQuery("select * from login where userid='"+user+"' and passwd='"+pass+"';"); 
			out.println("<html><head><TITLE> ABC Company </TITLE>");
			out.println("<style>");
			out.println("table{font-size:14pt;color:maroon}input{font-size:14pt;color:blue}");
			out.println("</style></HEAD>");
			out.println("<body bgcolor='E9FFB9' text='maroon'><center>");
			out.println("<h2><img src='http://localhost:8080/dms/images/rose1.gif' width=50><font size=5> ABC Company Welcomes U <img src='http://localhost:8080/dms/images/rose1.gif' width=50></h2><hr color=maroon size=5>");
			if(!rs.next()) 
			{
				out.println("<h3><center><font size=5><b>The given username is not found");
				out.println("<br><br>Please,Enter the valid Username !</font>");
				out.println("</b></pre>");
				out.println("<a href = 'http://localhost:8080/dms/home.html'>");
				out.println(" <img src='http://localhost:8080/dms/images/arrow.gif'></a>");
				out.println("</body></html>");
			}
			else if(user.equals((pass).toUpperCase()))
			{	
				hs.putValue("code",pass);
				res.sendRedirect("http://localhost:8080/dms/SalSign.html");
			}
			else
			{
				userid=rs.getString(1);
				code=rs.getString(3);
				pos=rs.getString(4);		
				hs.putValue("pos",pos);
				hs.putValue("user",userid);
				hs.putValue("code",code);
				if(pos.equals("A"))
                                        res.sendRedirect("http://localhost:8080/dms/AdminSign.html");
				else if(pos.equals("M"))
					res.sendRedirect("http://localhost:8080/servlet/ManagerSign");
				else if(pos.equals("H"))
					res.sendRedirect("http://localhost:8080/servlet/ManagerSign");
				else if (pos.equals("S"))
				{
					hs.putValue("code",code);
					res.sendRedirect("http://localhost:8080/dms/Salentryframe.html");
				}
				else if(pos.equals("D"))
				{
					res.sendRedirect("http://localhost:8080/dms/distributor.html");
				}
			}
		}catch(Exception e){out.println(e);}
		
}}
