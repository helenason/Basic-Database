package gov.example.appDemo;

import org.springframework.stereotype.Repository;

@Repository

// Class to create a list
// of employees
public class StudentDAO {

	static Students list = new Students();

	// This static block is executed
	// before executing the main block

	static {
		// Creating a few employees
		// and adding them to the list
		
		for (int i = 0; i < CrawlingStd.SL.size(); i++) {
			list.getStudentList().add
			(new Student(CrawlingStd.SL.get(i).sid, CrawlingStd.SL.get(i).name,
					CrawlingStd.SL.get(i).email, CrawlingStd.SL.get(i).graduation,
					CrawlingStd.SL.get(i).degree));
		}
	}

	// Method to return the list
	public Students getAllStudents() {
		return list;
	}

	// Method to add an employee
	// to the employees list
	public void addStudent(Student student) {
		list.getStudentList().add(student);

	}
}