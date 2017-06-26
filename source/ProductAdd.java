import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ProductAdd extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String a=null;
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from product");
		while(rs.next())
		{
			a=rs.getString(1);
		}
		if(a==null)
			a="PR1";
		else
		{
			int l=a.length();
			String l1=a.substring(2,l);
			Integer i=new Integer(l1);
			int n=i.intValue()+1;
			a=a.substring(0,2)+Integer.toString(n);
		}
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
	out.println("<html><title>Product Addition</title><style>");	
	out.println("input{font-size:13pt;color:blue;}");
	out.println("table{font-size:15pt;color:maroon;}");
	out.println("</style>");
	out.println("<center><h1><font color='blue'>Product Addition</font></h1>");
	out.println("<hr size=6 color='maroon'>");
	out.println("<body bgcolor='#e2e0d2'>");
	out.println("<FORM name=productadd METHOD=post ACTION='http://localhost:8080/servlet/ProductAdd1'><font face='timesnewroman' size=10>");
	out.println("<table cellspacing=10><tr><td>Product Code</td><td>: </td><td><INPUT TYPE='text' NAME='pcode' value='"+a+"'></td></tr>");
	out.println("<tr><td>Product Name </td><td>: </td><td><INPUT TYPE='text' NAME='pname'></td></tr>");
	out.println("<tr><td>Amount</td><td>: </td><td><INPUT TYPE='text' NAME='amount'></td></tr>");
	out.println("</table>");
	out.println("<INPUT TYPE='submit' value='Add'>&nbsp;&nbsp;&nbsp<INPUT TYPE='reset' value='Cancel'>");
	out.println("</FORM></font>");
	out.println(" <table><tr><td><br><br><a href='http://localhost:8080/dms/AdminSign.html' target='_top'><img src='http://localhost:8080/dms/images/back_1.gif'></a>");
	out.println("</td><td><a href = 'http://localhost:8080/dms/home.html' target='_top'><br><br>");
	out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a></td></tr></table>");
	out.println("</center></body></html>");
}
}