package gov.example.appDemo;

import java.sql.SQLException;
import java.text.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Start {
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException {
		CrawlingStd.crawling();
		StudentsDB studentsdb = new StudentsDB();
		studentsdb.createDB();
		studentsdb.insertDB();
		SpringApplication.run(AppDemoApplication.class, args);
	}
}
