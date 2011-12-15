package de.gawi.injectservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.gawi.vaadin_server.ServerApplication;

/**
 * Servlet implementation class InjectServlet
 */
public class InjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String cummo = exec(request);
		
		PrintWriter writer = response.getWriter();
		
		writer.println("<html>");
		writer.println("<head><title>Inject Servlet</title></head>");
		writer.println("<body>");
		writer.println("	<h1>Try to inject Vaadin Context!6\n\n" + cummo + "</h1>");
		writer.println("<body>");
		writer.println("</html>");
			
		writer.close();
		
		
	}

	private String exec(HttpServletRequest request) {
		
		String cummo = "\n<br/>";
		
		List<ServerApplication> users = ServerApplication.users;
		for( ServerApplication user : users)
		{
			user.inject();			
			cummo += "\n<br/>" + user.toString();			
		}
		
		cummo += "\n\n<br/><br/>va_cnt: " + users.size();
		return cummo;
	}

}
