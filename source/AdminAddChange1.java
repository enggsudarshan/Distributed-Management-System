import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class AdminAddChange1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	Enumeration e=req.getParameterNames();
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		Statement st1=con.createStatement();
		PreparedStatement ps=null;
		ResultSet rs=null;
		out.println("<html><title>Address Change Page</title> <style>");	
		out.println("input{font-size:12pt;color:blue;}table{font-size:12pt;color:maroon;font-weight:bold}");
		out.println("</style>");
		out.println("<body bgcolor='#e2e0d2' text='maroon'><center>");
		out.println("<h1><u>Address Modified Page</u></h1>");
		while(e.hasMoreElements())
		{
			String name=(String)e.nextElement();
			String value = (String) req.getParameter(name);
			rs=st.executeQuery("select add1,add2,city,state,pincode from addch where code='"+name+"'");
			rs.next();
			if((name.substring(0,1)).equals("r"))
					st1.executeUpdate("update rep set add1='"+rs.getString(1)+"' , add2='"+rs.getString(2)+"', city='"+rs.getString(3)+"', state='"+rs.getString(4)+"', pincode="+rs.getInt(5)+" where scode='"+name+"'");
			else
					st1.executeUpdate("update disdetails set add1='"+rs.getString(1)+"' , add2='"+rs.getString(2)+"', city='"+rs.getString(3)+"', state='"+rs.getString(4)+"', pincode="+rs.getInt(5)+" where dcode='"+name+"'");
			ps=con.prepareStatement("delete * from addch where code='"+name+"'");
			ps.execute();
			out.println("<font size=4>"+name+"'s addres is modified<br></h1>");
		}
		rs.close();
		st1.close();
		ps.close();
		}	catch(Exception ex){out.println(ex);}
		out.println("<table><tr><td><a href='http://localhost:8080/dms/AdminSign.html'><img src='http://localhost:8080/dms/images/back_1.gif'></a>");
		out.println("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><a href='http://localhost:8080/dms/home.html'>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif' ></a></td></tr></table></center>");
		out.println("</table></center></body></html>");
	}
}