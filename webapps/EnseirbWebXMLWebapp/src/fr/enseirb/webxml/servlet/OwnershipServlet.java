package fr.enseirb.webxml.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.w3c.dom.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.*;
import java.io.*;
import javax.xml.transform.stream.*;

import fr.enseirb.webxml.util.StringFormatUtil;
import fr.enseirb.webxml.util.ServletToolkit;
import fr.enseirb.webxml.data.xml.XMLMediator;
import fr.enseirb.webxml.util.XMLToolkit;
import fr.enseirb.webxml.data.model.User;

/**
 * Servlet implementation class AboutServlet
 */
public class OwnershipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OwnershipServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sResponse = new String("coucou");
		
		Properties props;
		
		String postData;
		
		if(request.getRequestURI().contains("task/user/ownership/post/isValid") || request.getRequestURI().contains("user/task/ownership/post/isValid"))
		{
			postData = ServletToolkit.getPostData(request);
			String ownershipXML = postData;
			boolean success = XMLToolkit.isXMLValid(ownershipXML, "resources/xsd/ownership.xsd");
			sResponse = XMLToolkit.createPostResult("Validation Ownership", success);
		}
		else if(request.getRequestURI().contains("task/user/ownership/post") || request.getRequestURI().contains("user/task/ownership/post"))
		{
			int taskId;
			String userName;
			
			postData = ServletToolkit.getPostData(request);
			Document doc = XMLToolkit.parseDocument(postData);
			Element ownershipElt = doc.getDocumentElement();
			
			taskId = Integer.parseInt(ownershipElt.getAttribute("taskId"));
			userName = ownershipElt.getAttribute("userName");
			
			String taskXML = XMLMediator.getTask(taskId);
			doc = XMLToolkit.parseDocument(taskXML);
			Element taskElt = doc.getDocumentElement();
			
			Element ownerElt = (Element) taskElt.getElementsByTagName("owner").item(0);
			ownerElt.setTextContent(userName);
		
			
			
			if(XMLMediator.addOrModifyTask(taskElt))
				sResponse = new String("Proprietaire modifie");
			else
				sResponse = new String("erreur");
			
		}
		else if(request.getRequestURI().contains("user/ownership"))
		{
			props = ServletToolkit.parseURLParams(request);
			//int taskId = Integer.parseInt(props.getProperty("taskId"));
			String userName = props.getProperty("userName");
			response.setHeader("Content-Type", "text/plain");
			String tasksXML = XMLMediator.getTasks();
			
			List <String> idList = XMLToolkit.getXPathValues(tasksXML, "/tasks/task[owner='"+userName+"']/@id");
			
			sResponse = StringFormatUtil.sortAndFormat(idList);

			
		}
		else if(request.getRequestURI().contains("task/ownership/csv"))
		{
			response.setHeader("Content-Type", "text/plain");

			Map<String, String> xslParams = new HashMap<String, String>();
			xslParams.put("pageTitle", "Ajout utilisateur");
			
			String fluxXML = XMLMediator.getTasks();
			sResponse=XMLToolkit.transformXML(fluxXML, "/resources/xsl/common/ownership.xsl", xslParams);

		}
		else if(request.getRequestURI().contains("task/ownership"))
		{
			props = ServletToolkit.parseURLParams(request);
			int taskId = Integer.parseInt(props.getProperty("taskId"));
			//String userName = props.getProperty("userName");
			response.setHeader("Content-Type", "text/plain");
			String tasksXML = XMLMediator.getTasks();
			
			List <String> idList = XMLToolkit.getXPathValues(tasksXML, "/tasks/task[@id='"+taskId+"']/owner/text()");
			
			sResponse = StringFormatUtil.sortAndFormat(idList);
			
			
		}

		ServletToolkit.writeResponse(response, sResponse);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
