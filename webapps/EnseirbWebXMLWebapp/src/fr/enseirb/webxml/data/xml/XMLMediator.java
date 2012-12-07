package fr.enseirb.webxml.data.xml;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.enseirb.webxml.data.dao.DBManager;
import fr.enseirb.webxml.data.model.Task;
import fr.enseirb.webxml.data.model.User;
import fr.enseirb.webxml.util.ServletToolkit;
import fr.enseirb.webxml.util.XMLToolkit;

public class XMLMediator {
	private static final Logger LOGGER = Logger.getLogger(XMLMediator.class.getName());

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat DATE_FORMAT_INV = new SimpleDateFormat("yyyyMMdd");

	public static final String createEmptyTaskXML() {
		StringBuilder buffer = new StringBuilder(XMLToolkit.XML_HEADER);
		appendTaskXML(new Task(), buffer);
		return buffer.toString();
	}

	private static final void appendTaskXML(Task task, StringBuilder buffer) {
		buffer.append("<task id=\"").append(task.getId()).append("\" ");
		buffer.append("title=\"").append(task.getTitle()).append("\" ");
		buffer.append("creationDate=\"").append(DATE_FORMAT.format(task.getCreationDate())).append("\" ");
		buffer.append("deadline=\"").append(DATE_FORMAT.format(task.getDeadline())).append("\" ");
		buffer.append("priority=\"").append(task.getPriority()).append("\" ");
		buffer.append("done=\"").append(Boolean.toString(task.isDone())).append("\">");
		buffer.append("<description>").append(task.getDescription()).append("</description>");
		buffer.append("<asker>").append(task.getAsker()).append("</asker>");
		buffer.append("<owner>").append(task.getOwner()).append("</owner>");
		buffer.append("</task>");
	}

	/*
	 * <tasks> <task id="xxx" title="ttt" deadline="dd/MM/yyyy" priority="123" done="true">
	 * <description>ddd</description> <asker>aaa</asker> <owner>ooo</owner> </task> <task id="yyy" title="ttt"
	 * deadline="dd/MM/yyyy" priority="123" done="false"> <description>ddd</description> <asker>aaa</asker>
	 * <owner>ooo</owner> </task> ... </tasks>
	 */
	public static String getTasks() {
		List<Task> tasks = DBManager.INSTANCE.getTaskDAO().getTasks();
		LOGGER.log(Level.INFO, "XML no Tasks are: " + tasks.size());
		Task task;

		Iterator <Task> tIterator = tasks.iterator();
		String tasksXML = "<tasks> ";
		while (tIterator.hasNext())
		{
			task = tIterator.next();
			
			tasksXML=tasksXML + "<task id=\"" + task.getId() + "\" title=\""+ task.getTitle() + "\" deadline=\""+ DATE_FORMAT.format(task.getDeadline()) + "\" priority=\""+ task.getPriority() + "\" done=\"" + task.isDone() + "\">";
			tasksXML=tasksXML + "<description>"+task.getDescription()+" </description>";
			tasksXML=tasksXML + "<asker>"+task.getAsker()+"</asker> ";
			tasksXML=tasksXML + "<owner>"+task.getOwner()+"</owner> ";
			
			
			tasksXML=tasksXML + "</task>";
		}
		tasksXML=tasksXML + "</tasks>";
		
		if(!(XMLToolkit.isXMLValid(tasksXML, "resources/xsd/tasks.xsd")))
			LOGGER.log(Level.INFO, "Erreur dans la validation");
		else
			LOGGER.log(Level.INFO, "XML Tasks are: " + tasksXML);
		return tasksXML;
	}

	public static String getTask(int id) {
		Task task = DBManager.INSTANCE.getTaskDAO().getTask(id);
		
		String taskXML;
		if(task==null)
			taskXML = "<task />";
		else
		{
			taskXML="<task id=\"" + task.getId() + "\" title=\""+ task.getTitle() + "\" deadline=\""+ task.getDeadline() + "\" priority=\""+ task.getPriority() + "\" done=\"" + task.isDone() + "\">";
			taskXML=taskXML + "<description>"+task.getDescription()+" </description>";
			taskXML=taskXML + "<asker>"+task.getAsker()+"</asker> ";
			taskXML=taskXML + "<owner>"+task.getOwner()+"</owner> ";
			taskXML=taskXML + "</task>";
		}
		LOGGER.log(Level.INFO, "XML Task with id " + id + " is: " + taskXML);
		return taskXML;
	}

