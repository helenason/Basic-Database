package gov.example.appDemo;

import java.util.ArrayList;
import java.util.List;

// Class to store the list of
// all the employees in an
// Array List
public class Students {

	private static List<Student> studentList;

	// Method to return the list
	// of employees
	public List<Student> getStudentList()
	{
		if (studentList == null) {
			studentList = new ArrayList<>();
		}
		
		return studentList;
	}

	public void setStudentList(List<Student> studentList)
	{
		Students.studentList = studentList;
	}
	
	public static Integer studentListSize()
	{
		return studentList.size();
	}
}
