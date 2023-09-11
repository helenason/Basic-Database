package gov.example.appDemo;

import java.sql.*;

public class StudentsDB {

	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	public StudentsDB() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");

		String url = "jdbc:postgresql://localhost/hongik";
		String userId = "postgres";
		String password = "1234";

		con = DriverManager.getConnection(url, userId, password);
		rs = null;
	}

	public void createDB() throws SQLException {
		
		try {
			String create_student = "CREATE TABLE students(sid INTEGER, name VARCHAR(100), email VARCHAR(100), "
					+ "graduation INTEGER, degree VARCHAR(10), primary key(email));";
			pstmt = con.prepareStatement(create_student);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void insertDB() throws SQLException {

		try {
			String insert_student = "INSERT INTO students(sid, name, email, graduation, degree) "
						+ "VALUES(?, ?, ?, ?, ?);";
			pstmt = con.prepareStatement(insert_student);

			for (int i = 0; i < CrawlingStd.SL.size(); i++) {
				pstmt.clearParameters();

				pstmt.setInt(1, CrawlingStd.SL.get(i).sid);
				pstmt.setString(2, CrawlingStd.SL.get(i).name);
				pstmt.setString(3, CrawlingStd.SL.get(i).email);
				pstmt.setInt(4, CrawlingStd.SL.get(i).graduation);
				pstmt.setString(5, CrawlingStd.SL.get(i).degree);

				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String[][] selectDBdegree(String name) throws SQLException { // 1번
		String[][] res = new String[10000][2];
		try {
			String select_degree = "SELECT name, degree FROM students WHERE name = ?;";
			
			pstmt = con.prepareStatement(select_degree);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			int i = 0;
			while (rs.next()) {
				res[i][0] = rs.getString(1);
				res[i][1] = rs.getString(2);
//				System.out.println("NAME = " + rs.getString(1) + ", DEGREE = " + rs.getString(2));
				i++;
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public String[][] selectDBemail(String name) throws SQLException { // 2번
		String[][] res = new String[10000][2];
		try {
			String select_email = "SELECT name, email FROM students WHERE name = ?;";

			pstmt = con.prepareStatement(select_email);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				res[i][0] = rs.getString(1);
				res[i][1] = rs.getString(2);
//				System.out.println("NAME = " + rs.getString(1) + ", EMAIL = " + rs.getString(2));
				i++;
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public String[][] selectDBstat(String degree) { // 3번
		String[][] res = new String[10000][2];
		try {
			String select_stat = "SELECT degree, COUNT(*) FROM students GROUP BY degree HAVING degree = ?;";

			pstmt = con.prepareStatement(select_stat);
			pstmt.setString(1, degree);
			rs = pstmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				res[i][0] = rs.getString(1);
				res[i][1] = rs.getString(2);
//				System.out.println("DEGREE = " + rs.getString(1) + ", COUNT = " + rs.getString(2));
				i++;
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public String registerDB(String name, String email, Integer graduation, String degree) { // 4번
		
		String res = "";
		try {
			
			String same_student = "SELECT * FROM students WHERE name=?";
			pstmt = con.prepareStatement(same_student);

			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				res = "Already registered";		
			} else {

				String insert_student = "INSERT INTO students VALUES(?, ?, ?, ?, ?);";
				pstmt = con.prepareStatement(insert_student);

				pstmt.setInt(1, Students.studentListSize() + 1);
				pstmt.setString(2, name);
				pstmt.setString(3, email);
				pstmt.setInt(4, graduation);
				pstmt.setString(5, degree);
				
				StudentDAO.list.getStudentList().add
				(new Student(Students.studentListSize() + 1, name, email, graduation, degree));
				
				int isInserted = pstmt.executeUpdate();
				if (isInserted == 1) {
					res = "Registration successful";
				}
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}
}