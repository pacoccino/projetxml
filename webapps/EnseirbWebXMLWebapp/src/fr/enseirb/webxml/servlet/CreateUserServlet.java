package fr.enseirb.webxml.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import java.util.Map;
import java.util.HashMap;

import fr.enseirb.webxml.util.ServletToolkit;
import fr.enseirb.webxml.util.XMLToolkit;
import fr.enseirb.webxml.data.xml.XMLMediator;
import fr.enseirb.webxml.data.model.User;

/**
 * Servlet implementation class AboutServlet
 */
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sResponse;
		
		if(request.getRequestURI().contains("user/create/url"))
		{
			Properties props = ServletToolkit.parseURLParams(request);
			String userXML;
			if(props.containsKey("name"))
			{
				userXML ="<user name=\""+ props.getProperty("name") +"\"/>";
				if(XMLMediator.addUser(userXML))
					sResponse = "Utilisateur "+props.getProperty("name")+" créé.";
				else
					sResponse = "Erreur lors de la creation de l'utilisateur";
			}
			else	
			{
				sResponse = "Aucun argument";
			}
		}
		else
		{
			Map<String, String> xslParams = new HashMap<String, String>();
			xslParams.put("pageTitle", "Ajout utilisateur");
			xslParams.put("htmlTitle", "Utilisateur à ajouter");
			sResponse=XMLToolkit.transformXML("<noXML/>", "/resources/xsl/common/create_user.xsl", xslParams);
		}
		ServletToolkit.writeResponse(response, sResponse);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
