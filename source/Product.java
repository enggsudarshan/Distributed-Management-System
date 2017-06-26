import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class Product extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	out.println("<html><title>Product</title><style>");
	out.println("<!-- .plain{BORDER: 2px solid blue;}");
	//out.println("<!-- .plain { BORDER-RIGHT: 2px solid blue; BORDER-TOP: 2px solid blue; FONT-SIZE: 12px; FONT-WEIGHT: Bold; BORDER-LEFT: 2px solid blue; BORDER-BOTTOM: 2px solid blue; FONT-FAMILY: verdana; Background-color:#CCCCCC;}-->");
	out.println("INPUT{font-size:13pt;color:blue;}");
	out.println("</style>");
	out.println("<center><h1><font color='9900cc'>Product Page </font></h1>");
	out.println("<hr size=6 color='maroon'>");
	out.println("<body bgcolor='#e2e0d2'>");
	out.println("<table ><tr align=center><td><FORM METHOD=post ACTION='http://localhost:8080/servlet/ProductAdd' target='second'><INPUT TYPE='submit' value='Add' class=plain>");
	out.println("</FORM></td></tr>");
	out.println("<tr align=center><td><FORM METHOD=post ACTION='http://localhost:8080/servlet/ProductView' target='second'><INPUT TYPE='submit' value='View' class=plain>");
	out.println("</FORM></td></tr>");
	out.println("<tr align=center><td><FORM METHOD=post ACTION='http://localhost:8080/servlet/ProductDel' target='second'><INPUT TYPE='submit' value='Delete' class=plain>");
	out.println("<input type=hidden name=type value='1'></FORM></td></tr>");
	out.println("<tr align=center><td><FORM METHOD=post ACTION='http://localhost:8080/servlet/ProductDel' target='second'><INPUT TYPE='submit' value='Modify' class=plain>");
	out.println("<input type=hidden name=type value='2'></FORM></td></tr></table>");
        out.println("<a href = 'http://localhost:8080/dms/AdminSign.html' target=_top>");
	out.println("<img src='http://localhost:8080/dms/images/back_1.gif' width=40></a>");
	out.println("</font></body></html>");
}
}
