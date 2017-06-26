import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class ManagerSign extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		HttpSession hs1=req.getSession(true);
		String title=null;
		String pos=(String)hs1.getValue("pos");
		if(pos.equals("M"))
			title="Sale's Manager";
		else
			title="Manager";
		out.println("<HTML><HEAD><style>a{font-family:Bookman Old Style;font-size:14pt;color:maroon</style>");
		out.println("<TITLE>"+title+"</TITLE></HEAD>");
		out.println("<BODY bgcolor=#ffddbb text=blue><center><img src='http://localhost:8080/dms/images/redline.gif'>");
		out.println("<h2><marquee direction=left behavior=alternate>ABC Company's "+title+" Page</marquee></h2>");
		out.println("<img src='http://localhost:8080/dms/images/redline.gif'><br><br>");
		out.println("<a href='http://localhost:8080/servlet/MangProdView'>Product View</a><br><br>");
		out.println("<a href='http://localhost:8080/dms/passchange.html'>Change Password</a><br><br>");
		out.println("<form method=post action='http://localhost:8080/servlet/ManagerFrame'>");
		out.println("<input type=submit value='Sales Rep Sales Report'><input type=hidden value=1 name=rep></form>");
		out.println("<form method=post action='http://localhost:8080/servlet/ManagerFrame'>");
		out.println("<input type=submit value='Distributor Sales Report'><input type=hidden value=2 name=rep></form>");
		if(pos.equals("H"))
			out.println("<a href='http://localhost:8080/servlet/FeedbackRep'>Feedback</a><br><br>");
		out.println("<a href='http://localhost:8080/dms/home.html'><img src='http://localhost:8080/dms/images/home1.gif'></a></center>");
		out.println("</BODY></HTML>");
	}
}