	/*
	 * <task id="xxx" title="ttt" deadline="dd/MM/yyyy" priority="123" done="true"> <description>ddd</description>
	 * <asker>aaa</asker> <owner>ooo</owner> </task>
	 */
	public static boolean addOrModifyTask(String taskXML) {
		if(!(XMLToolkit.isXMLValid(taskXML, "resources/xsd/task.xsd")))
		{
			LOGGER.log(Level.INFO, "Erreur dans la validation");
			return false;			
		}
		LOGGER.log(Level.INFO, "Adding or modifying Task stream: " + taskXML);
				
		Document doc = XMLToolkit.parseDocument(taskXML);
		Element taskElt = doc.getDocumentElement();

		return addOrModifyTask(taskElt);
	}
	
	public static String myGetElt(Element elt, String chi) {
		return elt.getElementsByTagName(chi).item(0).getFirstChild().getNodeValue();
	}

	public static boolean addOrModifyTask(Element taskElt) {
		LOGGER.log(Level.INFO, "Adding or modifying Task element");

		boolean success;
		try {
			Task task = new Task();

			// TODO compléter la création de l'objet Task
			// pour la date, utiliser le code ci-dessous
			String id = taskElt.getAttribute("id");
			if (id != null && !id.isEmpty()) {
				task.setId(Integer.parseInt(id));
			}
			task.setTitle(taskElt.getAttribute("title"));
			task.setDescription(myGetElt(taskElt,"description"));
			task.setAsker(myGetElt(taskElt,"asker"));
			task.setOwner(myGetElt(taskElt,"owner"));
			task.setPriority(Integer.parseInt(taskElt.getAttribute("priority")));
			task.setDone(Boolean.valueOf(taskElt.getAttribute("done")));
			task.setDeadline(DATE_FORMAT.parse(taskElt.getAttribute("deadline")));
			
	
			success = DBManager.INSTANCE.getTaskDAO().addOrModify(task);
			if (success) {
				LOGGER.log(Level.INFO, "Task added");
				// on rajoute les utilisateurs au passage
				DBManager.INSTANCE.getUserDAO().addUser(new User(task.getAsker()));
				DBManager.INSTANCE.getUserDAO().addUser(new User(task.getOwner()));
			}
		} catch (ParseException e) {
			LOGGER.log(Level.INFO, "Problem while adding or modifying", e);
			success = false;
		}

		return success;
	}

	/*
	 * <users> <user name="xxx"/> <user name="yyy"/> ... </users>
	 */
	public static String getUsers() {
		List<User> users = DBManager.INSTANCE.getUserDAO().getUsers();

		Iterator <User> uIterator = users.iterator();
		String usersXML = "<users> ";
		while (uIterator.hasNext())
			usersXML=usersXML + "<user name=\"" + uIterator.next().getName()+"\"/> ";
		usersXML=usersXML + "</users>";
		
		if(!(XMLToolkit.isXMLValid(usersXML, "resources/xsd/users.xsd")))
			LOGGER.log(Level.INFO, "Erreur dans la validation");
		else
			LOGGER.log(Level.INFO, "XML Users are: " + usersXML);
		return usersXML;
	}

	/*
	 * <user name="xxx"/>
	 */
	public static boolean addUser(String userXML) {
		if(!(XMLToolkit.isXMLValid(userXML, "resources/xsd/user.xsd")))
		{
			LOGGER.log(Level.INFO, "Erreur dans la validation");
			return false;			
		}
		
		LOGGER.log(Level.INFO, "Adding User stream: " + userXML);

		Document doc = XMLToolkit.parseDocument(userXML);
		Element userElt = doc.getDocumentElement();
		return addUser(userElt);
	}

	public static boolean addUser(Element userElt) {
		LOGGER.log(Level.INFO, "Adding User element: ");
		
		String name = userElt.getAttribute("name");
		
		User user = new User(name);
		
		return DBManager.INSTANCE.getUserDAO().addUser(user);
	}

