package com.nitesh.servletDemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		
		out.println("<h3>This is Servlet Demo</h3><hr/>");
		
		ServletContext context = getServletContext();
		 String theGreetingMessage = getInitParameter("greeting");
	        String theServiceLevel = getInitParameter("serviceLevel");
	        
	        out.println("greeting: " + theGreetingMessage);
	        out.println("<br/><br/>");
	        out.println("serviceLevel: " + theServiceLevel);

	        
		out.println("Max size is : "+context.getInitParameter("max-size") + " "+context.getInitParameter("team-name"));
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
