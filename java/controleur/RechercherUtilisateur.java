
package controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.UtilisateurBean;


@WebServlet( name = "RechercherUtilisateur" , urlPatterns = { "/RechercherUtilisateur" } )

public class RechercherUtilisateur extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    @Override
	protected void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException
	{
    	HttpSession maSession = request.getSession();
        if( maSession.getAttribute( "type" ) == null )
        {
            maSession.setAttribute( "message" , "Veuillez vous connecter en tant qu'administrateur!!" );
            maSession.setAttribute( "typeMessage" , "danger");
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request , response );
        }
        else if( maSession.getAttribute( "type" ).equals( "client" ) )
        {
            maSession.setAttribute( "message" , "Veuillez vous connecter en tant qu'administrateur!!" );
            maSession.setAttribute( "typeMessage" , "danger");
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request , response );
        }
        else
        {
            //L'UTILISATEUR EST BEL ET BIEN UN ADMINISTRATEUR
            request.setAttribute( "Utilisateurs", new UtilisateurBean().rechercher( request ) );
            this.getServletContext().getRequestDispatcher( "/rechercheClient.jsp" ).forward( request , response );
        }
	}
	
    @Override
	protected void doPost( HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException
	{
    	doGet( request , response );
	}

}