	public static String buildStats() {
		LOGGER.log(Level.INFO, "Building stats");
		String users = getUsers();
		String tasks = getTasks();

		String now = DATE_FORMAT.format(new Date());
		String day = now.substring(0, 2);
		String month = now.substring(3, 5);
		String year = now.substring(6, 10);

		String nowDate = year + month + day;
		String myNow = DATE_FORMAT_INV.format(new Date());
		String xPathDate = "concat(substring(@deadline,7,4), substring(@deadline,4,2), substring(@deadline,1,2))";

		// TODO : créer un flux XML pour les stats
		
		List <String> userList = XMLToolkit.getXPathValues(users, "/users/user/@name");

		List <String> taskList;
		List <String> doneList;
		List <String> todoList;
		List <String> lateList;
		List <String> intimeList;
		
		int showNull = -1;  // -1 affiche les nulls, 0 ne les affiche pas
		
		String statsXML = "<stats>";
				
		for(String name : userList)
		{
			statsXML += "<user name=\""+name+"\">";
			
			//String task = XMLToolkit.getXPathValue(tasks, "/tasks/task[owner='"+name+"']/@id");
			taskList = XMLToolkit.getXPathValues(tasks, "/tasks/task[owner='"+name+"']");
				statsXML += "<tasks number=\"" +taskList.size()+ "\">";
				doneList = XMLToolkit.getXPathValues(tasks, "/tasks/task[@done='true' and owner='"+name+"']/@title");
					statsXML += "<done number=\"" +doneList.size()+ "\">";
					for(String taskTitle : doneList)
						statsXML += "<task title=\"" +taskTitle+ "\"/>";
					statsXML += "</done>";
				todoList = XMLToolkit.getXPathValues(tasks, "/tasks/task[@done='false' and owner='"+name+"']/@title");
					statsXML += "<todo number=\"" +todoList.size()+ "\">";
					
					lateList = XMLToolkit.getXPathValues(tasks, "/tasks/task[@done='false' and owner='"+name+"' and "+xPathDate+"<"+nowDate+"]/@title");
						statsXML += "<late number=\"" +lateList.size()+ "\">";
						for(String taskTitle : lateList)
							statsXML += "<task title=\"" +taskTitle+ "\"/>";
						statsXML += "</late>";
	
					intimeList = XMLToolkit.getXPathValues(tasks, "/tasks/task[@done='false' and owner='"+name+"' and "+xPathDate+">"+nowDate+"]/@title");
						statsXML += "<intime number=\"" +intimeList.size()+ "\">";
						for(String taskTitle : intimeList)
							statsXML += "<task title=\"" +taskTitle+ "\"/>";
						statsXML += "</intime>";
	
						
					statsXML += "</todo>";
				
				statsXML += "</tasks>";
			
	
			//statsXML += XMLToolkit.getXPathValue(s, "/@name");
			statsXML += "</user>";
		}
		
		
		statsXML += "</stats>";
		LOGGER.log(Level.INFO, "Stats built: " + statsXML);
		return statsXML;
	}

	public static boolean parseInitDB(String initDBPath) {
		LOGGER.log(Level.INFO, "Parsing init DB file: " + initDBPath);

		String initDBStream;
		try {
			initDBStream = ServletToolkit.readFile(initDBPath);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error while parsing init DB file");
			initDBStream = null;
		}

		if (initDBStream != null) {
			return importXMLTasks(initDBStream);
		} else {
			return false;
		}
	}

	public static boolean importXMLTasks(String initDBStream) {
		LOGGER.log(Level.INFO, "Importing tasks");

		boolean success;
		try {
//			if (!XMLToolkit.isXMLValid(initDBStream, "/resources/xsd/db_dump.xsd")) {
//				throw new Exception("Init file is not valid against XMLSchema");
//			}

			Document doc = XMLToolkit.parseDocument(initDBStream);
			Element rootElt = doc.getDocumentElement();

			NodeList tasksNodes = rootElt.getElementsByTagName("tasks");
			if (tasksNodes.getLength() > 1) {
				throw new Exception("More than 1 <tasks> element");
			}

			NodeList usersNodes = rootElt.getElementsByTagName("users");
			if (tasksNodes.getLength() > 1) {
				throw new Exception("More than 1 <users> element");
			}

			if (tasksNodes.getLength() == 1) {
				// on ajoute les tâches
				Element tasksNode = (Element) tasksNodes.item(0);
				NodeList taskNodes = tasksNode.getElementsByTagName("task");
				for (int i = 0; i < taskNodes.getLength(); i++) {
					Element taskNode = (Element) taskNodes.item(i);
					if (!addOrModifyTask(taskNode)) {
						throw new Exception("Error while adding task");
					}
				}
			}

			if (usersNodes.getLength() == 1) {
				// on ajoute les users
				Element usersNode = (Element) usersNodes.item(0);
				NodeList userNodes = usersNode.getElementsByTagName("user");
				for (int i = 0; i < userNodes.getLength(); i++) {
					Element userNode = (Element) userNodes.item(i);
					if (!addUser(userNode)) {
						throw new Exception("Error while adding user");
					}
				}
			}

			success = true;
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "Error while importing data in db", e);
			success = false;
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Error while importing data in db", e);
			success = false;
		}

		return success;
	}

}
