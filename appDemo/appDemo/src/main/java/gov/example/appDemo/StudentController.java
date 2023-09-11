package gov.example.appDemo;

import java.net.URI;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/students")

public class StudentController {

	@Autowired
	private StudentDAO studentdao;

	@GetMapping(path = "/degree", produces = "application/json")
	@ResponseBody
	public String getStudentdegree(@RequestParam(value = "name") String name) // 1번
			throws SQLException, ClassNotFoundException {
		StudentsDB studentsdb = new StudentsDB();
		String[][] nameDegree = studentsdb.selectDBdegree(name);

		String res = "";

		if (nameDegree[0][0] == null) {
			res = "No such student";
		} else if (nameDegree[1][0] != null) {
			res = "There are multiple students with the same name. " + "Please provide an email address instead.";
		} else {
			res += nameDegree[0][0];
			res += " : ";
			res += nameDegree[0][1];
		}

		return res;
	}

	@GetMapping(path = "/email", produces = "application/json")
	@ResponseBody
	public String getStudentemail(@RequestParam(value = "name") String name) // 2번
			throws SQLException, ClassNotFoundException {
		StudentsDB studentsdb = new StudentsDB();
		String[][] nameEmail = studentsdb.selectDBemail(name);

		String res = "";

		if (nameEmail[0][0] == null) {
			res = "No such student";
		} else if (nameEmail[1][0] != null) {
			res = "There are multiple students with the same name. Please provide an email address instead.";
		} else {
			res += nameEmail[0][0];
			res += " : ";
			res += nameEmail[0][1];
		}
		return res;
	}

	@GetMapping(path = "/stat", produces = "application/json")
	@ResponseBody
	public String getStudentstat(@RequestParam(value = "degree") String degree) // 3번
			throws SQLException, ClassNotFoundException {
		StudentsDB studentsdb = new StudentsDB();
		String[][] degreeCount = studentsdb.selectDBstat(degree);

		String res = "Number of ";
		res += degreeCount[0][0];
		res += "'s student : ";
		res += degreeCount[0][1];
		res += "\n";

		return res;
	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")

	public ResponseEntity<Object> addStudent(@RequestBody Student student) {

		Integer id = studentdao.getAllStudents().getStudentList().size() + 1;

		student.setId(id);

		studentdao.addStudent(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/register")
	@ResponseBody
	public String registerStudent( // 4번
			@RequestParam(value = "name") String name, @RequestParam(value = "email") String email,
			@RequestParam(value = "graduation") Integer graduation, @RequestParam(value = "degree") String degree)
			throws ClassNotFoundException, SQLException {

		String res = "";
		StudentsDB studentsdb = new StudentsDB();
		res = studentsdb.registerDB(name, email, graduation, degree);

		return res;

	}
}