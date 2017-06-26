import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Login1 extends HttpServlet
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
		ResultSet rs=null;
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:dis");
			Statement st=con.createStatement();
			rs=st.executeQuery("select * from login where userid='"+user+"' and passwd='"+pass+"';"); 
			if(!rs.next()) 
			{
				res.sendRedirect("http://localhost:8080/dms/logininnvalid.html");
			}
			else if(user.equals((pass).toUpperCase()))
			{	
				res.sendRedirect("http://localhost:8080/dms/salreplogin.html");
				out.println("<center><form name='salform' method=post action='http://localhost:8080/servlet/salsign'>");
				out.println("<table><tr><td>Login Id </td><td>:</td><td><input type=text name=t1></td>");
				out.println("<tr><td>Password </td><td>:</td><td><input type=password name=t2></td>");
				out.println("<tr><td>Confirm Password</td><td>:</td><td><input type=password name=t3></td>");
				out.println("</tr></table><br>");
				out.println("<input type=hidden name=code='"+pass+"'>");
				out.println("<input type=submit value='Login'>&nbsp;&nbsp;<input type=reset value='Clear'>");
				out.println("</center></html>");
			}
			else
			{
				userid=rs.getString(1);
				passwd=rs.getString(2);
				code=rs.getString(3);
				pos=rs.getString(4);		
				if(pos.equals("A"))
				{
					out.println("<b><a href='http://localhost:8080/servlet/product'><font size=5>Product</a><br>");
					out.println("<a href='http://localhost:8080/salrepdet.html'><font size=5>Sales Rep Sale Details</a><br>");
					out.println("<a href='http://localhost:8080/report.html'><font size=5>Distributor Sale Details</a><br>");
					out.println("<a href='http://localhost:8080/servlet/dissalrep'><font size=5>Report</a><br>");
					out.println("<a href='http://localhost:8080/Comparison.html'><font size=5>FeedBack</a><br>");
					out.println("<a href='http://localhost:8080/servlet/salrepadd'><font size=5>Addition of Sales Rep</a><br><br>");
					out.println("<a href = 'http://localhost:8080/home.html'>");
					out.println(" <img src='http://localhost:8080/images/home1.gif'></a>");
					out.println("</body></html>");
				}
				else if(pos.equals("D"))
				{
					out.println("<b><form method=get action='http://localhost:8080/servlet/dissaldet'><input type=submit value='Sales Details'><input type = hidden name=login value='"+userid+"'><input type = hidden name=passwd value='"+passwd+"'><input type=hidden name=code value='"+code+"'></form>");
					out.println("<b><form method=get action='http://localhost:8080/servlet/disproview'><input type=submit value='Product View'><input type = hidden name=login value='"+userid+"'><input type = hidden name=passwd value='"+passwd+"'><input type=hidden name=code value='"+code+"'></form>");
					out.println("<b><form method=post action='http://localhost:8080/servlet/discomprep'><input type=submit value='Comparison'><input type = hidden name=login value='"+userid+"'><input type = hidden name=passwd value='"+passwd+"'><input type=hidden name=code value='"+code+"'></form>");
					out.println("<b><form method=get action='http://localhost:8080/servlet/dissalrep'><input type=submit value='Sales Report'><input type = hidden name=login value='"+userid+"'><input type = hidden name=passwd value='"+passwd+"'><input type=hidden name=code value='"+code+"'></form>");
					//out.println("<a href='http://localhost:8080/servlet/dissalrep'><font size=5>Sales Report</a><br><br>");
					out.println("<a href = 'http://localhost:8080/home.html'>");
					out.println(" <img src='http://localhost:8080/images/home1.gif'></a>");
					out.println("</body></html>");
				}
			}
		}catch(Exception e){out.println(e);}
}}