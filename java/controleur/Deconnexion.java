package controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet( name = "Deconnexion" , urlPatterns = { "/Deconnexion" } )

public class Deconnexion extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException
    {
    	HttpSession maSession = request.getSession();
    	maSession.setAttribute( "type" , null );
        maSession.setAttribute( "UtilisateurActuel" , null );
        this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request , response );
	}

    @Override
	protected void doPost( HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException
    {
    	
	}

}
