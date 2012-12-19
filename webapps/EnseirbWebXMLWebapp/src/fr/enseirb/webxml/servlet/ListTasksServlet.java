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
import fr.enseirb.webxml.data.xml.XMLMediator;
import fr.enseirb.webxml.util.XMLToolkit;

/**
 * Servlet implementation class AboutServlet
 */
public class ListTasksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int studentsNumber = 30;
	private int studentGroup = 2;
	private String studentClass = new String("I2");
	private String studentFirstName = new String("Pacien");
	private String studentLastName = new String("Boisson");
	private String teacherName = new String("a");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListTasksServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sResponse;
		
		Properties props = ServletToolkit.parseURLParams(request);
		if(request.getRequestURI().contains("task/list/xml"))
		{
			response.setHeader("Content-Type", "application/xml");
			if(props.containsKey("id"))
				sResponse = XMLMediator.getTask(Integer.parseInt(props.getProperty("id")));
			else	
				sResponse = XMLMediator.getTasks();
		}
		else
		{
			Map<String, String> xslParams = new HashMap<String, String>();
			xslParams.put("pageTitle", "Ajout utilisateur");
			
			String fluxXML = XMLMediator.getTasks();
			sResponse=XMLToolkit.transformXML(fluxXML, "/resources/xsl/common/task_list.xsl", xslParams);
		}
		ServletToolkit.writeResponse(response, sResponse);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
