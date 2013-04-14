package lcijsp;
import lcijava.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action.equals("login"))
		{
			HttpSession session = request.getSession();
			String username = request.getParameter("tusername");
			String password = request.getParameter("tpassword");
			
			String message;
			if (!username.equals("") && !password.equals(""))
			{
				lcijava.Login login=new lcijava.Login();
				if(login.authenticate(username,password))
				{
					message = "SUCCESS";
					session.setAttribute("auth", true);
				}
				else
					message = "Wrong userid pwd";
			}
			else
			{
				message = "Please enter username and pwd";
			}
			response.sendRedirect("login.jsp?msg=" + message);
		}
		
	}
}
