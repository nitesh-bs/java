package com.nitesh.servletDemo.mvc;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {

	public static List<Student> getStudents() {
		
		List<Student> students = new ArrayList<>();
		
		students.add(new Student("Nitesh", "Shekhada", "nit@gmail.com"));
		students.add(new Student("Yash", "Patel", "yash@gmail.com"));
		students.add(new Student("Jay", "Patil", "jay@gmail.com"));
		students.add(new Student("Raj", "Shah", "raj@gmail.com"));
		
		return students;
	}
}
