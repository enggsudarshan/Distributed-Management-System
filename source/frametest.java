import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class frametest extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	out.println("<FRAMESET COLS='20%,*' border=0>");
	out.println("<FRAME NAME=left SCROLL='NO' SRC='http://localhost:8080/servlet/Product'>");
	out.println("<FRAME NAME=left SCROLL='NO' SRC='http://localhost:8080/servlet/ProductTest'>");
	out.println("</FRAMESET>");
}}