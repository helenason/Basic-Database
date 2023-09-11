package gov.example.appDemo;

//Creating an entity Employee
public class Student {

	public Student() {
	}

	// Parameterized Constructor
	// to assign the values
	// to the properties of
	// the entity
	public Student(Integer sid, String name, String email, Integer graduation, String degree) {
		super();
		this.sid = sid;
		this.name = name;
		this.email = email;
		this.graduation = graduation;
		this.degree = degree;
	}

	public Integer sid;
	public String name;
	public String email;
	public Integer graduation;
	public String degree;

	// Overriding the toString method
	// to find all the values
	@Override
	public String toString() {

		return "Student [sid=" + sid + ", name=" + name + ", email=" + email + ","
				+ "graduation=" + graduation + ", degree=" + degree + "]";

	}

	// Getters and setters of
	// the properties
	public Integer getId() {

		return sid;
	}

	public void setId(Integer sid) {

		this.sid = sid;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}
	public Integer getGraduation() {

		return graduation;
	}

	public void setGraduation(Integer graduation) {

		this.graduation = graduation;
	}
	public String getDegree() {

		return degree;
	}

	public void setDegree(String degree) {

		this.degree = degree;
	}
}
