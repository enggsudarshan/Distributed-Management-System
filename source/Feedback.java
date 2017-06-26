import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class Feedback extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String a=null;
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	}
	catch(Exception ex){System.out.println("Error");}
	try
	{
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st1=con.createStatement();
		ResultSet rs=st1.executeQuery("select * from feedback");
		while(rs.next())
		{
			a=rs.getString(1);
		}
		if(a==null)
                        a="f1";
		else
		{
			int l=a.length();
			String l1=a.substring(1,l);
			int n=Integer.parseInt(l1)+1;
			a=a.substring(0,1)+Integer.toString(n);
		}
		st1.close();
		con.close();
		String code=req.getParameter("code");
		Connection con1=DriverManager.getConnection("jdbc:odbc:dis");
		PreparedStatement st=con1.prepareStatement("insert into feedback values(?,?,?,?,?,?,?,?)");
		st.setString(1,a);
		out.println(code+"+<br>");
		st.setString(2,req.getParameter("name"));
		st.setInt(3,new Integer(req.getParameter("phone")).intValue());
		Calendar c=Calendar.getInstance();
		String d=Integer.toString(c.get(Calendar.DATE))+"-"+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString(c.get(Calendar.YEAR));
		st.setString(4,d);
		st.setString(5,req.getParameter("email"));
		st.setString(6,req.getParameter("subject"));
		st.setString(7,req.getParameter("enquiry"));
		st.setString(8,code);
		st.execute();
		res.sendRedirect("http://localhost:8080/dms/thanks.html");
		st.close();
		con.close();
		}
		catch(Exception ex){out.println(ex);}
	}
}
