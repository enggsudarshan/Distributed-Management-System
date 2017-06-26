import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ManagerFrame extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String file=null;
	out.println("<html><title>Manager's Page</title> <style>");	
	out.println("input{font-size:12pt;color:blue;}table{font-size:15pt;color:maroon;font-style:bold}");
	out.println("</style>");
	out.println("<frameset cols='30%,*'>");
	if(req.getParameter("rep").equals("1"))
		file="http://localhost:8080/dms/mansalrep.html";
	else
		file="http://localhost:8080/dms/mandisrep.html";
	out.println("<frame name=leftframe scroll='no' src='"+file+"'>");
	out.println("<frame name=second scroll='no' src='http://localhost:8080/dms/empty1.html'>");
	out.println("</frameset>");
	out.println("</html>");
}
}