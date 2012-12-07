package fr.enseirb.webxml.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enseirb.webxml.util.ServletToolkit;
import fr.enseirb.webxml.data.xml.XMLMediator;

/**
 * Servlet implementation class DefaultServlet
 */
public class StatsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public StatsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sResponse = XMLMediator.buildStats();
		response.setHeader("Content-Type", "application/xml");

		ServletToolkit.writeResponse(response, sResponse);
	}

}
