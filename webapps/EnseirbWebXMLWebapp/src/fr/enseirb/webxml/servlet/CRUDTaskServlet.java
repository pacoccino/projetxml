package fr.enseirb.webxml.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;

import fr.enseirb.webxml.util.ServletToolkit;
import fr.enseirb.webxml.data.xml.XMLMediator;
import fr.enseirb.webxml.data.model.User;

/**
 * Servlet implementation class AboutServlet
 */
public class CRUDTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CRUDTaskServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sResponse;
		
		Properties props = ServletToolkit.parseURLParams(request);
		String taskXML;
		if(props.containsKey("title"))
		{
			int id=0;
			boolean done=false;
			if(props.containsKey("id"))
				id = Integer.parseInt(props.getProperty("id"));
			if(props.containsKey("done"))
				done = Boolean.valueOf(props.getProperty("done"));
				
			taskXML="<task id=\""+ id + "\" title=\""+ props.getProperty("title") + "\" deadline=\""+ props.getProperty("deadline") + "\" priority=\""+ props.getProperty("priority") + "\" done=\"" + done + "\">";
			taskXML=taskXML + "<description>"+props.getProperty("description")+" </description>";
			taskXML=taskXML + "<asker>"+props.getProperty("asker")+"</asker> ";
			taskXML=taskXML + "<owner>"+props.getProperty("owner")+"</owner> ";
			taskXML=taskXML + "</task>";
			
			if(XMLMediator.addOrModifyTask(taskXML))
				sResponse = "Tache "+props.getProperty("id")+" créé.";
			else
				sResponse = "Erreur lors de la creation de la tache";
		}
		else	
		{
			sResponse = "Aucun argument";
		}
		
		ServletToolkit.writeResponse(response, sResponse);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
