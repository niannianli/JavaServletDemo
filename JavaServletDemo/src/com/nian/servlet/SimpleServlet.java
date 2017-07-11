package com.nian.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//page.html default project url page, has a form, submit form, trigger demo.do, trigger SimpleServlet class, trigger service()method
//index.jsp can be visied by adding to the end of project url
public class SimpleServlet extends HttpServlet {

	// ServletConfig
	// tomcat created the servlet object

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
/**	String username;

	// use servletconfig for one servlet
	@Override
	public void init(ServletConfig config) throws ServletException {
		username = config.getInitParameter("username");

		//or, use servlet context for all, this object only created one time
		ServletContext context = config.getServletContext();
		username = context.getInitParameter("username");
		// now you get value root
	}
*/
	// how to define this to jsp?
	int var;
	// <%! %> declarative to define variables/methods
	// but how to invoke the method? how to use this method in jsp?
	void method() {

	}
	
	// HttpServlet class
	// Servilet API

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// contrl + space:
		
		// get data from url parameter = value;
		String name = request.getParameter("name");
		// String age = request.getParameter("age");
		System.out.println(name);

		// now send a response
		// write a html page here
		PrintWriter out = response.getWriter();
		// char stream
		out.println("<html>");
		out.println("<body>");
		out.println("<h1 style='color:red'>Hello " + name + "</h1>");
		out.println("</body>");
		out.println("</html>");
		out.close();

		// instead wrting html in service class
		// we can simply use JSP
		// Java Server Page
		// we can add html in jsp
	
		// byte stream: ByteStream...printStream

		// user using url to send request to web server
		// same user using same url to send 2nd time request

		System.out.println(new Date());

	}

}
// demo.do in form tag

// servlet? // what is servlet?
// a small programm in server to response to user request

// get or post

// get: localhost:8080/StudentDemo/demo.do?name=nian&age=34
// show parameter=value
// "get" request: send data in url:

// post: protect data from showing in url

// localhost:8080/StudentDemo/page.html
// submit data here
// got same url with data presented
// localhost:8080/StudentDemo/demo.do?name=nian&age=34

// ? whats wrong?
// name="name" in table will seen as parameter
// method="get"> by default

// now use post:
// localhost:8080/StudentDemo/demo.do
// data not presented in url: but data did get passed

// firebug:firefox:Network

// url: StudentDemo/index.jsp
// no need to configure
// you can write java code in jsp

// html: static
// Jsp: both html + java

// jsp is :

// <% %> Scriptlet

// <%@ %> declaration tag: to import

// java code internally is converted to servlet class
// and added to the service method automatically

// html code is converted to printerwriter and output

// how to print date on browser?

// use response object, use printerwriter to write date into html page

// instead, in jsp:
// expression tag: <%= new Date() %>

// jsp is converted to servlet
// works like service method
// but much simpler

// now : how below works?
// localhost:8080/StudentDemo/index.jsp?name=nian&age=34

// how to get data
// not using html, service method, request object now

// jsp already has request and respons object
// thats why

// jsp implicit objects:
// request, responst, out, config, session, page
// use them directly

// PrinterWriter not necessary
// just use out directly in jsp

// or just use
// <%= name %>

// SimpleServlet: service method:
// why no object is created
// but the non-static service method is invoked

// tomcat server created the object and invoked
// the service method?

// user request
// then tomcat server created servlet object
// call service method

// another user request to invoke service
// will another object be created?
// no, only one object is created,
// just call the method again for another user

// same object: several threads for several users

// request / response also created by tomcat
// differnt request/response for different user

// ServletConfig

// what if you have 2 servlets?
// each servlet has one servletconfig
// and only avaiable for each separate servletconfig

// servlet1:

// servlet2:

// what if the data is shared by all servletconfigs
// then use servletcontext: this object is created only one time:
// and shared
// no repeating data needed

// learn:
// javascript?
// jquery?

// clean all projects in workspace

// method="post"