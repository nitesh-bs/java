package com.nitesh.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		Connection myConn = null;
		java.sql.Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "select * from student";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				students.add(new Student(myRs.getInt("stud_id"), myRs.getString("firstName"),
						myRs.getString("lastName"), myRs.getString("email")));
			}

			return students;
		} finally {
			close(myConn, myStmt, myRs);
		}

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void addStudent(Student student) throws Exception {
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "insert into student" + "(firstName , lastName, email)" + " values (?,?,?)";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, student.getFirstName());
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
	}

	public Student getStudent(String sid) throws Exception{
		Student student = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int stud_id;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "select * from student where stud_id = ?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, Integer.parseInt(sid));
			
			myRs = myStmt.executeQuery();
			if(myRs != null)
			{
				if(myRs.next()) {
					student = new Student(Integer.parseInt(sid), myRs.getString("firstName"),
							myRs.getString("lastName"), myRs.getString("email"));
				
				}
				else {
					throw new Exception("Couldn't find Student "+sid);
				}
			}
			
			return student;
		} finally {
			close(myConn, myStmt, myRs);
		}
		
		
		
	}

	public void updateStudent(Student student) throws Exception{
		// TODO Auto-generated method stub
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		

		try {
			myConn = dataSource.getConnection();

			String sql = "update student set firstName=? , lastName=? , email=? "
					+ "where stud_id=?";
			myStmt = myConn.prepareStatement(sql);

			
			myStmt.setString(1, student.getFirstName());
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());
			myStmt.setInt(4, student.getStudId());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
		
	}

	public void deleteStudent(String sid)  throws Exception{
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt = null;
		

		try {
			int studId = Integer.parseInt(sid);
			myConn = dataSource.getConnection();

			String sql = "delete from student "
					+ "where stud_id=?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, studId);

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
		
		
	}

	public List<Student> searchStudents(String theSearchName) throws Exception{
List<Student> students = new ArrayList<>();
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int studentId;
        
        try {
            
            myConn = dataSource.getConnection();
            
            if (theSearchName != null && theSearchName.trim().length() > 0) {
                String sql = "select * from student where lower(firstName) like ? or lower(lastName) like ?";
                myStmt = myConn.prepareStatement(sql);
                
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
                myStmt.setString(2, theSearchNameLike);
                
            } else {
                String sql = "select * from student";
                
                myStmt = myConn.prepareStatement(sql);
            }
            
            myRs = myStmt.executeQuery();
            
            while (myRs.next()) {
                
                
                students.add( new Student(myRs.getInt("stud_id"), myRs.getString("firstName"),
						myRs.getString("lastName"), myRs.getString("email"))
				);            
            }
            
            return students;
        }
        finally {
            close(myConn, myStmt, myRs);
        }
	}

}
