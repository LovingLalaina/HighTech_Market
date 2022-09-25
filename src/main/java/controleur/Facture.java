
package controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.FactureBean;

@WebServlet( name = "Facture" , urlPatterns = { "/Facture" } )

public class Facture extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException
    {
        HttpSession maSession = request.getSession();
        if( maSession.getAttribute("type") == null )
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request , response );
        else
        {
        	request.setAttribute( "Facture" , new FactureBean().facturer( request ) );
        	this.getServletContext().getRequestDispatcher( "/facture.jsp" ).forward( request , response );
        }
    }

    @Override
    protected void doPost( HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException
    {
        //GROS TRAITEMENT BACK-END DE L'AFFICHAGE DE FACTURE
       this.getServletContext().getRequestDispatcher( "/facture.jsp" ).forward( request , response ); 
    }
}
