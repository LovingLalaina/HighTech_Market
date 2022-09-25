
package controleur;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.MaterielBean;
import modele.UtilisateurBean;

@WebServlet( name = "Client" , urlPatterns = { "/Client" } )

public class Client extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	   
    /*
     * Chemin dans lequel les images seront sauvegardées.
     */
    public static final String CHEMIN_IMAGES = "/BaseDeDonnees/ImageS/Materiel/";
        
    public String uploadPath;
    public File uploadDir;
    
    /*
     * Si le dossier de sauvegarde de l'image n'existe pas, on demande sa création.
     */ 
    @Override
    public void init() throws ServletException {
        uploadPath = getServletContext().getRealPath( CHEMIN_IMAGES );
        uploadDir = new File( uploadPath );
        if ( !uploadDir.exists() ) uploadDir.mkdir();
    }
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
            this.getServletContext().getRequestDispatcher( "/materiel.jsp" ).forward( request , response );
        }
        else
        {
            //L'UTILISATEUR EST BEL ET BIEN UN ADMINISTRATEUR
            request.setAttribute( "Utilisateurs", new UtilisateurBean().afficher( request ) );
            this.getServletContext().getRequestDispatcher( "/client.jsp" ).forward( request , response );
        }
	}
	
	protected void doPost( HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException
	{
		//TRAITEMENT BACK-END DE LA CREATION D'UN CLIENT
        UtilisateurBean monClient = new UtilisateurBean();
        String redirection = monClient.ajouter( request );
        request.setAttribute( "MaterielComplet" , new MaterielBean( this.uploadPath ).afficher( request ) );
        this.getServletContext().getRequestDispatcher( redirection ).forward( request , response );
	}

}
