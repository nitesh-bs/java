package com.nitesh.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;

	@Resource(name = "jdbc/nitesh")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}
	
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			String cmd = request.getParameter("command");
			
			if (cmd == null) {
				cmd = "list";
			}
			switch (cmd) {
			case "list": {
				listStudents(request, response);
				break;
			}
			case "add": {
				addStudents(request, response);
				break;
			}
			default:
				listStudents(request, response);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String cmd = request.getParameter("command");

			if (cmd == null) {
				cmd = "list";
			}
			switch (cmd) {
			case "list": {
				listStudents(request, response);
				break;
			}
			case "add": {
				addStudents(request, response);
				break;
			}
			case "edit": {
				editStudents(request, response);
				break;
			}
			case "del": {
				delStudents(request, response);
				break;
			}
			case "search": {
				searchStudents(request, response);
				break;
			}
			case "update": {
				updateStudents(request, response);
				break;
			}
			default:
				listStudents(request, response);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void searchStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String theSearchName = request.getParameter("searchName");
        
        List<Student> students = studentDbUtil.searchStudents(theSearchName);
        
        request.setAttribute("stud_list", students);
                
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-student.jsp");
        dispatcher.forward(request, response);
		
	}



	private void updateStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("studId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student student  = new Student(id, firstName, lastName, email);
		
		studentDbUtil.updateStudent(student);
		
		listStudents(request, response);
		
	}

	private void delStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		String sid = request.getParameter("studId");
		
		studentDbUtil.deleteStudent(sid);
		

		listStudents(request, response);
		
		
	}

	private void editStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sid = request.getParameter("studId");
		
		Student student = studentDbUtil.getStudent(sid);
		
		request.setAttribute("the_student", student);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
				dispatcher.forward(request, response);
	}

	private void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student student = new Student(firstName, lastName, email);
		
		studentDbUtil.addStudent(student);
		
//		listStudents(request, response);
		response.sendRedirect(request.getContextPath()+"/StudentController?command=list");

	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Student> students = studentDbUtil.getStudents();

		request.setAttribute("stud_list", students);

		RequestDispatcher dispatcher = request.getRequestDispatcher("list-student.jsp");

		dispatcher.forward(request, response);

	}

}
